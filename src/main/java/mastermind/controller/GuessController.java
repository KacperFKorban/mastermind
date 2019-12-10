package mastermind.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import mastermind.model.CodeWord;
import mastermind.model.GameSession;
import mastermind.model.Guess;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GuessController {

    private int size = 4;

    @FXML
    private Pane guessPane;

    @FXML
    private Button submitButton;

    private List<Circle> circles = new ArrayList<>(size);

    private Guess guess = new Guess(CodeWord.empty(size), 0, 0);
    private Runnable onSubmit;

    @FXML
    protected void initialize() {
        populateGuessPane();
        updateSubmitButton();
    }

    private void populateGuessPane() {
        guessPane.getChildren().clear();
        circles.clear();
        for(int i = 0; i < size; i++) {
            Circle circle = new Circle(30.0f, Color.DIMGRAY);
            circle.setId("slot-" + i);
            circle.setCenterX(i * 100 + 50);
            circle.setCenterY(50);
            guessPane.getChildren().add(circle);
            circles.add(circle);
        }
        guessPane.setPrefSize(size * 100, 100);
    }

    public void setDragHandler(DragHandler dragHandler) {
        dragHandler.setOnDropped((event, color) -> {
            Optional<Circle> hitCircle = circles.stream()
                    .filter(c -> c.contains(c.sceneToLocal(event.getSceneX(), event.getSceneY())))
                    .findFirst();
            hitCircle.ifPresent(c -> {
                guess.getWord().setColor(circles.indexOf(c), color);
                c.setFill(color);
                updateSubmitButton();
            });
        });
    }

    private void updateSubmitButton() {
        submitButton.setDisable(!guess.getWord().checkIfEveryColorIsChosen());
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setOnSubmit(Runnable onSubmit) {
        this.onSubmit = onSubmit;
    }

    public void handleSubmit(ActionEvent actionEvent) {
        if (this.onSubmit != null) {
            this.onSubmit.run();
        }
    }

    public Guess getGuess() {
        return guess;
    }

    public void setGuess(Guess guess) {
        this.guess = guess;
        for (int i = 0; i < circles.size(); i++) {
            circles.get(i).setFill(guess.getWord().getColors().get(i));
        }
        updateSubmitButton();
    }

    public void setGameSession(GameSession gameSession) {
        if (size != gameSession.getGuessWordLength()) {
            guess = new Guess(CodeWord.empty(gameSession.getGuessWordLength()), 0, 0);
        }
        size = gameSession.getGuessWordLength();
        populateGuessPane();
    }
}

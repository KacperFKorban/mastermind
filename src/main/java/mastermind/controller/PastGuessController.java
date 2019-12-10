package mastermind.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import mastermind.model.Guess;

import java.util.ArrayList;
import java.util.List;

public class PastGuessController {

    private static final Color HINT_CORRECT_PLACE = Color.GREEN;
    private static final Color HINT_CORRECT_COLOR = Color.WHITE;
    private static final Color HINT_INCORRECT = Color.DIMGRAY;

    @FXML
    private Pane wordPane;
    @FXML
    private Pane hintPane;

    private Guess guess;
    private int wordSize;

    private List<Circle> wordCircles = new ArrayList<>();
    private List<Circle> hintCircles = new ArrayList<>();

    @FXML
    protected void initialize() {

    }

    public void setGuess(Guess guess) {
        this.guess = guess;
        populateHintPane();
        populateWordPane();
        updateColors();
    }

    public void setWordSize(int wordSize) {
        this.wordSize = wordSize;
        populateWordPane();
        populateHintPane();
        updateColors();
    }

    private void populateWordPane() {
        wordPane.getChildren().clear();
        wordCircles.clear();
        for(int i = 0; i < wordSize; i++) {
            Circle circle = new Circle(30.0f, Color.DIMGRAY);
            circle.setId("slot-" + i);
            circle.setCenterX(i * 100 + 50);
            circle.setCenterY(50);
            wordPane.getChildren().add(circle);
            wordCircles.add(circle);
        }
        wordPane.setPrefSize(wordSize * 100, 100);
    }

    private void populateHintPane() {
        hintPane.getChildren().clear();
        hintCircles.clear();
        for (int i = 0; i < wordSize; i++) {
            Circle circle = new Circle(5f, HINT_INCORRECT);
            circle.setId("hint-" + i);
            circle.setCenterX((double) (i / 2 * 20 + 10));
            circle.setCenterY((double) (i % 2 * 20 + 40));
            hintPane.getChildren().add(circle);
            hintCircles.add(circle);
        }
        hintPane.setPrefWidth((wordSize + 1) / 2 * 20);
    }

    private void updateColors() {
        if (guess == null) {
            return;
        }

        for (int i = 0; i < wordCircles.size(); i++) {
            wordCircles.get(i).setFill(guess.getWord().getColors().get(i));
        }

        if (wordSize < guess.getWord().getColors().size()) {
            return;
        }

        int hintIndex = 0;
        for (int i = 0; i < guess.getCorrectPlace() && hintIndex < wordSize; i++, hintIndex++) {
            hintCircles.get(hintIndex).setFill(HINT_CORRECT_PLACE);
        }
        for (int i = 0; i < guess.getCorrectColor() && hintIndex < wordSize; i++, hintIndex++) {
            hintCircles.get(hintIndex).setFill(HINT_CORRECT_COLOR);
        }
        for (; hintIndex < wordSize; hintIndex++) {
            hintCircles.get(hintIndex).setFill(HINT_INCORRECT);
        }
    }
}

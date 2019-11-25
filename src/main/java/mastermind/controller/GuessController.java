package mastermind.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GuessController {

    private final int size = 4;

    @FXML
    private Pane guessPane;

    private List<Circle> circles = new ArrayList<>(size);

    @FXML
    protected void initialize() {
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
            hitCircle.ifPresent(c -> c.setFill(color));
        });
    }
}

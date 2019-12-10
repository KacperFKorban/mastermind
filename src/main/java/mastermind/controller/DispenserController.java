package mastermind.controller;

import com.google.inject.Singleton;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import mastermind.model.GameSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Singleton
public class DispenserController {

    @FXML
    private Pane dispenserPane;

    private int height = 8;
    private int width = 1;

    public static final ArrayList<Color> COLORS = new ArrayList<>(
            Arrays.asList(Color.BLUE, Color.FUCHSIA, Color.CYAN, Color.ORANGE, Color.PERU, Color.GREEN, Color.YELLOW, Color.RED)
    );

    private List<Circle> circles = new ArrayList<>();

    @FXML
    protected void initialize() {
        populateDispenserPane();
    }

    private void populateDispenserPane() {
        dispenserPane.getChildren().clear();
        circles.clear();

        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                Circle circle = new Circle(30.0f, COLORS.get(i*width + j));
                circle.setId("dispenser-" + i);
                circle.setCenterX(i * 100 + 50);
                circle.setCenterY(j * 100 + 50);
                dispenserPane.getChildren().add(circle);
                circles.add(circle);
            }
        }
        dispenserPane.setPrefSize(width * 100, height * 100);
    }

    public void setGameSession(GameSession gameSession) {
        height = gameSession.getDispenserHeight();
        width = gameSession.getDispenserWidth();
        populateDispenserPane();
    }

    public void setDragHandler(DragHandler dragHandler) {
        dispenserPane.getChildren().add(dragHandler.getDragCircle());
        for (Circle circle : circles) {
            circle.setOnMousePressed(event -> dragHandler.startDrag(event, circle));
            circle.setOnMouseDragged(dragHandler::onDrag);
            circle.setOnMouseReleased(dragHandler::onDrop);
        }
    }

}

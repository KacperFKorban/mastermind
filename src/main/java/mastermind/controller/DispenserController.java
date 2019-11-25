package mastermind.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Arrays;


public class DispenserController {

    @FXML
    private Pane dispenserPane;

    private final int height = 8;
    private final int width = 1;

    private final ArrayList<Color> colors = new ArrayList(
            Arrays.asList(Color.BLUE, Color.FUCHSIA, Color.CYAN, Color.ORANGE, Color.PERU, Color.GREEN, Color.YELLOW, Color.RED)
    );


    @FXML
    protected void initialize() {
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {

                Circle circle = new Circle(30.0f, colors.get(i*width + j));
                circle.setId("dispenser-" + i);
                circle.setCenterX(i * 100 + 50);
                circle.setCenterY(j * 100 + 50);
                dispenserPane.getChildren().add(circle);
            }
        }
        dispenserPane.setPrefSize(width * 100, height * 100);
    }

}

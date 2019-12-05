package mastermind.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import mastermind.Main;
import mastermind.model.Board;
import mastermind.model.CodeWord;

import java.io.IOException;
import java.util.Arrays;

public class BoardController extends AbstractController {

    private final int guessWidth = 4;
    private final int dispenserWidth = 1;
    private final int dispenserHeight = 8;

    private Board board = new Board(new CodeWord(Arrays.asList(
            Color.BLUE, Color.FUCHSIA, Color.CYAN, Color.ORANGE
    )));

    @FXML
    private Pane guessPane;

    @FXML
    private Pane dispenserPane;

    @FXML
    private DispenserController dispenserController;

    @FXML GuessController guessController;

    private DragHandler dragHandler = new DragHandler();

    @Override
    public void initLayout(Stage primaryStage) {
        try {
            FXMLLoader boardLoader = new FXMLLoader();
            boardLoader.setLocation(Main.class.getResource("view/BoardView.fxml"));

            AnchorPane rootLayout = (AnchorPane) boardLoader.load();
            rootLayout.setMinHeight(100 * dispenserHeight);
            rootLayout.setMinWidth(100 * (dispenserWidth + guessWidth));

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void initialize() {
        guessPane.setLayoutX(100 * dispenserWidth);
        guessPane.setLayoutY(100 * (dispenserHeight - 1));

        dispenserController.setDragHandler(dragHandler);
        guessController.setDragHandler(dragHandler);
    }
}

package mastermind.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
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
import mastermind.model.GameSession;

import java.io.IOException;
import java.util.Arrays;

@Singleton
public class BoardController extends AbstractController {

    private final int guessWidth = 4;
    private final int dispenserWidth = 1;
    private final int dispenserHeight = 8;

    private GameSession gameSession;

    private Board board = new Board(new CodeWord(Arrays.asList(
            Color.BLUE, Color.FUCHSIA, Color.CYAN, Color.ORANGE
    )));

    @FXML
    private Pane guessPane;

    @FXML
    private Pane dispenserPane;

    @FXML
    private DispenserController dispenserController;

    @FXML
    private GuessController guessController;

    private DragHandler dragHandler = new DragHandler();

    @Inject
    private FxmlLoaderFactory fxmlLoaderFactory;

    @Override
    public void initLayout(Stage primaryStage) {
        try {
            FXMLLoader boardLoader = fxmlLoaderFactory.createFxmlLoader();
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

    public GameSession getGameSession() {
        return gameSession;
    }

    public void setGameSession(GameSession gameSession) {
        this.gameSession = gameSession;
    }
}

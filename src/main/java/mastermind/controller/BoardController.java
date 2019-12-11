package mastermind.controller;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mastermind.Main;
import mastermind.model.Board;
import mastermind.model.CodeWord;
import mastermind.model.GameSession;
import mastermind.model.Guess;

import java.io.IOException;

@Singleton
public class BoardController extends AbstractController {

    private GameSession gameSession;

    private Board board;

    private Stage stage;

    @FXML
    private Pane guessPane;

    @FXML
    private Pane dispenserPane;

    @FXML
    private VBox pastGuesses;

    @FXML
    private DispenserController dispenserController;

    @FXML
    private GuessController guessController;

    private DragHandler dragHandler = new DragHandler();

    @Inject
    private FxmlLoaderFactory fxmlLoaderFactory;

    @Inject
    private ScoreController scoreController;

    @Override
    public void initLayout(Stage primaryStage) {
        try {
            this.board = new Board(CodeWord.random(gameSession));
            FXMLLoader boardLoader = fxmlLoaderFactory.createFxmlLoader();
            boardLoader.setLocation(Main.class.getResource("view/BoardView.fxml"));

            AnchorPane rootLayout = (AnchorPane) boardLoader.load();
            rootLayout.setMinHeight(100 * gameSession.getDispenserHeight());
            rootLayout.setMinWidth(100 * (gameSession.getDispenserWidth() + gameSession.getGuessWordLength()));

            this.stage = primaryStage;
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void initialize() {
        guessPane.setLayoutX(100 * gameSession.getDispenserWidth());

        dispenserController.setGameSession(gameSession);
        guessController.setGameSession(gameSession);

        initBoard();

        dispenserController.setDragHandler(dragHandler);
        guessController.setDragHandler(dragHandler);

        guessController.setOnSubmit(this::guessSubmitted);
    }

    private void initBoard() {
        board.setSolution(CodeWord.random(gameSession));
    }

    public GameSession getGameSession() {
        return gameSession;
    }

    public void setGameSession(GameSession gameSession) {
        this.gameSession = gameSession;
    }

    private void guessSubmitted() {
        if (board.submitGuess(guessController.getGuess())) {
            updatePastGuesses();
            if (guessController.getGuess().getCorrectPlace() == gameSession.getGuessWordLength() || pastGuesses.getChildren().size() == gameSession.getMaxGuessQuantity()) {
                gameSession.setGuessed(guessController.getGuess().getCorrectPlace() == gameSession.getGuessWordLength());
                gameSession.setGuessesMade(pastGuesses.getChildren().size());
                scoreController.setGameSession(gameSession);
                scoreController.initLayout(stage);
            }
            guessController.setGuess(new Guess(CodeWord.empty(gameSession.getGuessWordLength()), 0, 0));
        }
    }

    private void updatePastGuesses() {
        ObservableList<Node> pastGuessChildren = pastGuesses.getChildren();
        for (int i = pastGuessChildren.size(); i < board.getPastGuesses().size(); i++) {
            FXMLLoader loader = fxmlLoaderFactory.createFxmlLoader();
            loader.setLocation(Main.class.getResource("view/PastGuessView.fxml"));

            try {
                HBox guessView = loader.load();
                PastGuessController controller = loader.getController();
                controller.setGuess(board.getPastGuesses().get(i));
                controller.setWordSize(gameSession.getGuessWordLength());
                pastGuessChildren.add(0, guessView);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}

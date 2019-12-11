package mastermind.controller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mastermind.Main;
import mastermind.model.GameSession;

import javax.inject.Inject;
import java.io.IOException;

public class ScoreController extends AbstractController {

    private static GameSession gameSession;

    @Inject
    private FxmlLoaderFactory fxmlLoaderFactory;

    @FXML
    private IntegerProperty score = new SimpleIntegerProperty(0);

    @Override
    public void initLayout(Stage stage) {
        try {
            FXMLLoader scoreLoader = fxmlLoaderFactory.createFxmlLoader();
            if (gameSession.isGuessed()) {
                scoreLoader.setLocation(Main.class.getResource("view/WonView.fxml"));
            } else {
                scoreLoader.setLocation(Main.class.getResource("view/LostView.fxml"));
            }
            AnchorPane rootLayout = (AnchorPane) scoreLoader.load();

            Scene scene = new Scene(rootLayout);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void initialize() {
        setScore(gameSession.getScore());
    }

    public void setGameSession(GameSession gameSession) {
        this.gameSession = gameSession;
    }

    public int getScore() {
        return score.get();
    }

    public void setScore(int score) {
        this.score.set(score);
    }

    public IntegerProperty scoreProperty() {
        return score;
    }
}

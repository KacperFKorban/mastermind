package mastermind.controller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Pair;
import mastermind.Main;
import mastermind.model.GameSession;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.sql.SQLException;

@Singleton
public class ScoreController extends AbstractController {

    private static GameSession gameSession;

    @Inject
    private FxmlLoaderFactory fxmlLoaderFactory;

    @Inject
    private MainMenuController mainMenuController;

    @FXML
    private TableView<Pair<String, Integer>> rankingTable;

    @FXML
    private TableColumn<Pair<String, Integer>, String> nameColumn;

    @FXML
    private TableColumn<Pair<String, Integer>, Integer> scoreColumn;

    @FXML
    private IntegerProperty score = new SimpleIntegerProperty(0);

    @FXML
    private Pane wordPane;

    private Stage stage;

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

            this.stage = stage;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSolution() {
        System.out.println(wordPane);
        wordPane.getChildren().clear();
        for(int i = 0; i < gameSession.getGuessWordLength(); i++) {
            Circle circle = new Circle(20.0f, gameSession.getSolution().getColors().get(i));
            System.out.println(gameSession.getSolution().getColors().get(i));
            circle.setId("slot-" + i);
            circle.setCenterX(i * 50 + 50);
            circle.setCenterY(50);
            wordPane.getChildren().add(circle);
        }
        wordPane.setPrefSize(gameSession.getGuessWordLength() * 100, 100);
    }

    @FXML
    protected void initialize() {
        if(!gameSession.isGuessed()) {
            setSolution();
        } else {
            rankingTable.setItems(FXCollections.observableArrayList(gameSession.getRanking()));

            nameColumn.setCellValueFactory(dataValue -> new SimpleStringProperty(dataValue.getValue().getKey()));
            scoreColumn.setCellValueFactory(dataValue -> new SimpleObjectProperty<Integer>(dataValue.getValue().getValue()));
        }
        setScore(gameSession.getScore());
    }


    public void restartButtonClicked(ActionEvent actionEvent) {
        mainMenuController.initLayout(stage);
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

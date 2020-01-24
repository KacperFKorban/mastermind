package mastermind.controller;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mastermind.Main;
import mastermind.MasterMindModule;
import mastermind.db.SqliteDb;
import mastermind.model.GameSession;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class MainMenuController extends AbstractController {

    private Stage stage;

    @FXML
    private ComboBox usernamesComboBox;

    @FXML
    private ComboBox lengthComboBox;

    @FXML
    private ComboBox coloursComboBox;

    @FXML
    private ComboBox maxGuessComboBox;

    @FXML
    private Button addNewGamerButton;

    @FXML
    private Button startButton;

    private List<String> usernamesList = new ArrayList<>();

    @Inject
    @Named("length_list")
    private List<Integer> lengthList;

    @Inject
    @Named("colours_list")
    private List<Integer> coloursList;

    @Inject
    @Named("max_guess_list")
    private List<Integer> maxGuessList;

    @Inject
    private GameSession gameSession;

    @Inject
    private BoardController boardController;

    @Inject
    private FxmlLoaderFactory fxmlLoaderFactory;

    @Inject
    private AddNewGamerController addNewGamerController;

    @Inject
    private SqliteDb sqliteDb;

    @Override
    public void initLayout(Stage stage) {
        try {
            FXMLLoader mainMenuLoader = fxmlLoaderFactory.createFxmlLoader();
            mainMenuLoader.setLocation(Main.class.getResource("view/MainMenuView.fxml"));
            AnchorPane rootLayout = (AnchorPane) mainMenuLoader.load();

            this.stage = stage;
            Scene scene = new Scene(rootLayout);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void initialize() throws SQLException {

        Injector injector = Guice.createInjector(new MasterMindModule());
        injector.injectMembers(this);

        startButton.disableProperty().setValue(true);

        usernamesComboBox.setItems(FXCollections.observableArrayList(sqliteDb.getGamers()));
        usernamesComboBox.getSelectionModel().select(usernamesList.indexOf(gameSession.getName()));
        usernamesComboBox.setOnAction(usernamesComboBoxHandler);

        lengthComboBox.setItems(FXCollections.observableArrayList(lengthList));
        lengthComboBox.getSelectionModel().select(lengthList.indexOf(gameSession.getGuessWordLength()));
        lengthComboBox.setOnAction(lengthComboBoxHandler);

        coloursComboBox.setItems(FXCollections.observableArrayList(coloursList));
        coloursComboBox.getSelectionModel().select(coloursList.indexOf(gameSession.getColoursQuantity()));
        coloursComboBox.setOnAction(coloursComboBoxHandler);

        maxGuessComboBox.setItems(FXCollections.observableArrayList(maxGuessList));
        maxGuessComboBox.getSelectionModel().select(maxGuessList.indexOf(gameSession.getMaxGuessQuantity()));
        maxGuessComboBox.setOnAction(maxGuessComboBoxHandler);

        addNewGamerButton.setOnAction(addNewGamerHandler);
        startButton.setOnAction(startButtonHandler);
    }

    private ChangeListener<String> usernameHandler = (event, old, newOne) -> {
        gameSession.setName((String) usernamesComboBox.getValue());
    };

    private EventHandler<ActionEvent> lengthComboBoxHandler = (event) -> {
        gameSession.setGuessWordLength((Integer) lengthComboBox.getValue());
    };

    private EventHandler<ActionEvent> coloursComboBoxHandler = (event) -> {
        gameSession.setColoursQuantity((Integer) coloursComboBox.getValue());
    };

    private EventHandler<ActionEvent> maxGuessComboBoxHandler = (event) -> {
        gameSession.setMaxGuessQuantity((Integer) maxGuessComboBox.getValue());
    };

    private EventHandler<ActionEvent> startButtonHandler = (event) -> {
        boardController.setGameSession(gameSession);
        boardController.initLayout(stage);
    };

    private EventHandler<ActionEvent> usernamesComboBoxHandler = (event) -> {
        gameSession.setName((String) usernamesComboBox.getValue());
        startButton.disableProperty().setValue(false);
    };

    private EventHandler<ActionEvent> addNewGamerHandler = (event) -> {
        addNewGamerController.initLayout(stage);
        try {
            usernamesComboBox.setItems(FXCollections.observableArrayList(sqliteDb.getGamers()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };

}

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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mastermind.Main;
import mastermind.MasterMindModule;
import mastermind.model.GameSession;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;

@Singleton
public class MainMenuController extends AbstractController {

    private Stage stage;

    @FXML
    private TextField username;

    @FXML
    private ComboBox lengthComboBox;

    @FXML
    private ComboBox coloursComboBox;

    @FXML
    private ComboBox maxGuessComboBox;

    @FXML
    private Button startButton;

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
    protected void initialize() {

        Injector injector = Guice.createInjector(new MasterMindModule());
        injector.injectMembers(this);

        username.textProperty().setValue(gameSession.getName());
        username.textProperty().addListener(usernameHandler);

        lengthComboBox.setItems(FXCollections.observableArrayList(lengthList));
        lengthComboBox.getSelectionModel().select(lengthList.indexOf(gameSession.getGuessWordLength()));
        lengthComboBox.setOnAction(lengthComboBoxHandler);

        coloursComboBox.setItems(FXCollections.observableArrayList(coloursList));
        coloursComboBox.getSelectionModel().select(coloursList.indexOf(gameSession.getColoursQuantity()));
        coloursComboBox.setOnAction(coloursComboBoxHandler);

        maxGuessComboBox.setItems(FXCollections.observableArrayList(maxGuessList));
        maxGuessComboBox.getSelectionModel().select(maxGuessList.indexOf(gameSession.getMaxGuessQuantity()));
        maxGuessComboBox.setOnAction(maxGuessComboBoxHandler);

        startButton.setOnAction(startButtonHandler);
    }

    private ChangeListener<String> usernameHandler = (event, old, newOne) -> {
        gameSession.setName(username.getText());
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

}

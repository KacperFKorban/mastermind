package mastermind.controller;

import com.google.inject.Inject;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mastermind.Main;
import javafx.scene.control.Button;

import javax.inject.Singleton;
import java.io.IOException;

@Singleton
public class AddNewGamerController extends AbstractController {

    private Stage stage;

    @FXML
    private TextField username;

    @FXML
    private TextField email;

    @FXML
    private Button addNewGamerButton;

    @Inject
    private FxmlLoaderFactory fxmlLoaderFactory;

    @Override
    public void initLayout(Stage parentStage) {
        try {
            FXMLLoader addNewGamerLoader = fxmlLoaderFactory.createFxmlLoader();
            addNewGamerLoader.setLocation(Main.class.getResource("view/AddNewGamerView.fxml"));
            AnchorPane rootLayout = (AnchorPane) addNewGamerLoader.load();

            this.stage = new Stage();
            this.stage.initOwner(parentStage);
            Scene scene = new Scene(rootLayout);
            this.stage.setScene(scene);
            this.stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void initialize(){
        username.textProperty().addListener(usernameHandler);
        email.textProperty().addListener(emailHandler);
        addNewGamerButton.setOnAction(addNewGamerButtonHandler);
    }

    private ChangeListener<String> usernameHandler = (event, old, newOne) -> {
        // TO DO: validate username
    };

    private ChangeListener<String> emailHandler = (event, old, newOne) -> {
        // TO DO: add email to database
    };

    private EventHandler<ActionEvent> addNewGamerButtonHandler = (event) -> {
        //TO DO: add gamer to database and actualize combobox
        stage.hide();
    };

}

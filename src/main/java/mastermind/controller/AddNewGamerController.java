package mastermind.controller;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import javax.inject.Singleton;
import java.awt.*;

@Singleton
public class GamerController extends AbstractController {

    private Stage stage;

    @FXML
    private TextField username;

    @FXML
    private TextField email;

    @FXML
    private Button addNewGamerButton;

    @Override
    public void initLayout(Stage stage) {

    }

    @FXML
    protected void initialize(){

    }

    private ChangeListener<String> usernameHandler = (event, old, newOne) -> {
        gameSession.setName(username.getText());
    };

    private ChangeListener<String> emailHandler = (event, old, newOne) -> {
        gameSession.setName(email.getText());
    };

    private EventHandler<ActionEvent> addNewGamerButtonHandler = (event) -> {
        addNewGamerButton.
    };

}

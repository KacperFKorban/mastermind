package mastermind;

import com.google.inject.Guice;
import com.google.inject.Injector;
import mastermind.controller.AbstractController;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.inject.Inject;
import javax.inject.Named;

public class Main extends Application {

    private Stage primaryStage;

    @Inject
    private AbstractController mainController;

    @Inject
    @Named("title")
    private String title;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        Injector injector = Guice.createInjector(new MasterMindModule());
        injector.injectMembers(this);

        primaryStage.setTitle(title);
        this.mainController.initLayout(stage);
    }

    public static void main(String[] args) {
        launch();
    }

}

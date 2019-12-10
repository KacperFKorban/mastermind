package mastermind.controller;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import javafx.fxml.FXMLLoader;

@Singleton
public class FxmlLoaderFactory {

    @Inject
    private Injector injector;

    public FXMLLoader createFxmlLoader() {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(c -> injector.getInstance(c));
        return loader;
    }

}

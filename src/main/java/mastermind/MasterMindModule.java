package mastermind;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import mastermind.controller.AbstractController;
import mastermind.controller.BoardController;
import mastermind.controller.MainMenuController;
import mastermind.model.GameSession;

import java.util.Arrays;
import java.util.List;

public class MasterMindModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(AbstractController.class).to(MainMenuController.class);

        bind(String.class).annotatedWith(Names.named("title")).toInstance("MasterAndrzej");


        bind(new TypeLiteral<List<Integer>>(){}).annotatedWith(Names.named("length_list")).toInstance(Arrays.asList(3, 4, 5));
        bind(new TypeLiteral<List<Integer>>(){}).annotatedWith(Names.named("colours_list")).toInstance(Arrays.asList(6, 7, 8, 9, 10, 11, 12));
        bind(new TypeLiteral<List<Integer>>(){}).annotatedWith(Names.named("max_guess_list")).toInstance(Arrays.asList(5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17));
        bind(GameSession.class).toInstance(new GameSession("Tu wpisz nazwe gracza", 4, 8, 10));
    }
}

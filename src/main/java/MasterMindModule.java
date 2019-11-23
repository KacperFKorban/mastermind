import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import controller.AbstractController;
import controller.BoardController;

public class MasterMindModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(AbstractController.class).to(BoardController.class);
        bind(String.class).annotatedWith(Names.named("title")).toInstance("MasterAndrzej");
    }
}

package hr.pyro.chuckjokes.presentation.di.component.application;

import javax.inject.Singleton;

import dagger.Component;
import hr.pyro.chuckjokes.presentation.ChuckJokeApplication;
import hr.pyro.chuckjokes.presentation.di.module.ApplicationModule;
import hr.pyro.chuckjokes.presentation.di.module.DataModule;
import hr.pyro.chuckjokes.presentation.di.module.UseCaseModule;
import hr.pyro.chuckjokes.presentation.di.module.UtilsModule;

@Singleton
@Component(
        modules = {
                ApplicationModule.class,
//                UtilsModule.class,
//                ThreadingModule.class,
//                ConverterModule.class,
                DataModule.class,
//                AnalyticsModule.class
                UseCaseModule.class,
                UtilsModule.class
        }
)
public interface ApplicationComponent extends ApplicationComponentExposes, ApplicationComponentInjects{

    /**
     * An initializer that creates the graph from an application.
     */
    final class Initializer {
        static public ApplicationComponent init(ChuckJokeApplication application) {
            return DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(application))
                    .build();
        }

        // No instances
        private Initializer() {
        }
    }

}

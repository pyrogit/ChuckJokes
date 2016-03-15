package hr.pyro.chuckjokes.presentation.di.component.activity;


import dagger.Component;
import hr.pyro.chuckjokes.presentation.di.component.application.ApplicationComponent;
import hr.pyro.chuckjokes.presentation.di.module.ActivityModule;
import hr.pyro.chuckjokes.presentation.di.module.DetailsJokeActivityModule;
import hr.pyro.chuckjokes.presentation.di.module.JokeListActivityModule;
import hr.pyro.chuckjokes.presentation.di.module.RandomJokeActivityModule;
import hr.pyro.chuckjokes.presentation.di.scope.ActivityScope;

@ActivityScope
@Component(
        dependencies = {ApplicationComponent.class},
        modules = {
                ActivityModule.class,
                DetailsJokeActivityModule.class,
                JokeListActivityModule.class,
                RandomJokeActivityModule.class
        }
)
public interface ActivityComponent extends ApplicationComponent,
        ActivityComponentInjects,
        ActivityComponentExposes {

//    final class Initializer {
//        static public ActivityComponent init(InjectorActivity activity,
//                                             ApplicationComponent applicationComponent) {
//            return DaggerActivityComponent.builder()
//                    .applicationComponent(applicationComponent)
//                    .activityModule(new ActivityModule(activity))
//                    .detailsJokeActivityModule(new DetailsJokeActivityModule())
//                    .jokeListActivityModule(new JokeListActivityModule())
//                    .randomJokeActivityModule(new RandomJokeActivityModule())
//                    .build();
//        }
//
//        // No instances
//        private Initializer() {
//        }
//    }
}

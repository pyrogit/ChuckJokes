package hr.pyro.chuckjokes.presentation.di.component;

import hr.pyro.chuckjokes.presentation.ChuckJokeApplication;
import hr.pyro.chuckjokes.presentation.di.component.activity.ActivityComponent;
import hr.pyro.chuckjokes.presentation.di.component.activity.DaggerActivityComponent;
import hr.pyro.chuckjokes.presentation.di.component.application.ApplicationComponent;
import hr.pyro.chuckjokes.presentation.di.module.ActivityModule;
import hr.pyro.chuckjokes.presentation.di.module.DetailsJokeActivityModule;
import hr.pyro.chuckjokes.presentation.di.module.JokeListActivityModule;
import hr.pyro.chuckjokes.presentation.di.module.RandomJokeActivityModule;
import hr.pyro.chuckjokes.presentation.view.activity.InjectorActivity;

/**
 * Created on 03.03.16..
 */
public final class ComponentFactory {

    private ComponentFactory() {
        throw new RuntimeException("Must not instantiate this class!");
    }

    public static ApplicationComponent createApplicationComponent(final ChuckJokeApplication application) {
        return ApplicationComponent.Initializer.init(application);
    }

    public static ActivityComponent createActivityComponent(final InjectorActivity activity) {
        return DaggerActivityComponent.builder()
                .applicationComponent(ChuckJokeApplication.getInstance().getApplicationComponent())
                .activityModule(new ActivityModule(activity))
                .detailsJokeActivityModule(new DetailsJokeActivityModule())
                .jokeListActivityModule(new JokeListActivityModule())
                .randomJokeActivityModule(new RandomJokeActivityModule())
                .build();
    }
}

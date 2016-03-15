package hr.pyro.chuckjokes.presentation;

import android.app.Application;

import hr.pyro.chuckjokes.presentation.di.component.ComponentFactory;
import hr.pyro.chuckjokes.presentation.di.component.application.ApplicationComponent;

/**
 * Created on 02.03.16..
 */
public class ChuckJokeApplication extends Application {

    private static ChuckJokeApplication instance = null;
    private ApplicationComponent applicationComponent;

    public ChuckJokeApplication() {
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = ComponentFactory.createApplicationComponent(this);
        applicationComponent.inject(this);
    }

    public static ChuckJokeApplication getInstance() {
        return instance;
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}

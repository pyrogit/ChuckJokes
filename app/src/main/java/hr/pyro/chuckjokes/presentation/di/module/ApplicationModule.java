package hr.pyro.chuckjokes.presentation.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hr.pyro.chuckjokes.presentation.ChuckJokeApplication;
import hr.pyro.chuckjokes.presentation.di.qualifier.ForApplication;

/**
 * Created on 02.03.16..
 */
@Module
public final class ApplicationModule {

    private final ChuckJokeApplication application;

    public ApplicationModule(final ChuckJokeApplication chuckJokeApplication){
        this.application = chuckJokeApplication;
    }

    @Provides
    @Singleton
    ChuckJokeApplication provideApplication() {
        return this.application;
    }

    @Provides
    @Singleton
    @ForApplication
    Context provideApplicationContext() {
        return this.application;
    }

}

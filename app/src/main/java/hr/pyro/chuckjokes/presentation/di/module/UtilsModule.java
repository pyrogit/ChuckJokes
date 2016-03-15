package hr.pyro.chuckjokes.presentation.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hr.pyro.chuckjokes.presentation.util.ErrorMessageFactory;
import hr.pyro.chuckjokes.presentation.util.ErrorMessageFactoryImpl;

/**
 * Created on 08.03.16..
 */
@Module
public final class UtilsModule {

    @Provides
    @Singleton
    ErrorMessageFactory provideErrorMessageFactory(ErrorMessageFactoryImpl errorMessageFactory){
        return errorMessageFactory;
    }
}

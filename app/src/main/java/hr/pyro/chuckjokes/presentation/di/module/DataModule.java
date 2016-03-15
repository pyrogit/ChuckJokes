package hr.pyro.chuckjokes.presentation.di.module;

import android.content.Context;

import com.hannesdorfmann.sqlbrite.dao.DaoManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hr.pyro.chuckjokes.data.Constants;
import hr.pyro.chuckjokes.data.entity.mapper.JokeDataEntityDomainModelMapper;
import hr.pyro.chuckjokes.data.repository.JokeRepositoryImpl;
import hr.pyro.chuckjokes.data.repository.datasource.JokeApiDataStore;
import hr.pyro.chuckjokes.data.repository.datasource.JokeApiDataStoreImpl;
import hr.pyro.chuckjokes.data.repository.datasource.JokeDBDataStore;
import hr.pyro.chuckjokes.data.repository.datasource.JokeDBDataStoreImpl;
import hr.pyro.chuckjokes.domain.repository.JokeRepository;
import hr.pyro.chuckjokes.presentation.di.qualifier.ForApplication;
import hr.pyro.chuckjokes.presentation.model.converter.JokeDomainModelViewModelConverter;
import hr.pyro.chuckjokes.presentation.model.converter.JokeDomainModelViewModelConverterImpl;

/**
 * Created on 09.03.16..
 */
@Module
public final class DataModule {

    //////////////
    //DATA STORE//
    //////////////

    @Provides
    @Singleton
    JokeApiDataStore provideJokeApiDataStore() {
        return new JokeApiDataStoreImpl();
    }

    @Provides
    @Singleton
    JokeDBDataStore provideJokeDBDataStore(@ForApplication Context context) {
        JokeDBDataStoreImpl jokeDBDataStore = new JokeDBDataStoreImpl();
        DaoManager.with(context)
                .databaseName(Constants.JOKE_DATABASE_NAME)
                .version(Constants.JOKE_DATABASE_VERSION)
                .add(jokeDBDataStore)
                .logging(true)
                .build();
        return jokeDBDataStore;
    }

    ///////////////
    //DATA MAPPER//
    ///////////////

    @Provides
    @Singleton
    JokeDataEntityDomainModelMapper providesJokeEntityDataMapper() {
        return new JokeDataEntityDomainModelMapper();
    }

    @Provides
    @Singleton
    JokeDomainModelViewModelConverter providesJokeDomainModelViewModelConverter() {
        return new JokeDomainModelViewModelConverterImpl();
    }

    //////////////
    //REPOSITORY//
    /////////////

    @Provides
    @Singleton
    JokeRepository provideJokeRepository(final JokeApiDataStore jokeApiDataStore,
                                         final JokeDBDataStore jokeDBDataStore,
                                         final JokeDataEntityDomainModelMapper jokeDataEntityDomainModelMapper, @ForApplication Context context) {
        return new JokeRepositoryImpl(jokeApiDataStore, jokeDBDataStore, jokeDataEntityDomainModelMapper, context);
    }
}

package hr.pyro.chuckjokes.presentation.di.component.application;

import android.content.Context;

import hr.pyro.chuckjokes.data.entity.mapper.JokeDataEntityDomainModelMapper;
import hr.pyro.chuckjokes.data.repository.datasource.JokeApiDataStore;
import hr.pyro.chuckjokes.data.repository.datasource.JokeDBDataStore;
import hr.pyro.chuckjokes.domain.interactor.AddJokeToFavoritesUseCase;
import hr.pyro.chuckjokes.domain.interactor.DeleteJokeUseCase;
import hr.pyro.chuckjokes.domain.interactor.FetchFavoriteJokesUseCase;
import hr.pyro.chuckjokes.domain.interactor.FetchRandomJokeUseCase;
import hr.pyro.chuckjokes.presentation.di.qualifier.ForApplication;
import hr.pyro.chuckjokes.domain.repository.JokeRepository;
import hr.pyro.chuckjokes.presentation.model.converter.JokeDomainModelViewModelConverter;
import hr.pyro.chuckjokes.presentation.util.ErrorMessageFactory;

/**
 * Created on 02.03.16..
 */
public interface ApplicationComponentExposes {

    @ForApplication
    Context provideApplicationContext();

    ErrorMessageFactory provideErrorMessageFactory();

    //////////////
    //DATA STORE//
    //////////////

    JokeApiDataStore provideJokeApiDataStore();

    JokeDBDataStore provideJokeDbDataStore();

    ///////////////
    //DATA MAPPER//
    ///////////////

    JokeDataEntityDomainModelMapper provideJokeEntityDataMapper();

    JokeDomainModelViewModelConverter providesJokeDomainModelViewModelConverter();

    //////////////
    //REPOSITORY//
    /////////////

    JokeRepository provideJokeRepository();

    ////////////
    //USE CASE//
    ///////////

    AddJokeToFavoritesUseCase provideAddJokeToFavoritesUseCase();

    DeleteJokeUseCase provideDeleteJokeUseCase();

    FetchFavoriteJokesUseCase provideFetchFavoriteJokesUseCase();

    FetchRandomJokeUseCase provideFetchRandomJokeUseCase();

}

package hr.pyro.chuckjokes.presentation.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import hr.pyro.chuckjokes.presentation.di.qualifier.ForActivity;
import hr.pyro.chuckjokes.domain.interactor.FetchFavoriteJokesUseCase;
import hr.pyro.chuckjokes.presentation.model.converter.JokeDomainModelViewModelConverter;
import hr.pyro.chuckjokes.presentation.presenter.JokeListActivityPresenter;
import hr.pyro.chuckjokes.presentation.presenter.JokeListActivityPresenterImpl;
import hr.pyro.chuckjokes.presentation.router.Router;
import hr.pyro.chuckjokes.presentation.util.ErrorMessageFactory;
import hr.pyro.chuckjokes.presentation.view.adapter.FavoriteJokesListAdapter;

/**
 * Created on 02.03.16..
 */

@Module
public final class JokeListActivityModule {


    @Provides
    JokeListActivityPresenter providePresenter(final ErrorMessageFactory errorMessageFactory,
                                               final @ForActivity Router router,
                                               final JokeDomainModelViewModelConverter converter,
                                           final FetchFavoriteJokesUseCase fetchFavoriteJokesUseCase){
        return new JokeListActivityPresenterImpl(errorMessageFactory, router, converter, fetchFavoriteJokesUseCase);
    }

    @Provides
    FavoriteJokesListAdapter provideFavoriteJokesListAdapter(final @ForActivity Context context,
                                                             final JokeListActivityPresenter jokeListActivityPresenter){
        return new FavoriteJokesListAdapter(context, jokeListActivityPresenter);
    }
}

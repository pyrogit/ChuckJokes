package hr.pyro.chuckjokes.presentation.di.module;

import dagger.Module;
import dagger.Provides;
import hr.pyro.chuckjokes.domain.interactor.AddJokeToFavoritesUseCase;
import hr.pyro.chuckjokes.domain.interactor.DeleteJokeUseCase;
import hr.pyro.chuckjokes.domain.interactor.FetchRandomJokeUseCase;
import hr.pyro.chuckjokes.presentation.di.qualifier.ForActivity;
import hr.pyro.chuckjokes.presentation.model.converter.JokeDomainModelViewModelConverter;
import hr.pyro.chuckjokes.presentation.presenter.RandomJokePresenter;
import hr.pyro.chuckjokes.presentation.presenter.RandomJokePresenterImpl;
import hr.pyro.chuckjokes.presentation.router.Router;
import hr.pyro.chuckjokes.presentation.util.ErrorMessageFactory;

/**
 * Created on 02.03.16..
 */

@Module
public final class RandomJokeActivityModule {

    @Provides
    RandomJokePresenter providePresenter(final ErrorMessageFactory errorMessageFactory,
                                         final @ForActivity Router router,
                                         final JokeDomainModelViewModelConverter converter,
                                         final FetchRandomJokeUseCase fetchRandomJokeUseCase,
                                         final DeleteJokeUseCase deleteJokeUseCase,
                                         final AddJokeToFavoritesUseCase addJokeToFavoritesUseCase) {
        return new RandomJokePresenterImpl(errorMessageFactory, router, converter,
                addJokeToFavoritesUseCase, deleteJokeUseCase, fetchRandomJokeUseCase);
    }
}

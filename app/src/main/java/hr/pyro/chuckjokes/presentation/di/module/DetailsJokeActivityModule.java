package hr.pyro.chuckjokes.presentation.di.module;

import dagger.Module;
import dagger.Provides;
import hr.pyro.chuckjokes.domain.interactor.AddJokeToFavoritesUseCase;
import hr.pyro.chuckjokes.domain.interactor.DeleteJokeUseCase;
import hr.pyro.chuckjokes.domain.interactor.FetchJokeUseCase;
import hr.pyro.chuckjokes.presentation.model.converter.JokeDomainModelViewModelConverter;
import hr.pyro.chuckjokes.presentation.presenter.DetailsJokePresenter;
import hr.pyro.chuckjokes.presentation.presenter.DetailsJokePresenterImpl;
import hr.pyro.chuckjokes.presentation.util.ErrorMessageFactory;

/**
 * Created on 02.03.16..
 */

@Module
public final class DetailsJokeActivityModule {

    @Provides
    DetailsJokePresenter providePresenter(final ErrorMessageFactory errorMessageFactory,
                                          final JokeDomainModelViewModelConverter converter,
                                         final FetchJokeUseCase fetchJokeUseCase,
                                         final DeleteJokeUseCase deleteJokeUseCase,
                                         final AddJokeToFavoritesUseCase addJokeToFavoritesUseCase){
        return new DetailsJokePresenterImpl(errorMessageFactory, addJokeToFavoritesUseCase,
                deleteJokeUseCase, fetchJokeUseCase, converter);
    }
}

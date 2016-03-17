package hr.pyro.chuckjokes.presentation.presenter;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

import hr.pyro.chuckjokes.domain.interactor.AddJokeToFavoritesUseCase;
import hr.pyro.chuckjokes.domain.interactor.DeleteJokeUseCase;
import hr.pyro.chuckjokes.domain.interactor.FetchRandomJokeUseCase;
import hr.pyro.chuckjokes.presentation.di.qualifier.ForActivity;
import hr.pyro.chuckjokes.presentation.model.JokeViewModel;
import hr.pyro.chuckjokes.presentation.model.converter.JokeDomainModelViewModelConverter;
import hr.pyro.chuckjokes.presentation.router.Router;
import hr.pyro.chuckjokes.presentation.util.ErrorMessageFactory;
import hr.pyro.chuckjokes.presentation.view.RandomJokeView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created on 11.03.16..
 */
public final class RandomJokePresenterImpl extends BasePresenter implements RandomJokePresenter {

    private WeakReference<RandomJokeView> view;
    private final AddJokeToFavoritesUseCase addJokeToFavoritesUseCase;
    private final DeleteJokeUseCase deleteJokeUseCase;
    private final FetchRandomJokeUseCase fetchRandomJokeUseCase;
    private final Router router;
    private final JokeDomainModelViewModelConverter converter;

    @Inject
    public RandomJokePresenterImpl(final ErrorMessageFactory errorMessageFactory,
                                   final @ForActivity Router router,
                                   final JokeDomainModelViewModelConverter converter,
                                   final AddJokeToFavoritesUseCase addJokeToFavoritesUseCase,
                                   final DeleteJokeUseCase deleteJokeUseCase,
                                   final FetchRandomJokeUseCase fetchRandomJokeUseCase) {
        super(errorMessageFactory);
        this.addJokeToFavoritesUseCase = addJokeToFavoritesUseCase;
        this.deleteJokeUseCase = deleteJokeUseCase;
        this.fetchRandomJokeUseCase = fetchRandomJokeUseCase;
        this.router = router;
        this.converter = converter;
    }

    @Override
    public void requestRandomJoke() {
        showLoadingView();
        addSubscription(Observable.defer(() ->
                fetchRandomJokeUseCase.execute())
                .map(converter::domainModelToViewModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::renderJoke,
                        this::responseError,
                        this::responseCompleted
                ));
    }

    private void renderJoke(@NonNull final JokeViewModel joke) {
        final RandomJokeView view = getView();
        if (view != null && !JokeViewModel.EMPTY.equals(joke)) {
            view.renderJoke(joke);
        }
    }

    @Override
    public void addToFavorites(@NonNull final JokeViewModel joke) {
        if (!JokeViewModel.EMPTY.equals(joke)) {
            addSubscription(Observable.defer(() ->
                    addJokeToFavoritesUseCase.execute(
                            converter.viewModelToDomainModel(joke)))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            this::addedToFavorites,
                            this::responseError,
                            this::responseCompleted
                    ));
        }
    }

    @Override
    public void removeFromFavorites(@NonNull final JokeViewModel joke) {
        if (!JokeViewModel.EMPTY.equals(joke)) {
            addSubscription(Observable.defer(() ->
                    deleteJokeUseCase.execute(
                            converter.viewModelToDomainModel(joke)))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            this::removedFromFavorites,
                            this::responseError,
                            this::responseCompleted
                    ));
        }
    }

    @Override
    public void showFavoriteJokes() {
        router.showFavoriteJokes();
    }

    @Override
    public void activate(final RandomJokeView view) {
        this.view = new WeakReference<>(view);
    }

    private void addedToFavorites(final Long id) {
        final RandomJokeView view = getView();
        if (view != null) {
            view.renderFavorite(true);
        }
    }

    private void removedFromFavorites(final Integer affected) {
        final RandomJokeView view = getView();
        if (view != null) {
            view.renderFavorite(false);
        }
    }

    @Override
    protected RandomJokeView getView() {
        return view != null ? view.get() : null;
    }
}

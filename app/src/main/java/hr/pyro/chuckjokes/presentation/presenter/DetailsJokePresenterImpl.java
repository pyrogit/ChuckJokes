package hr.pyro.chuckjokes.presentation.presenter;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

import hr.pyro.chuckjokes.domain.interactor.AddJokeToFavoritesUseCase;
import hr.pyro.chuckjokes.domain.interactor.DeleteJokeUseCase;
import hr.pyro.chuckjokes.domain.interactor.FetchJokeUseCase;
import hr.pyro.chuckjokes.domain.model.JokeDomainModel;
import hr.pyro.chuckjokes.presentation.model.JokeViewModel;
import hr.pyro.chuckjokes.presentation.model.converter.JokeDomainModelViewModelConverter;
import hr.pyro.chuckjokes.presentation.util.ErrorMessageFactory;
import hr.pyro.chuckjokes.presentation.view.DetailsJokeView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created on 11.03.16..
 */
public final class DetailsJokePresenterImpl extends BasePresenter implements DetailsJokePresenter {

    private WeakReference<DetailsJokeView> view;
    private final AddJokeToFavoritesUseCase addJokeToFavoritesUseCase;
    private final DeleteJokeUseCase deleteJokeUseCase;
    private final FetchJokeUseCase fetchJokeUseCase;
    private final JokeDomainModelViewModelConverter converter;

    @Inject
    public DetailsJokePresenterImpl(final ErrorMessageFactory errorMessageFactory,
                                    final AddJokeToFavoritesUseCase addJokeToFavoritesUseCase,
                                    final DeleteJokeUseCase deleteJokeUseCase,
                                    final FetchJokeUseCase fetchJokeUseCase,
                                    final JokeDomainModelViewModelConverter converter) {
        super(errorMessageFactory);
        this.addJokeToFavoritesUseCase = addJokeToFavoritesUseCase;
        this.deleteJokeUseCase = deleteJokeUseCase;
        this.fetchJokeUseCase = fetchJokeUseCase;
        this.converter = converter;
    }

    @Override
    public void requestJoke(@NonNull final int id) {
        addSubscription(Observable.defer(() ->
                fetchJokeUseCase.execute(Integer.valueOf(id)))
                .map(converter::domainModelToViewModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::renderJoke,
                        this::responseError,
                        this::responseCompleted
                ));
    }

    @Override
    public void activate(final DetailsJokeView view) {
        this.view = new WeakReference<>(view);
    }

    private void renderJoke(@NonNull final JokeViewModel joke) {
        final DetailsJokeView view = getView();
        if (view != null && !JokeViewModel.EMPTY.equals(joke)) {
            view.renderJoke(joke);
        }
    }

    @Override
    public void addToFavorites(@NonNull final JokeViewModel joke) {
        if (!JokeDomainModel.EMPTY.equals(joke)) {
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
        if (!JokeDomainModel.EMPTY.equals(joke)) {
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

    private void addedToFavorites(final Long id) {
        final DetailsJokeView view = getView();
        if (view != null) {
            view.renderFavorite(true);
        }
    }

    private void removedFromFavorites(final Integer affected) {
        final DetailsJokeView view = getView();
        if (view != null) {
            view.renderFavorite(false);
        }
    }

    @Override
    protected DetailsJokeView getView() {
        return view != null ? view.get() : null;
    }
}

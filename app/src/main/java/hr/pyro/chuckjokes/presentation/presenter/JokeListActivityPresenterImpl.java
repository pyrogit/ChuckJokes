package hr.pyro.chuckjokes.presentation.presenter;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

import hr.pyro.chuckjokes.domain.interactor.FetchFavoriteJokesUseCase;
import hr.pyro.chuckjokes.presentation.di.qualifier.ForActivity;
import hr.pyro.chuckjokes.presentation.model.JokeViewModel;
import hr.pyro.chuckjokes.presentation.model.converter.JokeDomainModelViewModelConverter;
import hr.pyro.chuckjokes.presentation.router.Router;
import hr.pyro.chuckjokes.presentation.util.ErrorMessageFactory;
import hr.pyro.chuckjokes.presentation.view.JokeListView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created on 11.03.16..
 */
public final class JokeListActivityPresenterImpl extends BasePresenter implements JokeListActivityPresenter {

    private WeakReference<JokeListView> view;
    private final FetchFavoriteJokesUseCase fetchFavoriteJokesUseCase;
    private final Router router;
    private final JokeDomainModelViewModelConverter converter;

    @Inject
    public JokeListActivityPresenterImpl(final ErrorMessageFactory errorMessageFactory,
                                         final @ForActivity Router router,
                                         final JokeDomainModelViewModelConverter converter,
                                         final FetchFavoriteJokesUseCase fetchFavoriteJokesUseCase) {
        super(errorMessageFactory);
        this.fetchFavoriteJokesUseCase = fetchFavoriteJokesUseCase;
        this.router = router;
        this.converter = converter;
    }

    @Override
    protected JokeListView getView() {
        return view != null ? view.get() : null;
    }

    @Override
    public void requestFavoriteJokes() {
        addSubscription(Observable.defer(() ->
                fetchFavoriteJokesUseCase.execute())
                .map(converter::domainModelToViewModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::renderFavoriteJokes,
                        this::responseError,
                        this::responseCompleted
                ));
    }

    @Override
    public void showDetailsJoke(final int id) {
        router.showJokeDetails(id);
    }

    @Override
    public void activate(final JokeListView view) {
        this.view = new WeakReference<>(view);
    }

    private void renderFavoriteJokes(final List<JokeViewModel> jokes) {
        final JokeListView view = getView();
        if (view != null && !jokes.isEmpty()) {
            view.renderFavoriteJokes(jokes);
        }
    }
}

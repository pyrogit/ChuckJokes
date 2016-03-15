package hr.pyro.chuckjokes.presentation.presenter;

import hr.pyro.chuckjokes.presentation.model.JokeViewModel;
import hr.pyro.chuckjokes.presentation.view.DetailsJokeView;

/**
 * Created on 11.03.16..
 */
public interface DetailsJokePresenter extends ScopedPresenter {

    void requestJoke(int id);

    void addToFavorites(JokeViewModel joke);

    void removeFromFavorites(JokeViewModel joke);

    void activate(DetailsJokeView view);
}

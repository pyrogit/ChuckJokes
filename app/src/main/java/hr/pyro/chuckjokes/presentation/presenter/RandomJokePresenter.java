package hr.pyro.chuckjokes.presentation.presenter;

import hr.pyro.chuckjokes.presentation.model.JokeViewModel;
import hr.pyro.chuckjokes.presentation.view.RandomJokeView;

/**
 * Created on 11.03.16..
 */
public interface RandomJokePresenter extends ScopedPresenter {

    void requestRandomJoke();

    void addToFavorites(JokeViewModel joke);

    void removeFromFavorites(JokeViewModel joke);

    void showFavoriteJokes();

    void activate(RandomJokeView view);
}

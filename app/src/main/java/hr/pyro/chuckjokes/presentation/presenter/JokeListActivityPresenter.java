package hr.pyro.chuckjokes.presentation.presenter;

import hr.pyro.chuckjokes.presentation.view.JokeListView;

/**
 * Created on 11.03.16..
 */
public interface JokeListActivityPresenter extends ScopedPresenter {

    void requestFavoriteJokes();

    void showDetailsJoke(int id);

    void activate(JokeListView view);
}

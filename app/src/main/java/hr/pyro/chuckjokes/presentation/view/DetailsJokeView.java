package hr.pyro.chuckjokes.presentation.view;

import hr.pyro.chuckjokes.presentation.model.JokeViewModel;

/**
 * Created on 11.03.16..
 */
public interface DetailsJokeView extends BaseView {

    void renderJoke(JokeViewModel joke);

    void renderFavorite(boolean isFavorite);

}

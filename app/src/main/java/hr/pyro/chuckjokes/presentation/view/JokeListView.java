package hr.pyro.chuckjokes.presentation.view;

import java.util.List;

import hr.pyro.chuckjokes.presentation.model.JokeViewModel;

/**
 * Created on 11.03.16..
 */
public interface JokeListView extends BaseView {

    void renderFavoriteJokes(List<JokeViewModel> jokes);
}

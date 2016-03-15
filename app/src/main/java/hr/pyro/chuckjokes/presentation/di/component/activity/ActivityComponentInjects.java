package hr.pyro.chuckjokes.presentation.di.component.activity;


import hr.pyro.chuckjokes.presentation.view.activity.FavoriteJokesActivity;
import hr.pyro.chuckjokes.presentation.view.activity.JokeDetailsActivity;
import hr.pyro.chuckjokes.presentation.view.activity.RandomJokeActivity;

public interface ActivityComponentInjects {

//    void inject(MainActivity mainActivity);

    void inject(JokeDetailsActivity jokeDetailsActivity);

    void inject(FavoriteJokesActivity activity);

    void inject(RandomJokeActivity activity);

}

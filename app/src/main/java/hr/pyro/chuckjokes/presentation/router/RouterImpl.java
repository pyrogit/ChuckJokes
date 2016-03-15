package hr.pyro.chuckjokes.presentation.router;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import hr.pyro.chuckjokes.presentation.di.qualifier.ForActivity;
import hr.pyro.chuckjokes.presentation.view.activity.FavoriteJokesActivity;
import hr.pyro.chuckjokes.presentation.view.activity.JokeDetailsActivity;

/**
 * Created on 13.03.16..
 */
public class RouterImpl implements Router {

    private final Context context;

    @Inject
    public RouterImpl(@ForActivity Context context) {
        this.context = context;
    }

    @Override
    public void showFavoriteJokes() {
        final Intent intent = FavoriteJokesActivity.createIntent(context);
        context.startActivity(intent);
    }

    @Override
    public void showJokeDetails(final int jokeId) {
        final Intent intent = JokeDetailsActivity.createIntent(context, jokeId);
        context.startActivity(intent);
    }
}

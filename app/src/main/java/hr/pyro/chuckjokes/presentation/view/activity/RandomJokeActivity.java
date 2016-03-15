package hr.pyro.chuckjokes.presentation.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.pyro.chuckjokes.presentation.R;
import hr.pyro.chuckjokes.presentation.di.component.ComponentFactory;
import hr.pyro.chuckjokes.presentation.model.JokeViewModel;
import hr.pyro.chuckjokes.presentation.presenter.RandomJokePresenter;
import hr.pyro.chuckjokes.presentation.view.RandomJokeView;

/**
 * Created on 11.03.16..
 */
public final class RandomJokeActivity extends InjectorActivity implements RandomJokeView {

    private final static String TAG = RandomJokeActivity.class.getName();

    @Inject
    RandomJokePresenter presenter;

    @Bind(R.id.random_joke_favorite)
    ImageView favoriteButton;

    @Bind(R.id.random_joke_text)
    TextView jokeText;

    @Bind(R.id.progress_bar)
    ProgressBar progressBar;

    private JokeViewModel joke = JokeViewModel.EMPTY;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_joke);
        ButterKnife.bind(this);
        presenter.activate(this);
        presenter.requestRandomJoke();
    }

    @Override
    protected void inject() {
        ComponentFactory.createActivityComponent(this).inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.deactivate();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.random_joke_random_button)
    public void randomJokeButtonClicked() {
        presenter.requestRandomJoke();
    }

    @OnClick(R.id.random_joke_favorite)
    public void favoriteIconClicked() {
        if (joke.isFavorite()) {
            presenter.removeFromFavorites(joke);
        } else {
            presenter.addToFavorites(joke);
        }
    }

    @OnClick(R.id.random_joke_my_favorites)
    public void myFavoritesClicked() {
        presenter.showFavoriteJokes();
    }

    @Override
    public void renderJoke(final JokeViewModel joke) {
        this.joke = joke;
        jokeText.setText(joke.getJoke());
        toggleFavorite(joke.isFavorite());
    }

    private void toggleFavorite(final boolean isFavorite) {
        if (isFavorite) {
            favoriteButton.setImageResource(R.drawable.ic_favourite_positive);
        } else {
            favoriteButton.setImageResource(R.drawable.ic_favourite_negative);
        }
    }

    @Override
    public void renderFavorite(final boolean isFavorite) {
        joke.setIsFavorite(isFavorite);
        toggleFavorite(isFavorite);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);//TODO fix progressbar
        Log.d("PROGRESS", "VISIBLE");
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String errorMessage) {
        Log.e(TAG, errorMessage);
    }
}

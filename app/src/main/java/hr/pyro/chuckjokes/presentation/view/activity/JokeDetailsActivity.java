package hr.pyro.chuckjokes.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import hr.pyro.chuckjokes.presentation.presenter.DetailsJokePresenter;
import hr.pyro.chuckjokes.presentation.view.DetailsJokeView;

/**
 * Created on 13.03.16..
 */
public final class JokeDetailsActivity extends InjectorActivity implements DetailsJokeView{

    public final static int FAVORITE_TOOGLED = 8888;

    private final static String TAG = JokeDetailsActivity.class.getName();
    private static String JOKE_ID_EXTRA = "jokeid";

    public static Intent createIntent(final Context context, final int jokeId){
        final Intent intent = new Intent(context, JokeDetailsActivity.class);
        intent.putExtra(JOKE_ID_EXTRA, jokeId);
        return intent;
    }

    @Inject
    DetailsJokePresenter presenter;

    @Bind(R.id.random_joke_favorite)
    ImageView favoriteButton;

    @Bind(R.id.random_joke_text)
    TextView jokeText;

    @Bind(R.id.progress_bar)
    ProgressBar progressBar;

    private JokeViewModel joke = JokeViewModel.EMPTY;
    private boolean isFavoriteToggled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_details);
        ButterKnife.bind(this);
        presenter.activate(this);
        presenter.requestJoke(getIntent().getIntExtra(JOKE_ID_EXTRA, JokeViewModel.EMPTY.getId()));

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

    @Override
    public void onBackPressed() {
        if(isFavoriteToggled){
            setResult(FAVORITE_TOOGLED);
        }
        super.onBackPressed();
    }

    @OnClick(R.id.random_joke_favorite)
    public void favoriteIconClicked(){
        if(joke.isFavorite()){
            presenter.removeFromFavorites(joke);
        } else {
            presenter.addToFavorites(joke);
        }
    }

    @Override
    public void renderJoke(JokeViewModel joke) {
        this.joke = joke;
        jokeText.setText(joke.getJoke());
        toggleFavorite(joke.isFavorite());
    }

    private void toggleFavorite(final boolean isFavorite){
        if(isFavorite){
            favoriteButton.setImageResource(R.drawable.ic_favourite_positive);
        } else {
            favoriteButton.setImageResource(R.drawable.ic_favourite_negative);
        }
    }

    @Override
    public void renderFavorite(final boolean isFavorite) {
        joke.setIsFavorite(isFavorite);
        isFavoriteToggled = !isFavoriteToggled;
        toggleFavorite(isFavorite);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String errorMessage) {
        Log.e(TAG, errorMessage);
    }
}

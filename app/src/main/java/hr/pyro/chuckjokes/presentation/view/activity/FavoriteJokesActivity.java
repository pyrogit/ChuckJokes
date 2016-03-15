package hr.pyro.chuckjokes.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import hr.pyro.chuckjokes.presentation.R;
import hr.pyro.chuckjokes.presentation.di.component.ComponentFactory;
import hr.pyro.chuckjokes.presentation.model.JokeViewModel;
import hr.pyro.chuckjokes.presentation.presenter.JokeListActivityPresenter;
import hr.pyro.chuckjokes.presentation.view.JokeListView;
import hr.pyro.chuckjokes.presentation.view.adapter.FavoriteJokesListAdapter;

/**
 * Created on 11.03.16..
 */
public final class FavoriteJokesActivity extends InjectorActivity implements JokeListView{

    private final static String TAG = FavoriteJokesActivity.class.getName();

    public static Intent createIntent(final Context context){
        return new Intent(context, FavoriteJokesActivity.class);
    }

    @Inject
    JokeListActivityPresenter presenter;

    @Inject
    FavoriteJokesListAdapter adapter;

    @Bind(R.id.favorite_jokes_listview)
    protected ListView favoriteListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_jokes);
        ButterKnife.bind(this);
        presenter.activate(this);
        presenter.requestFavoriteJokes();
        favoriteListView.setAdapter(adapter);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == JokeDetailsActivity.FAVORITE_TOOGLED) {
            presenter.requestFavoriteJokes();
        }
    }

    @Override
    public void renderFavoriteJokes(final List<JokeViewModel> jokes) {
        adapter.setUpFavorites(jokes);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(final String errorMessage) {
        Log.e(TAG, errorMessage);
    }
}

package hr.pyro.chuckjokes.presentation.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.pyro.chuckjokes.presentation.R;
import hr.pyro.chuckjokes.presentation.di.qualifier.ForActivity;
import hr.pyro.chuckjokes.presentation.model.JokeViewModel;
import hr.pyro.chuckjokes.presentation.presenter.JokeListActivityPresenter;

public final class FavoriteJokesListAdapter extends ArrayAdapter<JokeViewModel> {

    private final JokeListActivityPresenter presenter;

    @Inject
    public FavoriteJokesListAdapter(@ForActivity final Context context, final JokeListActivityPresenter presenter) {
        super(context, R.layout.item_favorite_joke);
        this.presenter = presenter;
    }

    public void setUpFavorites(final List<JokeViewModel> jokes) {
        clear();
        addAll(jokes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_favorite_joke, parent, false);
            viewHolder = new ViewHolder(convertView, presenter);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.fillViews(getItem(position));

        return convertView;
    }

    protected static class ViewHolder {

        @Bind(R.id.joke_text_item_favorite_joke)
        protected TextView jokeTextView;
        private JokeViewModel joke;

        private final WeakReference<JokeListActivityPresenter> presenterWeakReference;

        ViewHolder(final View view, final JokeListActivityPresenter presenter) {
            ButterKnife.bind(this, view);
            presenterWeakReference = new WeakReference<>(presenter);
        }

        void fillViews(final JokeViewModel joke) {
            jokeTextView.setText(joke.getJoke());
            this.joke = joke;
        }

        @OnClick(R.id.item_favorite_joke)
        void itemClicked() {
            final JokeListActivityPresenter presenter = presenterWeakReference.get();
            if (presenter != null) {
                presenter.showDetailsJoke(joke.getId());
            }
        }

    }
}

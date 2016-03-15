package hr.pyro.chuckjokes.data.entity;

import com.hannesdorfmann.sqlbrite.objectmapper.annotation.Column;
import com.hannesdorfmann.sqlbrite.objectmapper.annotation.ObjectMappable;

/**
 * Created on 09.03.16..
 */
@ObjectMappable
public final class JokeEntity {
    public static final String TABLE_NAME = "FavoriteChuckJokes";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_JOKE = "joke";
    public static final String COLUMN_FAVORITE = "favorite";

    @Column(COLUMN_ID)
    private int id;
    @Column(COLUMN_JOKE)
    private String joke;
    @Column(COLUMN_FAVORITE)
    private boolean favorite;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}

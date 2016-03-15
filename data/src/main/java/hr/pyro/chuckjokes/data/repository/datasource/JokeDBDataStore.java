package hr.pyro.chuckjokes.data.repository.datasource;

import java.util.List;

import hr.pyro.chuckjokes.data.entity.JokeEntity;
import rx.Observable;

/**
 * Created on 09.03.16..
 */
public interface JokeDBDataStore {

    Observable<List<JokeEntity>> getFavoriteJokes();

    Observable<Long> insertJoke(int id, String joke, boolean isFavorite);

    Observable<Integer> deleteJoke(int id);

    Observable<List<JokeEntity>> getAllJokes();

    Observable<JokeEntity> getJoke(int id);

}

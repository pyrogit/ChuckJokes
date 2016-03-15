package hr.pyro.chuckjokes.data.repository.datasource;

import hr.pyro.chuckjokes.data.entity.JokeApiEntity;
import rx.Observable;

/**
 * Created on 09.03.16..
 */
public interface JokeApiDataStore {

    Observable<JokeApiEntity> getRandomJoke();
}

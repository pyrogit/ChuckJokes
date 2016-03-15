package hr.pyro.chuckjokes.domain.repository;


import java.util.List;

import hr.pyro.chuckjokes.domain.model.JokeDomainModel;
import rx.Observable;

/**
 * Created on 09.03.16..
 */
public interface JokeRepository {

    Observable<JokeDomainModel> fetchRandomJoke();

    Observable<List<JokeDomainModel>> fetchFavoriteJokes();

    Observable<Long> addJoke(JokeDomainModel jokeDomainModel);

    Observable<Integer> deleteJoke(JokeDomainModel jokeDomainModel);

    Observable<JokeDomainModel> fetchJoke(int id);
}

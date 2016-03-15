package hr.pyro.chuckjokes.data.repository;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import hr.pyro.chuckjokes.data.entity.mapper.JokeDataEntityDomainModelMapper;
import hr.pyro.chuckjokes.data.repository.datasource.JokeApiDataStore;
import hr.pyro.chuckjokes.data.repository.datasource.JokeDBDataStore;
import hr.pyro.chuckjokes.domain.model.JokeDomainModel;
import hr.pyro.chuckjokes.domain.repository.JokeRepository;
import rx.Observable;

/**
 * Created on 10.03.16..
 */
public final class JokeRepositoryImpl implements JokeRepository {

    private final JokeApiDataStore jokeApiDataStore;
    private final JokeDBDataStore jokeDBDataStore;
    private final JokeDataEntityDomainModelMapper jokeDataEntityDomainModelMapper;
    private final Context context;

    @Inject
    public JokeRepositoryImpl(JokeApiDataStore jokeApiDataStore, JokeDBDataStore jokeDBDataStore, JokeDataEntityDomainModelMapper jokeDataEntityDomainModelMapper, Context context) {
        this.jokeApiDataStore = jokeApiDataStore;
        this.jokeDBDataStore = jokeDBDataStore;
        this.jokeDataEntityDomainModelMapper = jokeDataEntityDomainModelMapper;
        this.context = context;
    }

    @Override
    public Observable<JokeDomainModel> fetchRandomJoke() {
        return jokeApiDataStore.getRandomJoke().map(entity ->
                jokeDataEntityDomainModelMapper.transformFromApiEntityToModel(entity));
    }

    @Override
    public Observable<List<JokeDomainModel>> fetchFavoriteJokes() {
        return jokeDBDataStore.getFavoriteJokes().map(entities ->
                jokeDataEntityDomainModelMapper.transformFromEntityToModel(entities));
    }

    @Override
    public Observable<Long> addJoke(JokeDomainModel jokeDomainModel) {
        return jokeDBDataStore.insertJoke(jokeDomainModel.id, jokeDomainModel.joke, true);//TODO
    }

    @Override
    public Observable<Integer> deleteJoke(JokeDomainModel jokeDomainModel) {
        return jokeDBDataStore.deleteJoke(jokeDomainModel.id);
    }

    @Override
    public Observable<JokeDomainModel> fetchJoke(final int id) {
        return jokeDBDataStore.getJoke(id).map(entity ->
                jokeDataEntityDomainModelMapper.transformFromEntityToModel(entity));
    }
}

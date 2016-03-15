package hr.pyro.chuckjokes.data.repository.datasource;

import hr.pyro.chuckjokes.data.Constants;
import hr.pyro.chuckjokes.data.entity.JokeApiEntity;
import hr.pyro.chuckjokes.data.repository.datasource.services.JokeServices;
import retrofit.RestAdapter;
import rx.Observable;

/**
 * Created on 09.03.16..
 */
public final class JokeApiDataStoreImpl implements JokeApiDataStore {

    private final JokeServices jokeServices;

    public JokeApiDataStoreImpl(){
        final RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(Constants.JOKE_API_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL).build();
        jokeServices = restAdapter.create(JokeServices.class);
    }

    @Override
    public Observable<JokeApiEntity> getRandomJoke() {
        return jokeServices.getRandomJoke();
    }
}

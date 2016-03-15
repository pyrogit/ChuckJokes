package hr.pyro.chuckjokes.data.repository.datasource.services;


import hr.pyro.chuckjokes.data.entity.JokeApiEntity;
import retrofit.Callback;
import retrofit.http.GET;
import rx.Observable;

/**
 * Created on 09.03.16..
 */
public interface JokeServices {

    @GET("/random")
    Observable<JokeApiEntity> getRandomJoke();

    @GET("/random")
    void getRandomJoke(Callback<JokeApiEntity> callback);
}

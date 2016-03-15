package hr.pyro.chuckjokes.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import hr.pyro.chuckjokes.domain.model.JokeDomainModel;
import hr.pyro.chuckjokes.domain.repository.JokeRepository;
import rx.Observable;

/**
 * Created on 11.03.16..
 */
public final class FetchFavoriteJokesUseCase implements UseCase<List<JokeDomainModel>> {

    private final JokeRepository jokeRepository;

    @Inject
    public FetchFavoriteJokesUseCase(JokeRepository jokeRepository) {
        this.jokeRepository = jokeRepository;
    }

    @Override
    public Observable<List<JokeDomainModel>> execute() {
        return jokeRepository.fetchFavoriteJokes();
    }
}

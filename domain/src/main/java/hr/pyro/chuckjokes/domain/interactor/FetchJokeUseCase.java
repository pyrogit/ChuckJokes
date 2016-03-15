package hr.pyro.chuckjokes.domain.interactor;

import javax.inject.Inject;

import hr.pyro.chuckjokes.domain.model.JokeDomainModel;
import hr.pyro.chuckjokes.domain.repository.JokeRepository;
import rx.Observable;

/**
 * Created on 11.03.16..
 */
public final class FetchJokeUseCase implements UseCaseWithParameter<JokeDomainModel, Integer> {

    private final JokeRepository jokeRepository;

    @Inject
    public FetchJokeUseCase(JokeRepository jokeRepository) {
        this.jokeRepository = jokeRepository;
    }

    @Override
    public Observable<JokeDomainModel> execute(final Integer id) {
        return this.jokeRepository.fetchJoke(id);
    }
}

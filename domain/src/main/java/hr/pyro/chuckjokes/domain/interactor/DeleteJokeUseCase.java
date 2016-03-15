package hr.pyro.chuckjokes.domain.interactor;

import javax.inject.Inject;

import hr.pyro.chuckjokes.domain.model.JokeDomainModel;
import hr.pyro.chuckjokes.domain.repository.JokeRepository;
import rx.Observable;

/**
 * Created on 11.03.16..
 */
public final class DeleteJokeUseCase implements UseCaseWithParameter<Integer, JokeDomainModel> {

    private final JokeRepository jokeRepository;

    @Inject
    public DeleteJokeUseCase(JokeRepository jokeRepository) {
        this.jokeRepository = jokeRepository;
    }

    @Override
    public Observable<Integer> execute(final JokeDomainModel jokeDomainModel) {
        return this.jokeRepository.deleteJoke(jokeDomainModel);
    }
}

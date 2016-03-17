package hr.pyro.chuckjokes.domain.interactor;

import javax.inject.Inject;

import hr.pyro.chuckjokes.domain.model.JokeDomainModel;
import hr.pyro.chuckjokes.domain.repository.JokeRepository;
import rx.Observable;

/**
 * Created on 11.03.16..
 */
public final class AddJokeToFavoritesUseCase implements UseCaseWithParameter<Long, JokeDomainModel> {

    private final JokeRepository jokeRepository;

    @Inject
    public AddJokeToFavoritesUseCase(JokeRepository jokeRepository) {
        this.jokeRepository = jokeRepository;
    }

    @Override
    public Observable<Long> execute(JokeDomainModel joke) {
        return this.jokeRepository.addJokeToFavorites(joke);
    }
}

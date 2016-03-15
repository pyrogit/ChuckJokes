package hr.pyro.chuckjokes.presentation.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hr.pyro.chuckjokes.domain.interactor.AddJokeToFavoritesUseCase;
import hr.pyro.chuckjokes.domain.interactor.DeleteJokeUseCase;
import hr.pyro.chuckjokes.domain.interactor.FetchFavoriteJokesUseCase;
import hr.pyro.chuckjokes.domain.interactor.FetchJokeUseCase;
import hr.pyro.chuckjokes.domain.interactor.FetchRandomJokeUseCase;
import hr.pyro.chuckjokes.domain.repository.JokeRepository;

/**
 * Created on 08.03.16..
 */
@Module
public final class UseCaseModule {

    @Provides
    @Singleton
    AddJokeToFavoritesUseCase provideAddJokeToFavoritesUseCase(JokeRepository jokeRepository){
        return new AddJokeToFavoritesUseCase(jokeRepository);
    }

    @Provides
    @Singleton
    DeleteJokeUseCase provideDeleteJokeUseCase(JokeRepository jokeRepository){
        return new DeleteJokeUseCase(jokeRepository);
    }

    @Provides
    @Singleton
    FetchFavoriteJokesUseCase provideFetchFavoriteJokesUseCase(JokeRepository jokeRepository){
        return new FetchFavoriteJokesUseCase(jokeRepository);
    }

    @Provides
    @Singleton
    FetchRandomJokeUseCase provideFetchRandomJokeUseCase(JokeRepository jokeRepository){
        return new FetchRandomJokeUseCase(jokeRepository);
    }

    @Provides
    @Singleton
    FetchJokeUseCase provideFetchJokeUseCase(JokeRepository jokeRepository){
        return new FetchJokeUseCase(jokeRepository);
    }
}

package hr.pyro.chuckjokes.domain.interactor;

import rx.Observable;

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 * <p>
 * By convention each UseCase implementation will return the resulting {@link Observable}.
 * <p>
 * If client of the Use Case needs blocking execution it should call toBlocking()
 * method on the resulting observable
 * <p>
 * If entity class isn't enough for a response, add UseCaseResponse POJO class that will
 * hold the data and return Observable<UseCaseResponse> from execute.
 * <p>
 * If simple parameter isn't enough for executing the Use Case, add UseCaseRequest POJO class
 * that will hold the data and pass it to the execute() method
 *
 * @param T Type of the UseCase response
 * @param U Type of the UseCase request
 */
public interface UseCaseWithParameter<T, U> {

    /**
     * Builds an {@link Observable} which will be used when executing the current {@link hr.pyro.chuckjokes.presentation.interactor.UseCaseWithParameter}.
     */
    Observable<T> execute(U parameter);
}

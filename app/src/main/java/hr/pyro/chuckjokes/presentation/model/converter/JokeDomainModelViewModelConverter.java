package hr.pyro.chuckjokes.presentation.model.converter;

import java.util.List;

import hr.pyro.chuckjokes.domain.model.JokeDomainModel;
import hr.pyro.chuckjokes.presentation.model.JokeViewModel;

/**
 * Created on 15.03.16..
 */
public interface JokeDomainModelViewModelConverter {

    JokeViewModel domainModelToViewModel(JokeDomainModel jokeDomainModel);

    JokeDomainModel viewModelToDomainModel(JokeViewModel jokeViewModel);

    List<JokeViewModel> domainModelToViewModel(List<JokeDomainModel> jokeDomainModels);
}

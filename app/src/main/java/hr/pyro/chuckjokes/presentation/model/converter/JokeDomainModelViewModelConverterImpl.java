package hr.pyro.chuckjokes.presentation.model.converter;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import java.util.List;

import hr.pyro.chuckjokes.domain.model.JokeDomainModel;
import hr.pyro.chuckjokes.presentation.model.JokeViewModel;

/**
 * Created on 15.03.16..
 */
public final class JokeDomainModelViewModelConverterImpl implements JokeDomainModelViewModelConverter {

    @Override
    public JokeViewModel domainModelToViewModel(final JokeDomainModel jokeDomainModel) {
        if (jokeDomainModel == null || JokeDomainModel.EMPTY.equals(jokeDomainModel)) {
            return JokeViewModel.EMPTY;
        }
        return new JokeViewModel(jokeDomainModel.id, jokeDomainModel.joke, jokeDomainModel.isFavorite);
    }

    @Override
    public JokeDomainModel viewModelToDomainModel(final JokeViewModel jokeViewModel) {
        if (jokeViewModel == null || JokeViewModel.EMPTY.equals(jokeViewModel)) {
            return JokeDomainModel.EMPTY;
        }
        return JokeDomainModel.create()
                .id(jokeViewModel.getId())
                .joke(jokeViewModel.getJoke())
                .favorite(jokeViewModel.isFavorite())
                .build();
    }

    @Override
    public List<JokeViewModel> domainModelToViewModel(final List<JokeDomainModel> jokeDomainModels) {
        return Stream.of(jokeDomainModels)
                .map(this::domainModelToViewModel)
                .collect(Collectors.toList());
    }
}

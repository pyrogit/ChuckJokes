package hr.pyro.chuckjokes.data.entity.mapper;

import android.text.Html;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import java.util.Collections;
import java.util.List;

import hr.pyro.chuckjokes.data.entity.JokeApiEntity;
import hr.pyro.chuckjokes.data.entity.JokeEntity;
import hr.pyro.chuckjokes.domain.model.JokeDomainModel;

/**
 * Created on 09.03.16..
 */
public final class JokeDataEntityDomainModelMapper {

    public JokeDomainModel transformFromEntityToModel(final JokeEntity entity){
        if(entity == null){
            return JokeDomainModel.EMPTY;
        }
        return JokeDomainModel.create()
                .id(entity.getId())
                .joke(entity.getJoke())
                .favorite(entity.isFavorite())
                .build();
    }


    public JokeDomainModel transformFromApiEntityToModel(final JokeApiEntity entity){
        if(entity == null){
            return JokeDomainModel.EMPTY;
        }
        return JokeDomainModel.create()
                .id(entity.getValue().getId())
                .joke(Html.fromHtml(entity.getValue().getJoke()).toString())
                .favorite(false)
                .build();
    }

    public JokeEntity transformFromModelToEntity(JokeDomainModel model){
        if(model == null || model.equals(JokeDomainModel.EMPTY)){
            return null;
        }
        JokeEntity entity = new JokeEntity();
        entity.setId(model.id);
        entity.setJoke(model.joke);
        entity.setFavorite(model.isFavorite);
        return entity;
    }

    public List<JokeDomainModel> transformFromEntityToModel(List<JokeEntity> entities){
        if(entities == null || entities.isEmpty()){
            return Collections.EMPTY_LIST;
        }
        return Stream.of(entities)
                .map(entity -> transformFromEntityToModel(entity))
                .collect(Collectors.toList());
    }
}

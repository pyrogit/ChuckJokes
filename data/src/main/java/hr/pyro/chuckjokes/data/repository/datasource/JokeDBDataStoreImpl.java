package hr.pyro.chuckjokes.data.repository.datasource;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.hannesdorfmann.sqlbrite.dao.Dao;

import java.util.List;

import hr.pyro.chuckjokes.data.entity.JokeEntity;
import hr.pyro.chuckjokes.data.entity.JokeEntityMapper;
import rx.Observable;

/**
 * Created on 09.03.16..
 */
public final class JokeDBDataStoreImpl extends Dao implements JokeDBDataStore {

    /**
     * Create here the database table for the given dao
     *
     * @param database
     */
    @Override
    public void createTable(SQLiteDatabase database) {
        CREATE_TABLE(JokeEntity.TABLE_NAME, JokeEntity.COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL",
                JokeEntity.COLUMN_JOKE + " TEXT", JokeEntity.COLUMN_FAVORITE + " BOOLEAN")
                .execute(database);
    }

    /**
     * This method will be called, if a Database has been updated and a database
     * table scheme may be needed
     *
     * @param db         the database
     * @param oldVersion old database version
     * @param newVersion new database version
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //no database upgrade
    }

    @Override
    public Observable<List<JokeEntity>> getAllJokes() {
        return query(SELECT(JokeEntity.COLUMN_ID, JokeEntity.COLUMN_JOKE, JokeEntity.COLUMN_FAVORITE)
                .FROM(JokeEntity.TABLE_NAME)).run().mapToList(JokeEntityMapper.MAPPER);
    }

    @Override
    public Observable<JokeEntity> getJoke(final int id) {
        return query(SELECT(JokeEntity.COLUMN_ID, JokeEntity.COLUMN_JOKE, JokeEntity.COLUMN_FAVORITE)
                .FROM(JokeEntity.TABLE_NAME).WHERE(JokeEntity.COLUMN_ID + " = " + id))
                .run().mapToOne(JokeEntityMapper.MAPPER);
    }

    @Override
    public Observable<List<JokeEntity>> getFavoriteJokes() {
        return query(SELECT(JokeEntity.COLUMN_ID, JokeEntity.COLUMN_JOKE, JokeEntity.COLUMN_FAVORITE)
                .FROM(JokeEntity.TABLE_NAME).WHERE(JokeEntity.COLUMN_FAVORITE + " = 1"))
                .run().mapToList(JokeEntityMapper.MAPPER);
    }

    //TODO rename metodu, pocisti malo, dodaj update, brisem i dodajem preko varijabli il entity ?
    @Override
    public Observable<Long> insertJoke(final int id, final String joke, final boolean isFavorite) {
        ContentValues values = JokeEntityMapper.contentValues()
                .id(id)
                .joke(joke)
                .favorite(isFavorite)
                .build();

        return insert(JokeEntity.TABLE_NAME, values);
    }

    @Override
    public Observable<Integer> deleteJoke(final int id) {
        return delete(JokeEntity.TABLE_NAME, JokeEntity.COLUMN_ID + " = " + id);
    }
}

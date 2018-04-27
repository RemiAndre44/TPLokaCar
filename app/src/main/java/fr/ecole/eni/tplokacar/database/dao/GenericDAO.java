package fr.ecole.eni.tplokacar.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface GenericDAO<T> {

    @Insert
    void insert(T entity);

    @Insert
    void insert(List<T> entities);

    @Delete
    void delete(T entity);

    @Update
    void update(T entity);
}

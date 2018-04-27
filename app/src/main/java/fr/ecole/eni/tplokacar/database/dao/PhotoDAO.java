package fr.ecole.eni.tplokacar.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import fr.ecole.eni.tplokacar.database.entity.Photo;

@Dao
public interface PhotoDAO extends GenericDAO<Photo> {

    @Query("Delete FROM Photo")
    void deleteAll();
}

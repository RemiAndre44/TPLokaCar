package fr.ecole.eni.tplokacar.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import fr.ecole.eni.tplokacar.database.entity.Location;

@Dao
public interface LocationDAO extends GenericDAO<Location> {

    @Query("DELETE FROM Location")
    void deleteAll();

    @Query("SELECT * FROM Location")
    List<Location> selectAll();
}

package fr.ecole.eni.tplokacar.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

import fr.ecole.eni.tplokacar.database.entity.Vehicule;

@Dao
public interface VehiculeDAO extends GenericDAO<Vehicule> {

    @Query("DELETE FROM Vehicule")
    void deleteAll();

    @Query("SELECT * FROM Vehicule")
    List<Vehicule> selectAll();
}

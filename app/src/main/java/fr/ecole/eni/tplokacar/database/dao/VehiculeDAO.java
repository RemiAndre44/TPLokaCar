package fr.ecole.eni.tplokacar.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;
import java.util.List;

import fr.ecole.eni.tplokacar.database.DateTypeConverter;
import fr.ecole.eni.tplokacar.database.entity.Vehicule;

@Dao
public interface VehiculeDAO extends GenericDAO<Vehicule> {

    @Query("DELETE FROM Vehicule")
    void deleteAll();

    @Query("SELECT * FROM Vehicule")
    List<Vehicule> selectAll();

    @TypeConverters({DateTypeConverter.class})
    @Query("SELECT DISTINCT * FROM Vehicule " +
            "WHERE Vehicule.id_vehicule " +
            "NOT IN (" +
            "SELECT L.vehiculeId FROM location L  " +
            "WHERE L.dateDepart BETWEEN :dateDebut AND :dateFin )")
    List<Vehicule> getAvailableVehicles(Date dateDebut , Date dateFin);
}

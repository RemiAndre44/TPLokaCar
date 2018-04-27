package fr.ecole.eni.tplokacar.database.dao;

import android.arch.persistence.room.Dao;

import fr.ecole.eni.tplokacar.database.entity.Vehicule;

@Dao
public interface VehiculeDAO extends GenericDAO<Vehicule> {
}

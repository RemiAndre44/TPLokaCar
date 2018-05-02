package fr.ecole.eni.tplokacar.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import fr.ecole.eni.tplokacar.database.dao.ClientDAO;
import fr.ecole.eni.tplokacar.database.dao.LocationDAO;
import fr.ecole.eni.tplokacar.database.dao.PhotoDAO;
import fr.ecole.eni.tplokacar.database.dao.VehiculeDAO;
import fr.ecole.eni.tplokacar.database.entity.Client;
import fr.ecole.eni.tplokacar.database.entity.Location;
import fr.ecole.eni.tplokacar.database.entity.Photo;
import fr.ecole.eni.tplokacar.database.entity.Vehicule;

@Database(entities = {Client.class, Location.class, Photo.class, Vehicule.class}, version = 4)
public abstract class LokaCarDB extends RoomDatabase {

    public static final String DATABASE_NAME = "LokaCar";

    public abstract ClientDAO clientDAO();
    public abstract LocationDAO locationDAO();
    public abstract PhotoDAO photoDAO();
    public abstract VehiculeDAO vehiculeDAO();
}

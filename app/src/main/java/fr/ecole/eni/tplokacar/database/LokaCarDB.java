package fr.ecole.eni.tplokacar.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import fr.ecole.eni.tplokacar.database.dao.ClientDAO;
import fr.ecole.eni.tplokacar.database.entity.Client;
import fr.ecole.eni.tplokacar.database.entity.Location;
import fr.ecole.eni.tplokacar.database.entity.Photo;
import fr.ecole.eni.tplokacar.database.entity.Vehicule;

@Database(entities = {Client.class, Location.class, Photo.class, Vehicule.class}, version = 1)
public abstract class LokaCarDB extends RoomDatabase {

    public static final String DATABASE_NAME = "LokaCar";

    public abstract ClientDAO clientDAO();
}

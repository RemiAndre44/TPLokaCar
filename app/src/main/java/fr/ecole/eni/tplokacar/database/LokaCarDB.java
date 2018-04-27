package fr.ecole.eni.tplokacar.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import fr.ecole.eni.tplokacar.database.dao.ClientDAO;
import fr.ecole.eni.tplokacar.database.entity.Client;

@Database(entities = {Client.class}, version = 1)
public abstract class LokaCarDB extends RoomDatabase {

    public static final String DATABASE_NAME = "LokaCar";

    public abstract ClientDAO clientDAO();
}

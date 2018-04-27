package fr.ecole.eni.tplokacar;

import android.app.Application;
import android.arch.persistence.room.Room;

import fr.ecole.eni.tplokacar.database.LokaCarDB;


public class App extends Application {

    private static App instance;
    private LokaCarDB database;

    public static App get() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // Create db
        database = Room.databaseBuilder(getApplicationContext(), LokaCarDB.class, LokaCarDB.DATABASE_NAME).build();

        instance = this;
    }
}

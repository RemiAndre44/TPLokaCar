package fr.ecole.eni.tplokacar;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;

import fr.ecole.eni.tplokacar.database.LokaCarDB;
import fr.ecole.eni.tplokacar.database.Seed;
import fr.ecole.eni.tplokacar.database.entity.Client;


public class App extends Application {

    private static App instance;
    private LokaCarDB database;
    private String password = "qsdf";

    public static App get() {
        return instance;
    }

    public LokaCarDB getDB() {
        return database;
    }

    public boolean isValidPassword(String pass) {
        return this.password.equals(pass);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // Create db
        //
        // fallbackToDestructiveMigration and new Seed().execute()
        // need to be commented once in production :3
        database = Room
                .databaseBuilder(getApplicationContext(), LokaCarDB.class, LokaCarDB.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
        new Seed().execute();

        instance = this;
    }
}

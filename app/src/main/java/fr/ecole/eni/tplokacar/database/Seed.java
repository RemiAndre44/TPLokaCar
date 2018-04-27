package fr.ecole.eni.tplokacar.database;

import android.os.AsyncTask;

import fr.ecole.eni.tplokacar.App;
import fr.ecole.eni.tplokacar.database.entity.Client;

public class Seed extends AsyncTask<Void, Void, String> {

    @Override
    protected String doInBackground(Void... voids) {
        LokaCarDB db = App.get().getDB();
        db.beginTransaction();

        // Create clients;
        Client bob = new Client();
        Client yo = new Client();

        db.clientDAO().insert(bob);
        db.clientDAO().insert(yo);


        db.endTransaction();

        return null;
    }
}

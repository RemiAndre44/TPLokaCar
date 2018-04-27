package fr.ecole.eni.tplokacar.database;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fr.ecole.eni.tplokacar.App;
import fr.ecole.eni.tplokacar.database.entity.Client;

public class Seed extends AsyncTask<Void, Void, String> {

    @Override
    protected String doInBackground(Void... voids) {
        LokaCarDB db = App.get().getDB();
        db.beginTransaction();

        // Create clients;
        Client pierre = new Client();
        pierre.setPrenom("Pierre");
        pierre.setNom("Cormier");
        pierre.setAdresse("12 rue de la défaite");
        pierre.setCodePostal("44 000");
        pierre.setVille("Nantes");

        db.clientDAO().insert(pierre);

        List<Client> clients = db.clientDAO().getAll();
        for (Client c : clients) {
            Log.e("TEST", c.getId_client() + "");
        }

        db.endTransaction();

        return null;
    }
}

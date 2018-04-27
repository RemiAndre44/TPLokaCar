package fr.ecole.eni.tplokacar.database;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.ecole.eni.tplokacar.App;
import fr.ecole.eni.tplokacar.database.entity.Client;
import fr.ecole.eni.tplokacar.database.entity.Location;
import fr.ecole.eni.tplokacar.database.entity.Vehicule;

public class Seed extends AsyncTask<Void, Void, String> {

    @Override
    protected String doInBackground(Void... voids) {
        LokaCarDB db = App.get().getDB();

        Log.e("TEST", "Start seed");
        db.beginTransaction();

        db.locationDAO().deleteAll();
        db.photoDAO().deleteAll();
        db.clientDAO().deleteAll();
        db.vehiculeDAO().deleteAll();


        // Create clients;
        Client pierre = new Client();
        pierre.setPrenom("Pierre");
        pierre.setNom("Cormier");
        pierre.setAdresse("12 rue de la défaite");
        pierre.setCodePostal("44 000");
        pierre.setVille("Nantes");
        pierre.setId_client( (int) db.clientDAO().insert(pierre));

        // Create vehicules
        Vehicule pijot = new Vehicule();
        pijot.setLouee(false);
        pijot.setCarburant("diesel");
        pijot.setNbrePlaces(4);
        pijot.setPlaque("CAT-789-BB");
        pijot.setMarque("Peugeot");
        pijot.setModele("106");
        pijot.setId_vehicule((int) db.vehiculeDAO().insert(pijot));

        // Create loc
        Location loc = new Location();
        loc.setClientId(pierre.getId_client());
        loc.setVehiculeId(pijot.getId_vehicule());
        loc.setDateDepart(new Date());

        loc.setId_location( (int) db.locationDAO().insert(loc));

        db.endTransaction();
        Log.e("TEST", "end of seed");

        Log.e("TEST", pierre.getId_client() + "");
        Log.e("TEST", pijot.getId_vehicule() + "");
        Log.e("TEST", loc.getId_location() + "");


        return null;
    }
}

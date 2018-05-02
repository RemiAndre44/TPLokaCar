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

        // Create clients;
        Client lucio = new Client();
        lucio.setPrenom("Lucio");
        lucio.setNom("Bukowski");
        lucio.setAdresse("rue de la plume");
        lucio.setCodePostal("56 000");
        lucio.setVille("Vannes");


        db.clientDAO().insert(lucio);

        // Create clients;
        Client jean = new Client();
        jean.setPrenom("Jean-Claude");
        jean.setNom("Dus");
        jean.setAdresse("rue des tombeurs");
        jean.setCodePostal("33 000");
        jean.setVille("Bordeaux");


        db.clientDAO().insert(jean);

        // Create clients;
        Client dooz = new Client();
        dooz.setPrenom("Dooz");
        dooz.setNom("Kawa");
        dooz.setAdresse("rue des étoiles du sol");
        dooz.setCodePostal("67 000");
        dooz.setVille("Strasbourg");


        db.clientDAO().insert(dooz);

        // Create vehicules
        Vehicule pijot = new Vehicule();
        pijot.setLouee(true);
        pijot.setCarburant("diesel");
        pijot.setNbrePlaces(4);
        pijot.setPlaque("CA-789-BB");
        pijot.setMarque("Peugeot");
        pijot.setModele("106");
        pijot.setPrix(25);
        pijot.setId_vehicule((int) db.vehiculeDAO().insert(pijot));

        Vehicule golf = new Vehicule();
        golf.setCarburant("diesel");
        golf.setNbrePlaces(4);
        golf.setPlaque("GH-488-FD");
        golf.setMarque("Volkswagen");
        golf.setModele("Golf");
        golf.setPrix(50);
        db.vehiculeDAO().insert(golf);

        golf.setPlaque("FV-556-GT");
        db.vehiculeDAO().insert(golf);

        golf.setPlaque("GF-478-GK");
        db.vehiculeDAO().insert(golf);

        // Create loc
        Location loc = new Location();
        loc.setClientId(pierre.getId_client());
        loc.setVehiculeId(pijot.getId_vehicule());
        loc.setDateDepart(new Date());
        loc.setDuree(5);

        loc.setId_location( (int) db.locationDAO().insert(loc));

        Log.e("TEST", "end of seed");

        return null;
    }
}

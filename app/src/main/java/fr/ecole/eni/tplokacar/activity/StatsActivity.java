package fr.ecole.eni.tplokacar.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import fr.ecole.eni.tplokacar.App;
import fr.ecole.eni.tplokacar.R;
import fr.ecole.eni.tplokacar.database.LokaCarDB;
import fr.ecole.eni.tplokacar.database.entity.Client;
import fr.ecole.eni.tplokacar.database.entity.Location;
import fr.ecole.eni.tplokacar.database.entity.Vehicule;

public class StatsActivity extends ActivityWithMenu {
    List<Client> clients;
    List<Vehicule> vehicules;
    List<Location> locations;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        new DBCall().start();
    }

    private class DBCall extends Thread {
        @Override
        public void run() {
            LokaCarDB db = App.get().getDB();

            clients = db.clientDAO().getAll();
            vehicules = db.vehiculeDAO().selectAll();
            locations = db.locationDAO().selectAll();

            RenderView();
        }
    }

    private Vehicule getVehiculeById(int id) {
        for (Vehicule v : vehicules) {
            if (v.getId_vehicule() == id) {
                return v;
            }
        }

        return null;
    }

    private void RenderView() {
        TextView cc = findViewById(R.id.count_client);
        TextView cv = findViewById(R.id.count_vehicule);
        TextView cl = findViewById(R.id.count_loue);
        TextView tca = findViewById(R.id.chiffre_affaire);

        cc.setText(clients.size() + "");
        cv.setText(vehicules.size() + "");


        int nbLoue = 0;
        for (Vehicule v : vehicules) {
            if (v.isLouee()) nbLoue++;
        }

        int pourcent = (nbLoue * 100) /  vehicules.size();

        cl.setText(nbLoue + " - " + pourcent + "%");

        long ca = 0;
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Date date = new GregorianCalendar(year, 0, 0).getTime();

        for (Location loc : locations) {
            if (loc.getDateDepart().after(date)) {
                Vehicule v = getVehiculeById(loc.getVehiculeId());
                ca += (loc.getDuree() * v.getPrix());
            }
        }

        tca.setText(ca + "€");
    }
}

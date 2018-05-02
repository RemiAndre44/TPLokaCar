package fr.ecole.eni.tplokacar.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

import fr.ecole.eni.tplokacar.App;
import fr.ecole.eni.tplokacar.R;
import fr.ecole.eni.tplokacar.database.LokaCarDB;
import fr.ecole.eni.tplokacar.database.entity.Client;
import fr.ecole.eni.tplokacar.database.entity.Vehicule;

public class StatsActivity extends ActivityWithMenu {
    List<Client> clients;
    List<Vehicule> vehicules;

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

            RenderView();
        }
    }

    private void RenderView() {
        TextView cc = findViewById(R.id.count_client);
        TextView cv = findViewById(R.id.count_vehicule);
        TextView cl = findViewById(R.id.count_loue);

        cc.setText(clients.size() + "");
        cv.setText(vehicules.size() + "");


        int nbLoué = 0;
        for (Vehicule v : vehicules) {
            if (v.isLouee()) nbLoué++;
        }

        cl.setText(nbLoué + "");
    }
}

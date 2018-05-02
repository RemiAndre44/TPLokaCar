package fr.ecole.eni.tplokacar.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.ecole.eni.tplokacar.App;
import fr.ecole.eni.tplokacar.R;
import fr.ecole.eni.tplokacar.database.LokaCarDB;
import fr.ecole.eni.tplokacar.database.entity.Client;
import fr.ecole.eni.tplokacar.database.entity.Vehicule;

public class ListeClientActivity extends ActivityWithMenu {
    private ListView lv;
    private List<Client> lstC;
    private String resultat;
    private AdapterClient adapterClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_client);

        lstC = new ArrayList<Client>();

        lv= findViewById(R.id.listViewClient);
        adapterClient = new AdapterClient(ListeClientActivity.this,
                R.layout.client,
                lstC);
        lv.setAdapter(adapterClient);
        final Thread thread = new Thread(){
            public void run(){

                lstC = App.get().getDB().clientDAO().getAll();
                chargeListeClient();
                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);
            }
        };

        thread.start();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Client selected= new Client();
                selected=lstC.get(position);
                Log.d("salut", selected.toString());
                Intent intent= new Intent(ListeClientActivity.this, DetailClientActivity.class);
                intent.putExtra("selected", selected);
                startActivity(intent);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ListeClientActivity.this, CreateCarActivity.class);

                startActivity(intent);
            }
        });
    }

    private void chargeListeClient(){
        adapterClient.clear();
        adapterClient.addAll(lstC);
        adapterClient.notifyDataSetChanged();

    }

    public void onClickRechercheClient(View view) {
        TextView recherche= findViewById(R.id.resultatRecherche);
        resultat= recherche.getText().toString();
        lstC= new ArrayList<Client>();

        final Thread thread = new Thread(){
            public void run(){
                lstC = App.get().getDB().clientDAO().findByName("%"+resultat+"%");
                Log.d("Resultat", lstC.toString());
                Message msg = new Message();
                msg.what = 2;
                handler.sendMessage(msg);
            }
        };

        thread.start();
    }



    private class monHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 1) {
                chargeListeClient();
            } else {
                chargeListeClient();
            }
        }
    }

    private monHandler handler = new monHandler();

}

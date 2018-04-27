package fr.ecole.eni.tplokacar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.ecole.eni.tplokacar.App;
import fr.ecole.eni.tplokacar.R;
import fr.ecole.eni.tplokacar.database.entity.Vehicule;

public class ListeVehiculeActivity extends AppCompatActivity {

    private List<Vehicule> lstV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_vehicule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lstV= new ArrayList<Vehicule>();
        ListView lv= findViewById(R.id.listView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ListeVehiculeActivity.this, HomeActivity.class);

                startActivity(intent);
            }
        });

        AdapterVehicule adapterVehicule = new AdapterVehicule(ListeVehiculeActivity.this,
                R.layout.item,
                lstV);

        Thread thread= new Thread(){
            public void run(){
                lstV =App.get().getDB().vehiculeDAO().selectAll();
            }
        };


        lv.setAdapter(adapterVehicule);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

}

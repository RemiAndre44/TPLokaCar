package fr.ecole.eni.tplokacar.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import fr.ecole.eni.tplokacar.R;
import fr.ecole.eni.tplokacar.database.entity.Client;

public class DetailClientActivity extends AppCompatActivity {
    private TextView num;
    private TextView nom;
    private TextView prenom;
    private TextView adresse;
    private TextView codePostal;
    private TextView ville;
    private TextView numClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_client);

        //chargeDonnees();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void onClickHome(MenuItem item) {
        Intent intent = new Intent(DetailClientActivity.this, HomeActivity.class);

        startActivity(intent);
    }
}

package fr.ecole.eni.tplokacar.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import fr.ecole.eni.tplokacar.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void onClickVehicule(View view) {
        Intent intent = new Intent(HomeActivity.this, ListeVehiculeActivity.class);

        startActivity(intent);
    }


    public void onClickHome(MenuItem item) {
        Toast.makeText(HomeActivity.this, "Vous êtes déjà sur la page d'accueil", Toast.LENGTH_SHORT).show();
    }

    public void onClickClient(View view) {
        Intent intent= new Intent(HomeActivity.this, ListeClientActivity.class);

        startActivity(intent);
    }

    public void onClickStats(View view) {
        Intent intent = new Intent(HomeActivity.this, StatsActivity.class);
        startActivity(intent);
    }
}

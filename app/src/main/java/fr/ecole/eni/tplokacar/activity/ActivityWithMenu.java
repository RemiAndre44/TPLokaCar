package fr.ecole.eni.tplokacar.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import fr.ecole.eni.tplokacar.R;

public class ActivityWithMenu extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void onClickHome(MenuItem item) {
        Intent intent= new Intent(this, HomeActivity.class);

        startActivity(intent);
    }
}

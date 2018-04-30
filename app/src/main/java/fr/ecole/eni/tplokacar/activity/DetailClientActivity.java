package fr.ecole.eni.tplokacar.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import fr.ecole.eni.tplokacar.R;

public class DetailClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_client);
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

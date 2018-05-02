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

        chargeDonnees();


    }

    private void chargeDonnees(){

        Intent intent= getIntent();
        Client client= new Client();
        client = intent.getParcelableExtra("selected");
        Log.d("oups",client.toString());

        nom = findViewById(R.id.nomC);
        nom.setText(client.getNom());
        prenom = findViewById(R.id.prenomC);
        prenom.setText(client.getPrenom());
        adresse = findViewById(R.id.adresseC);
        adresse.setText(client.getAdresse());
        codePostal = findViewById(R.id.codePostalC);
        codePostal.setText(client.getCodePostal());
        ville = findViewById(R.id.villeC);
        ville.setText(client.getVille());
        num = findViewById(R.id.numC);
        num.setText("#"+Integer.toString(client.getId_client()) );

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

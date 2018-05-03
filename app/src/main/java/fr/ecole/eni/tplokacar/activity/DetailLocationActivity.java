package fr.ecole.eni.tplokacar.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.provider.MediaStore.Images.Media;

import fr.ecole.eni.tplokacar.R;
import fr.ecole.eni.tplokacar.database.entity.Client;
import fr.ecole.eni.tplokacar.database.entity.Location;
import fr.ecole.eni.tplokacar.database.entity.Vehicule;

public class DetailLocationActivity extends ActivityWithMenu {
    TextView nomClient;
    TextView marqueVehicule;
    TextView modeleVehicule;
    TextView immatriculationVehicule;
    TextView nbPlacesVehicule;
    TextView dateDepartLocation;
    TextView dureeLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("salop","yo4");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_location);

        ContentResolver photoResolver = getContentResolver();
        Uri photoUri = Media.EXTERNAL_CONTENT_URI;


    }

    public void chargementDonnee(){
        Intent intent =getIntent();
        Client client = new Client();
        client= intent.getParcelableExtra("");
        Vehicule vehicule = new Vehicule();
        vehicule = intent.getParcelableExtra("");
        Location location = new Location();
        location = intent.getParcelableExtra("");

        nomClient = findViewById(R.id.nomClientLocation);
        nomClient.setText(client.getNom()+"#"+client.getId_client());
        marqueVehicule = findViewById(R.id.marqueVehiculeLocaton);
        marqueVehicule.setText(vehicule.getMarque());
        modeleVehicule = findViewById(R.id.modeleVehiculeLocation);
        modeleVehicule.setText(vehicule.getModele());
        immatriculationVehicule = findViewById(R.id.imatVehiculeLocation);
        immatriculationVehicule.setText(vehicule.getPlaque());
        nbPlacesVehicule = findViewById(R.id.nbPlacesVehiculeLocation);
        nbPlacesVehicule.setText(vehicule.getNbrePlaces());
        dateDepartLocation = findViewById(R.id.dateDepartVehiculeLocation);
        //dateDepartLocation.setText(Integer.toString(location.getDateDepart()));
        dureeLocation = findViewById(R.id.dureeVehiculeLocation);
        dureeLocation.setText(Integer.toString(location.getDuree()));


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("salop", "yo1");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("salop", "yo2");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("salop", "yo3");
    }

    public void onClickCamDepart(View view) {
        Intent intent = new Intent(DetailLocationActivity.this, CameraActivity.class);
        String depart = "depart";
        intent.putExtra("photo",depart);
        startActivity(intent);
    }

    public void onClickCamRetour(View view) {
        Intent intent = new Intent(DetailLocationActivity.this, CameraActivity.class);
        String retour = "retour";
        intent.putExtra("photo",retour);
        startActivity(intent);
    }

}
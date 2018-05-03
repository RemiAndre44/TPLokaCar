package fr.ecole.eni.tplokacar.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.provider.MediaStore.Images.Media;

import java.text.DateFormat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_location);

        ContentResolver photoResolver = getContentResolver();
        Uri photoUri = Media.EXTERNAL_CONTENT_URI;

        //chargementDonnee();

        List<String> lstKeys = new ArrayList<>();

        File liste_file=new File(Environment.getExternalStoragePublicDirectory (Environment.DIRECTORY_PICTURES).getAbsolutePath());

        Log.d("salut",liste_file.toString());

        ContentResolver photoResolver1 = getContentResolver();
        Uri photoUri1 = Media.EXTERNAL_CONTENT_URI;

        String selection = Media.IS_PRIVATE + " != 0";

        String[] projection = {
                Media.DESCRIPTION,
                Media.MIME_TYPE,
                Media.DATE_TAKEN,
                Media.TITLE,
                Media.DISPLAY_NAME
        };

        Cursor photoCursor = photoResolver1.query(photoUri1, projection, selection, null, null);

        Log.i("photo", "Nombre photos : " + photoCursor.getCount());

    }

    public void chargementDonnee(){
        Intent intent =getIntent();
        Client client = new Client();
        client= intent.getParcelableExtra("client");
        Vehicule vehicule = new Vehicule();
        vehicule = intent.getParcelableExtra("voiture");
        Location location = new Location();
        location = intent.getParcelableExtra("location");

        nomClient = findViewById(R.id.nomClientLocation);
        nomClient.setText(client.getNom()+"#"+client.getId_client());
        marqueVehicule = findViewById(R.id.marqueVehiculeLocaton);
        marqueVehicule.setText(vehicule.getMarque());
        modeleVehicule = findViewById(R.id.modeleVehiculeLocation);
        modeleVehicule.setText(vehicule.getModele());
        immatriculationVehicule = findViewById(R.id.imatVehiculeLocation);
        immatriculationVehicule.setText(vehicule.getPlaque());
        nbPlacesVehicule = findViewById(R.id.nbPlacesVehiculeLocation);
        nbPlacesVehicule.setText(Integer.toString(vehicule.getNbrePlaces()));
        dateDepartLocation = findViewById(R.id.dateDepartVehiculeLocation);
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

        dateDepartLocation.setText(dateFormat.format(location.getDateDepart()));

        Log.d("tog", "chargementDonnee: "+location.getDateDepart());
        dureeLocation = findViewById(R.id.dureeVehiculeLocation);
        dureeLocation.setText(Integer.toString(location.getDuree()));


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
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
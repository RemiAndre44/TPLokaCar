package fr.ecole.eni.tplokacar.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import org.w3c.dom.Text;

import fr.ecole.eni.tplokacar.R;
import fr.ecole.eni.tplokacar.database.entity.Vehicule;

public class DetailVehiculeActivity extends ActivityWithMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_vehicule);

        //récupération de l'objet selectionné
        Intent intent= getIntent();
        Vehicule selected = new Vehicule();
        selected= intent.getParcelableExtra("selected");

        //chargement des champs
        TextView marque = findViewById(R.id.marqueVehicule);
        marque.setText(selected.getMarque());
        TextView modele= findViewById(R.id.modeleVehicule);
        modele.setText(selected.getModele());
        TextView carburant = findViewById(R.id.carburantVehicule);
        carburant.setText(selected.getCarburant());
        TextView nbPlaces = findViewById(R.id.places);
        nbPlaces.setText(Integer.toString(selected.getNbrePlaces()));
        TextView plaque= findViewById(R.id.plaqueVehicule);
        plaque.setText(selected.getPlaque());
        TextView prix = findViewById(R.id.prixVehicule);
        prix.setText(Integer.toString((int) selected.getPrix())+" €");
       /* TextView loue= findViewById(R.id.loue);
        boolean estLoue = selected.isLouee();
        if(estLoue == true){
            loue.setText("Est loué");
        }else{
            loue.setText("A louer");
        }*/



    }
}

package fr.ecole.eni.tplokacar.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.ecole.eni.tplokacar.App;
import fr.ecole.eni.tplokacar.R;
import fr.ecole.eni.tplokacar.database.entity.Client;
import fr.ecole.eni.tplokacar.database.entity.Vehicule;

public class CreateCarActivity extends AppCompatActivity{

    TextView marque;
    TextView modele;
    TextView prix;
    TextView nbrePlaces;
    TextView plaque;
    Spinner carburantSpinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_car);

        initComponent();
        createListeCarburant();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    private void initComponent() {
        marque = findViewById(R.id.marque);
        modele = findViewById(R.id.modele);
        prix = findViewById(R.id.prix);
        nbrePlaces = findViewById(R.id.nbrePlaces);
        plaque = findViewById(R.id.plaque);
        carburantSpinner = findViewById(R.id.carburant);
    }

    private void createListeCarburant() {

        List carbuList = new ArrayList();
        carbuList.add("Diesel");
        carbuList.add("SP98");
        carbuList.add("SP95");
        carbuList.add("GPL");
        carbuList.add("Hybride");

        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                carbuList
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carburantSpinner.setAdapter(adapter);
    }


    public void submitForm(View view) {

        String marqueArg = marque.getText().toString();
        String modeleArg = modele.getText().toString();
        String prixArg = prix.getText().toString();
        String plaqueArg = plaque.getText().toString();
        String nbrePlacesArg = nbrePlaces.getText().toString();
        String carbuArg = carburantSpinner.getSelectedItem().toString();

        final Vehicule vehicule = new Vehicule();
        vehicule.setMarque(marqueArg);
        vehicule.setModele(modeleArg);
        vehicule.setPrix(Float.parseFloat(prixArg));
        vehicule.setPlaque(plaqueArg);
        vehicule.setNbrePlaces(Integer.parseInt(nbrePlacesArg));
        vehicule.setCarburant(carbuArg);

        new SaveData().execute(vehicule);

    }

    public void cxlForm(View view) {
        marque.setText("");
        modele.setText("");
        prix.setText("");
        plaque.setText("");
        nbrePlaces.setText("");
        carburantSpinner.setSelection(0);
    }

    public void onClickHome(MenuItem item) {
        Intent intent= new Intent(CreateCarActivity.this, HomeActivity.class);

        startActivity(intent);
    }



    private class SaveData extends AsyncTask<Vehicule, Integer, String> {

        @Override
        protected String doInBackground(Vehicule... vehicule) {
            App.get().getDB().vehiculeDAO().insert(vehicule[0]);
            return "le véhicule est bien enregistré";
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast toast = Toast.makeText(getApplicationContext(),  s, Toast.LENGTH_SHORT);
            toast.show();
            cxlForm(findViewById(R.id.view));
        }

    }
}

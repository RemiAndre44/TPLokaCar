package fr.ecole.eni.tplokacar.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fr.ecole.eni.tplokacar.App;
import fr.ecole.eni.tplokacar.R;
import fr.ecole.eni.tplokacar.database.entity.Vehicule;

public class CreateCarActivity extends ActivityWithMenu {

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
        final Vehicule vehicule = new Vehicule();

        try {
            vehicule.setMarque(marque.getText().toString());
            vehicule.setModele(modele.getText().toString());
            vehicule.setPrix(Float.parseFloat(prix.getText().toString()));
            vehicule.setPlaque(plaque.getText().toString());
            vehicule.setNbrePlaces(Integer.parseInt(nbrePlaces.getText().toString()));
            vehicule.setCarburant(carburantSpinner.getSelectedItem().toString());

        } catch (Exception e) {
            displayActionResult("Merci de remplir l'ensemble des champs");
            return;
        }

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

    private void displayActionResult(String msg) {
        Toast toast = Toast.makeText(getApplicationContext(),  msg, Toast.LENGTH_SHORT);
        toast.show();
    }

    private class SaveData extends AsyncTask<Vehicule, Integer, String> {

        @Override
        protected String doInBackground(Vehicule... vehicule) {
            try {
                App.get().getDB().vehiculeDAO().insert(vehicule[0]);
            } catch (Exception e) {
                return "Une erreur est survenue :(";
            }

            return "le véhicule est bien enregistré";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            displayActionResult(s);
            cxlForm(findViewById(R.id.view));
        }
    }
}

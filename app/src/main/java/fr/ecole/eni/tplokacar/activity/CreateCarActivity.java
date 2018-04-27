package fr.ecole.eni.tplokacar.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.ecole.eni.tplokacar.R;

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

    }

    public void cxlForm(View view) {
        marque.setText("");
        modele.setText("");
        prix.setText("");
        plaque.setText("");
        nbrePlaces.setText("");
        carburantSpinner.setSelection(0);
    }
}

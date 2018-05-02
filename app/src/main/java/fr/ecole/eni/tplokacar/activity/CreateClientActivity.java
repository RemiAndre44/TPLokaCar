package fr.ecole.eni.tplokacar.activity;

import android.os.AsyncTask;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import fr.ecole.eni.tplokacar.App;
import fr.ecole.eni.tplokacar.R;
import fr.ecole.eni.tplokacar.database.entity.Client;


public class CreateClientActivity extends AppCompatActivity {


    private TextView nom ;
    private TextView prenom;
    private TextView adresse;
    private TextView codePostal;
    private TextView ville;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_client);
        initComponent();
    }

    private void initComponent() {
        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        adresse = findViewById(R.id.adresse);
        codePostal = findViewById(R.id.codePostal);
        ville = findViewById(R.id.ville);
    }


    public void submitForm(View view) {

        String nomArg = nom.getText().toString();
        String prenomArg = prenom.getText().toString();
        String adresseArg = adresse.getText().toString();
        String codePostalArg = codePostal.getText().toString();
        String villeArg = ville.getText().toString();


        final Client client = new Client();
        client.setNom(nomArg);
        client.setPrenom(prenomArg);
        client.setAdresse(adresseArg);
        client.setCodePostal(codePostalArg);
        client.setVille(villeArg);

        new SaveData().execute(client);
    }

    private void cleanForm(){
        nom.setText("");
        prenom.setText("");
        adresse.setText("");
        codePostal.setText("");
        ville.setText("");
    }

    private class SaveData extends AsyncTask<Client, Integer, String>{

        @Override
        protected String doInBackground(Client... client) {
            App.get().getDB().clientDAO().insert(client[0]);
            return "le client est bien enregistré";
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast toast = Toast.makeText(getApplicationContext(),  s, Toast.LENGTH_SHORT);
            toast.show();
            cleanForm();
        }

    }



}

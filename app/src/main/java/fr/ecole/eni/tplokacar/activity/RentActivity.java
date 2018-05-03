package fr.ecole.eni.tplokacar.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import fr.ecole.eni.tplokacar.App;
import fr.ecole.eni.tplokacar.R;
import fr.ecole.eni.tplokacar.database.entity.Client;
import fr.ecole.eni.tplokacar.database.entity.Location;
import fr.ecole.eni.tplokacar.database.entity.Vehicule;

public class RentActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{


    private Date dateDebut;
    private Date dateFin;
    private Calendar calendar;
    private TextView nbreJours;
    private Spinner spinner;
    private List<Vehicule> listeVehicules;
    private MonHandler handler = new MonHandler();
    private Vehicule selectedVehicle;
    private List<Client> lstC;
    private String resultat;
    private AdapterClient adapterClient;
    private ListView lv;
    private Client selectedClient;
    private TextView nomClient;
    private TextView prenomClient;
    private TextView vehiculeDetail;
    private Button btnCarDetail;
    private Location location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);
        this.nbreJours =  findViewById(R.id.nbreJours);
        this.spinner = findViewById(R.id.spinner);
        this.nomClient = findViewById(R.id.nomClient);
        this.prenomClient = findViewById(R.id.prenomClient);
        this.vehiculeDetail = findViewById(R.id.vehicule);
        this.btnCarDetail = findViewById(R.id.detailVehicule);


        this.btnCarDetail.setEnabled(false);
        lstC = new ArrayList<Client>();

        lv= findViewById(R.id.listViewClient);
        adapterClient = new AdapterClient(RentActivity.this,
                R.layout.client,
                lstC);
        lv.setAdapter(adapterClient);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedClient=lstC.get(position);
                nomClient.setText(selectedClient.getNom());
                prenomClient.setText(selectedClient.getPrenom());
            }
        });

    }




    /**
     * This callback method, call DatePickerFragment class,
     * DatePickerFragment class returns calendar view.
     * @param view
     */
    public void datePicker(View view) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(),"date");
    }

    public void getVehicles(View view) {

        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        int nbreJours = Integer.parseInt(this.nbreJours.getText().toString());

        calendar.add(calendar.DATE, nbreJours);
        this.dateFin =   calendar.getTime();

        getVehiclesAsync asyncTask = new getVehiclesAsync();
        asyncTask.execute();

    }

    public void validateLocation(View view) {
        if(selectedVehicle == null || selectedClient == null || dateDebut == null || dateFin == null){
            new Toast(this).makeText(this, "merci de renseigner tous les champs",
                    Toast.LENGTH_LONG).show();
        }else{
           SaveLocation locationAsync =  new SaveLocation();
           locationAsync.execute();
        }

    }



    public void carDetail(View view) {
        Intent intent = new Intent(RentActivity.this, DetailVehiculeActivity.class);
        intent.putExtra("selected",selectedVehicle);
        startActivity(intent);
    }


    /**
     * Create a DatePickerFragment class that extends DialogFragment.
     * Define the onCreateDialog() method to return an instance of DatePickerDialog
     */
    public static class DatePickerFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(),
                    (DatePickerDialog.OnDateSetListener)
                            getActivity(), year, month, day);
        }

    }

    /**
     * To receive a callback when the user sets the date.
     * @param view
     * @param year
     * @param month
     * @param day
     */
    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        setDate(cal);
    }

    /**
     * To set date on TextView
     * @param calendar
     */
    private void setDate(final Calendar calendar) {

        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        this.calendar = calendar;
        this.dateDebut = calendar.getTime();

        ((TextView) findViewById(R.id.showDate)).setText(dateFormat.format(this.dateDebut));
    }

    private class getVehiclesAsync extends AsyncTask<Vehicule, Integer, String> {

        @Override
        protected String doInBackground(Vehicule... vehicule) {
            listeVehicules =  App.get().getDB().vehiculeDAO().getAvailableVehicles(dateDebut, dateFin);
            return "";
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Message msg = new Message();
            msg.what = 1;
            handler.sendMessage(msg);
        }

    }

    private class SaveLocation extends AsyncTask<Location, Integer, String> {

        @Override
        protected String doInBackground(Location... vehicule) {
            try {
                location = new Location();
                location.setClientId(selectedClient.getId_client());
                location.setVehiculeId(selectedVehicle.getId_vehicule());
                location.setDateDepart(dateDebut);
                location.setDuree(Integer.parseInt(nbreJours.getText().toString()));
                location.setId_location( (int) App.get().getDB().locationDAO().insert(location));
            } catch (Exception e) {
                return "Une erreur est survenue :(";
            }

            return "la location est bien enregistrée";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Message msg = new Message();
            msg.what = 3;
            handler.sendMessage(msg);
        }
    }



    private class MonHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1 :
                   fillSpinner();
                    break;
                case 2 :
                    chargeListeClient();
                    break;
                case 3 :
                    getDetailLocation();
                    break;
            }
        }
    }

    private void getDetailLocation() {
        Intent intent= new Intent(RentActivity.this, DetailClientActivity.class);
        intent.putExtra("client", selectedClient);
        intent.putExtra("voiture", selectedVehicle);
        intent.putExtra("location" , location);
        startActivity(intent);
    }

    private void fillSpinner() {

        List<String> spinnerList = new ArrayList<>();

        for(Vehicule v : listeVehicules){
            spinnerList.add(v.getMarque() + " | " + v.getModele() );
        }

        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                spinnerList
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                    {
                        selectedVehicle = listeVehicules.get(position);
                        vehiculeDetail.setText(selectedVehicle.getMarque() + " - " + selectedVehicle.getModele());
                        btnCarDetail.setEnabled(true);
                    }
                    public void onNothingSelected(AdapterView<?> parent)
                    {

                    }
                });
    }


    public void onClickRechercheClient(View view) {
        TextView recherche= findViewById(R.id.resultatRecherche);
        resultat= recherche.getText().toString();
        lstC= new ArrayList<Client>();

        final Thread thread = new Thread(){
            public void run(){
                lstC = App.get().getDB().clientDAO().findByName("%"+resultat+"%");
                Message msg = new Message();
                msg.what = 2;
                handler.sendMessage(msg);
            }
        };

        thread.start();
    }


    private void chargeListeClient(){
        adapterClient.clear();
        adapterClient.addAll(lstC);
        adapterClient.notifyDataSetChanged();

    }


}

package fr.ecole.eni.tplokacar.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
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
import fr.ecole.eni.tplokacar.database.entity.Vehicule;

public class RentActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{


    private Date dateDebut;
    private Date dateFin;
    private Calendar calendar;
    private TextView nbreJours;
    private Spinner spinner;
    private List<Vehicule> listeVehicules;
    private MonHandler handler = new MonHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);
        this.nbreJours =  findViewById(R.id.nbreJours);
        this.spinner = findViewById(R.id.spinner);
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
        Log.d("tog", "setDate: "+ dateFormat.format(this.dateFin));

        getVehiclesAsync asyncTask = new getVehiclesAsync();
        asyncTask.execute();


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

        Log.d("tog", "setDate: "+dateFormat.format(this.dateDebut));
        ((TextView) findViewById(R.id.showDate)).setText(dateFormat.format(this.dateDebut));
    }

    private class getVehiclesAsync extends AsyncTask<Vehicule, Integer, String> {

        @Override
        protected String doInBackground(Vehicule... vehicule) {
            listeVehicules =  App.get().getDB().vehiculeDAO().getAvailableVehicles(dateDebut, dateFin);

            return "le véhicule est bien enregistré";
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Message msg = new Message();
            msg.what = 1;

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

            }


        }
    }

    private void fillSpinner() {

        List<String> spinnerList = new ArrayList<String>();

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
    }

}

package fr.ecole.eni.tplokacar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import fr.ecole.eni.tplokacar.R;

public class DetailLocationActivity extends ActivityWithMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_location);


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
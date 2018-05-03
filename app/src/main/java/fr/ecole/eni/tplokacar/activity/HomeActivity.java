package fr.ecole.eni.tplokacar.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import fr.ecole.eni.tplokacar.R;

public class HomeActivity extends AppCompatActivity {
    private final int MY_PERMISSIONS_REQUEST_CAMERA= 1;
    private final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE=2;
    private final int MY_PERMISSION_REQUEST_READ_EXTERNAL_STORAGE= 3;
    private View mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mLayout = findViewById(R.id.homeLayout);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            //Permission
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED){
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.CAMERA) || ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)|| ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)){

                    Snackbar.make(mLayout, R.string.permission_access_required,
                            Snackbar.LENGTH_INDEFINITE).setAction(R.string.ok, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Request the permission
                            ActivityCompat.requestPermissions(HomeActivity.this,
                                    new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},
                                    MY_PERMISSIONS_REQUEST_CAMERA);

                        }
                    }).show();


                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},
                            MY_PERMISSIONS_REQUEST_CAMERA);
                }
            } else {
                //Application suite
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void onClickVehicule(View view) {
        Intent intent = new Intent(HomeActivity.this, ListeVehiculeActivity.class);

        startActivity(intent);
    }


    public void onClickHome(MenuItem item) {
        Toast.makeText(HomeActivity.this, "Vous êtes déjà sur la page d'accueil", Toast.LENGTH_SHORT).show();
    }

    public void onClickClient(View view) {
        Intent intent= new Intent(HomeActivity.this, ListeClientActivity.class);

        startActivity(intent);
    }


    /**
     * Demande persmission
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[],
                                           int[] grantResults) {
        //Android version >= 23 Marshmallow
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            switch (requestCode) {
                case MY_PERMISSIONS_REQUEST_CAMERA: {
                    if (grantResults.length > 0
                            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        Log.i("PERMISSION_APP", "PERMISSION_GRANTED");
                        // La permission est garantie

                    } else {
                        // La permission est refusée
                        Toast.makeText(HomeActivity.this, "Nous ne pouvons lancer l'application sans cette autorisation.", Toast.LENGTH_LONG).show();
                        finish();
                    }
                    return;
                }
                case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                    if (grantResults.length > 0
                            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        Log.i("PERMISSION_APP", "PERMISSION_GRANTED");
                        // La permission est garantie

                    } else {
                        // La permission est refusée
                        Toast.makeText(HomeActivity.this, "Nous ne pouvons lancer l'application sans cette autorisation.", Toast.LENGTH_LONG).show();
                        finish();
                    }
                    return;
                }
                case MY_PERMISSION_REQUEST_READ_EXTERNAL_STORAGE: {
                    if (grantResults.length > 0
                            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        Log.i("PERMISSION_APP", "PERMISSION_GRANTED");
                        // La permission est garantie

                    } else {
                        // La permission est refusée
                        Toast.makeText(HomeActivity.this, "Nous ne pouvons lancer l'application sans cette autorisation.", Toast.LENGTH_LONG).show();
                        finish();
                    }
                    return;
                }

            }
        }
    }

    public void onClickStats(View view) {
        Intent intent = new Intent(HomeActivity.this, StatsActivity.class);
        startActivity(intent);
    }

    public void createLocation(View view) {
        Intent intent= new Intent(HomeActivity.this, RentActivity.class);
        startActivity(intent);
    }
}

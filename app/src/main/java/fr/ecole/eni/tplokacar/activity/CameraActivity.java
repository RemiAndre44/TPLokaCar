package fr.ecole.eni.tplokacar.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.ecole.eni.tplokacar.R;

public class CameraActivity extends Activity implements SurfaceHolder.Callback{
    private Camera camera;
    private SurfaceView surfaceCamera;
    private Boolean isPreview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        isPreview = false;

        // Nous récupérons notre surface pour le preview
        surfaceCamera = (SurfaceView) findViewById(R.id.surfaceViewCamera);

        // Méthode d'initialisation de la caméra
        InitializeCamera();

        surfaceCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (camera != null) {
                    SavePicture();
                }
            }
        });
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (camera == null)
            camera = Camera.open();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // Si le mode preview est lancé alors nous le stoppons
        if (isPreview) {
            camera.stopPreview();
        }
        // Nous récupérons les paramètres de la caméra
        Camera.Parameters parameters = camera.getParameters();

        // Nous changeons la taille
        parameters.setPreviewSize(width, height);

        // Nous appliquons nos nouveaux paramètres
        camera.setParameters(parameters);

        try {
            // Nous attachons notre prévisualisation de la caméra au holder de la
            // surface
            camera.setPreviewDisplay(surfaceCamera.getHolder());
        } catch (IOException e) {
        }

        // Nous lançons la preview
        camera.startPreview();

        isPreview = true;
    }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (camera != null) {
            camera.stopPreview();
            isPreview = false;
            camera.release();
        }
    }

    public void InitializeCamera() {
        // Nous attachons nos retours du holder à notre activité
        surfaceCamera.getHolder().addCallback(this);
        // Nous spécifiions le type du holder en mode SURFACE_TYPE_PUSH_BUFFERS
        surfaceCamera.getHolder().setType(
                SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void onResume() {
        super.onResume();
        camera = Camera.open();
    }

    // Mise en pause de l'application
    @Override
    public void onPause() {
        super.onPause();

        if (camera != null) {
            camera.release();
            camera = null;
        }
    }

    private void SavePicture() {
        Intent intent = new Intent(CameraActivity.this, DetailLocationActivity.class);

        startActivity(intent);
        Toast.makeText(CameraActivity.this,"Votre photo est bien enregistrée", Toast.LENGTH_SHORT).show();
    }

}

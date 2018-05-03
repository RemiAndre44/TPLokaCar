package fr.ecole.eni.tplokacar.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.provider.MediaStore.Images.Media;

import fr.ecole.eni.tplokacar.App;
import fr.ecole.eni.tplokacar.R;
import fr.ecole.eni.tplokacar.database.entity.Photo;

public class CameraActivity extends Activity implements SurfaceHolder.Callback{
    private Camera camera;
    private SurfaceView surfaceCamera;
    private Boolean isPreview;
    private FileOutputStream stream;


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

        try {
            SimpleDateFormat timeStampFormat = new SimpleDateFormat(
                    "yyyy-MM-dd-HH.mm.ss");
            String fileName = "photo_" + timeStampFormat.format(new Date())
                    + ".jpg";

            // Metadata pour la photo
            ContentValues values = new ContentValues();
            values.put(Media.TITLE, fileName);
            values.put(Media.DISPLAY_NAME, fileName);
            values.put(Media.DESCRIPTION, "Image prise par FormationCamera");
            values.put(Media.DATE_TAKEN, new Date().getTime());
            values.put(Media.MIME_TYPE, "image/jpeg");

            // Support de stockage
            Uri taken = getContentResolver().insert(Media.EXTERNAL_CONTENT_URI,
                    values);

            // Ouverture du flux pour la sauvegarde
            stream = (FileOutputStream) getContentResolver().openOutputStream(
                    taken);

            camera.takePicture(null, pictureCallback, pictureCallback);

            Intent intent = new Intent(CameraActivity.this, DetailLocationActivity.class);
            intent.putExtra("values", values);
            startActivity(intent);
            Toast.makeText(CameraActivity.this,"Votre photo est bien enregistrée", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            // TODO: handle exception
        }



    }

    Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {

        public void onPictureTaken(byte[] data, Camera camera) {
            if (data != null) {
                // Enregistrement de votre image
                try {
                    if (stream != null) {
                        stream.write(data);
                        stream.flush();
                        stream.close();
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }

                // Nous redémarrons la prévisualisation
                camera.startPreview();
            }
        }
    };




}

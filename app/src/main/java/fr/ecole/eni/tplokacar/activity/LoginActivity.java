package fr.ecole.eni.tplokacar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import fr.ecole.eni.tplokacar.App;
import fr.ecole.eni.tplokacar.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onLoginClick(View view) {
        TextView mdp = findViewById(R.id.mdp);

        if (App.get().isValidPassword(mdp.getText().toString())) {
            Intent intent= new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            LoginActivity.this.finish();
        }
    }
}

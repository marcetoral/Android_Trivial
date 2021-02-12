package com.example.trivial;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;


public class AjustesActivity extends AppCompatActivity {

    private Switch switchMusica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);


        switchMusica = findViewById(R.id.switchMusica);


    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.switchMusica:
                Intent servicio = new Intent(this, MusicService.class);
                if (switchMusica.isChecked()) {
                    startService(servicio);
                } else {
                    stopService(servicio);
                }
                break;

            case R.id.btn_mant:
                Intent intent = new Intent(this, MantActivity.class);
                startActivity(intent);
                break;

            default:
                return;
        }

    }
}
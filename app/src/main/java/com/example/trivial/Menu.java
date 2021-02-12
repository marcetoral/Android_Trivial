package com.example.trivial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import java.util.Objects;

public class Menu extends AppCompatActivity {

    public static String username = "Guest";
    private EditText etUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //pantalla completa
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        etUsername = findViewById(R.id.etUsername);
        Intent servicio = new Intent(this, MusicService.class);
        startService(servicio);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent servicio = new Intent(this, MusicService.class);
        stopService(servicio);
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_jugar:
                username = etUsername.getText().toString().trim();
                intent = new Intent(this, MainActivity.class);
                break;

            case R.id.btn_ajustes:
                intent = new Intent(this, AjustesActivity.class);
                break;

            case R.id.btn_tops:
                intent = new Intent(this, TopsActivity.class);
                break;
            default:
                return;
        }
        startActivity(intent);
    }


    //Menu y opciones


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.i_mant) {
            Intent intent = new Intent(this, MantActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
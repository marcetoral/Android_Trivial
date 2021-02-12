package com.example.trivial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ExplicacionActivity extends AppCompatActivity {

    Button btnCerrar;
    TextView tvExplicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicacion);


        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        btnCerrar = findViewById(R.id.btn_volver);
        tvExplicacion = findViewById(R.id.tv_fin_puntos);

        tvExplicacion.setText(getIntent().getStringExtra("explicacion"));


        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


}
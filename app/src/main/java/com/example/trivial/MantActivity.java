package com.example.trivial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.trivial.adapters.MiAdaptador;
import com.example.trivial.modal.Pregunta;
import com.example.trivial.modal.PreguntasDB;

import java.util.ArrayList;
import java.util.List;

public class MantActivity extends AppCompatActivity {


    private static List<Pregunta> preguntas = null;
    public EditText etPregunta, etExplicacion;
    public CheckBox cbVerdadero;
    private MiAdaptador miAdaptador;
    private PreguntasDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mant);
        db = new PreguntasDB(this);
        inicilizarElementos();
        inicializarPreguntas();


        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        //recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        miAdaptador = new MiAdaptador(preguntas, this);

        recyclerView.setAdapter(miAdaptador);


    }

    public void onClick(View v) {
        //si no ha puesto nada, avisar
        if (etPregunta.getText().toString().trim().equals("") ||
                etExplicacion.getText().toString().trim().equals("")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Por favor, rellene los campos").setNegativeButton(android.R.string.ok,
                    (DialogInterface.OnClickListener) (dialog, which) -> {
                    });
            builder.create().show();
            return;
        }
        //si ha rellenado, añade a la lista
        Pregunta pNueva = new Pregunta(etPregunta.getText().toString(),
                cbVerdadero.isChecked(), etExplicacion.getText().toString());

        switch (v.getId()) {
            case R.id.btn_crear:
                //añadir a bd y el recycler
                db.addPregunta(pNueva);
                miAdaptador.addPregunta(pNueva);
                break;
            case R.id.btn_mod:
                Pregunta pSelected = miAdaptador.getPreguntaSeleccionada();
                if (pSelected == null) break;
                //modificar en list y db
                miAdaptador.modPregunta(pNueva);
                pNueva.setIdPregunta(pSelected.getIdPregunta());
                db.modificarRegistro(pNueva);
                break;
        }
        //limpiamos
        etExplicacion.setText("");
        etPregunta.setText("");
        cbVerdadero.setChecked(false);
    }


    private void inicilizarElementos() {

        etPregunta = findViewById(R.id.et_pregunta);
        etExplicacion = findViewById(R.id.et_explicacion);
        cbVerdadero = findViewById(R.id.cb_verdadero);

    }

    private void inicializarPreguntas() {
        preguntas = new ArrayList<>();
        Cursor c = db.getListaPreguntas();
        c.moveToFirst();
        while (!c.isAfterLast()) {
            preguntas.add(new Pregunta(c.getInt(c.getColumnIndex("_id")),
                    c.getString(c.getColumnIndex("pregunta")),
                    c.getInt(c.getColumnIndex("respuesta")) != 0, //me lo ha simplificado asi
                    c.getString(c.getColumnIndex("explicacion"))));
            c.moveToNext();
        }
    }


}
package com.example.trivial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.trivial.adapters.TopAdaptador;
import com.example.trivial.modal.PreguntasDB;
import com.example.trivial.modal.Score;

import java.util.ArrayList;
import java.util.List;

public class TopsActivity extends AppCompatActivity {

    private PreguntasDB db;
    private List<Score> listaTop;
    private TopAdaptador topAdaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tops);

        inicializarAtributos();

        RecyclerView recyclerView = findViewById(R.id.recycler_top);
        //recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        topAdaptador = new TopAdaptador(listaTop);

        recyclerView.setAdapter(topAdaptador);

        Button btnVolver = findViewById(R.id.btn_volver);
        btnVolver.setOnClickListener(v -> {
            finish();
        });


    }

    private void inicializarAtributos() {

        db = new PreguntasDB(this);
        listaTop = new ArrayList<>();

        Cursor c = db.getListaTop();
        Log.d("midebug", "select: " + c.getCount());
        c.moveToFirst();
        while (!c.isAfterLast()) {
            listaTop.add(new Score(c.getString(c.getColumnIndex("username")),
                    c.getInt(c.getColumnIndex("score"))));
            c.moveToNext();
        }

    }
}
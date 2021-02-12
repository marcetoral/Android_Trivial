package com.example.trivial;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trivial.modal.Pregunta;
import com.example.trivial.modal.PreguntasDB;
import com.example.trivial.modal.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static List<Pregunta> preguntas = null;
    private TextView tvPregunta;
    private int idPreguntaActual = -1;
    private int tiempo = 100;
    private ProgressBar barra;
    private int score;
    private static Score scoreFinal = new Score();
    //para hilo explciacion
    final Handler handler = new Handler();

    private Button btnTrue, btnFalso, btnNext;
    private Animation animBlink, animShake;
    private TextView tvScore;
    private TextView tvFin;
    private Dialog dialogTiempo, dialogFin;
    private Contador contador;

    private PreguntasDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //pantalla completa (no me interesa, quiero poder ver la hora y notificaciones)
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        scoreFinal.setUsername(Menu.username);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        //conectarlos los elementos de xml
        db = new PreguntasDB(this);
        inicializarElementos();
        inicializarPreguntas();

        cargarPregunta();

    }


    private void cargarPregunta() {

        idPreguntaActual++;
        btnNext.setEnabled(false);
        this.tvPregunta.setText(preguntas.get(idPreguntaActual).getPregunta());
        tiempo = 100;
        //si ya hay un contador, cancelarlo para meter uno nuevo
        if (contador != null) {
            contador.cancel();
            contador = null;
        }

        contador = new Contador(10000, 85);
        contador.start();

    }

    private void cargarExplicacion() {

        if (contador != null) {
            contador.cancel();
            contador = null;
        }

        Intent intent = new Intent(this, ExplicacionActivity.class);
        intent.putExtra("explicacion", preguntas.get(idPreguntaActual).getExplicacion());
        startActivity(intent);
        //activo aqui el boton por si le da antes de que cargue explicacion
        btnNext.setEnabled(true);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        Log.i("trivial", "mi boton es " + v.getId());
        boolean respuesta = false;

        switch (v.getId()) {

            case R.id.btn_false:
                //Si el timer esta parado, que no pueda responder
                if (contador == null) return;
                respuesta = false;
                contador.cancel();
                break;
            case R.id.btn_true:
                if (contador == null) return;
                respuesta = true;
                contador.cancel();
                break;
            case R.id.btn_volver:
                dialogTiempo.hide();
                cargarExplicacion();
                return;
            case R.id.btn_volver_menu:
                finish();
                return;
            case R.id.btn_next:
                //si se pasa next
                if ((idPreguntaActual + 1) < preguntas.size()) {
                    cargarPregunta();
                } else {
                    terminaJuego();
                }
                return;
        }


        if (preguntas.get(idPreguntaActual).isRespuesta() == respuesta) {
            //si responde bien, animacion del score actualizandose
            score += tiempo;
            tvScore.setText("Score: " + score);
            tvScore.startAnimation(animBlink);
            Toast.makeText(this, "¡Has acertado!", Toast.LENGTH_LONG).show();
        } else {
            //si falla y dio a verdadero, shake ese boton
            if (respuesta) {
                btnTrue.startAnimation(animShake);
            } else {
                btnFalso.startAnimation(animShake);
            }
            Toast.makeText(this, "Fallaste :C", Toast.LENGTH_LONG).show();
        }

        //pequeño delay para ver animaciones
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                cargarExplicacion();
            }
        }, 1000);


    }

    private void terminaJuego() {
        scoreFinal.setScore(score);

        db.updateScore(scoreFinal);
        tvFin.setText("¡Has terminado con un score de " + score + " puntos!");
        dialogFin.show();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (contador != null) {
            contador.cancel();
        }

    }
    //menu contextual


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //si se lo ponemos a varios, tenemos que hacer switch para asber el id
        //if(v.getId() == R.id.btn_ejemplo)
        getMenuInflater().inflate(R.menu.menu_juego, menu);
    }

    public class Contador extends CountDownTimer {


        public Contador(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }


        @Override
        public void onFinish() {

            dialogTiempo.show();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            barra.setProgress(tiempo);
            tiempo--;
        }

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

        //las mezclamos
        Collections.shuffle(preguntas);

    }

    private void inicializarElementos() {

        animBlink = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        animShake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
        //xml
        tvScore = findViewById(R.id.tv_score);
        tvScore.setText("Score: " + score);
        btnFalso = findViewById(R.id.btn_false);
        btnTrue = findViewById(R.id.btn_true);
        btnNext = findViewById(R.id.btn_next);
        this.barra = findViewById(R.id.progressBar);
        this.tvPregunta = findViewById(R.id.tv_pregunta);
        //context menu
        registerForContextMenu(tvPregunta);
        //listeners
        btnTrue.setOnClickListener(this);
        btnFalso.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        //dialog
        dialogTiempo = new Dialog(this, R.style.Theme_03_Trivial);
        dialogTiempo.setContentView(R.layout.popupview);
        dialogTiempo.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialogTiempo.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.fondo_dialog)));
        dialogFin = new Dialog(this, R.style.Theme_03_Trivial);
        dialogFin.setContentView(R.layout.dialog_fin);
        dialogFin.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialogFin.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.fondo_dialog)));

        tvFin = dialogFin.findViewById(R.id.tv_fin_puntos);
        Button btnMenu = dialogFin.findViewById(R.id.btn_volver_menu);
        btnMenu.setOnClickListener(this);
        Button btnContinuar = dialogTiempo.findViewById(R.id.btn_volver);
        btnContinuar.setOnClickListener(this);
    }


}
















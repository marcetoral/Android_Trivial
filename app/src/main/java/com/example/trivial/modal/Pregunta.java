package com.example.trivial.modal;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.util.Objects;

public class Pregunta implements Serializable {

    private int idPregunta;
    private String pregunta;
    private boolean respuesta;
    private String explicacion;

    public Pregunta(String pregunta, boolean respuesta, String explicacion) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
        this.explicacion = explicacion;
    }

    public Pregunta(int idPregunta, String pregunta, boolean respuesta, String explicacion) {
        this(pregunta, respuesta, explicacion);
        this.idPregunta = idPregunta;

    }

    //GETTERS Y SETTERS


    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getPregunta() {
        return pregunta;
    }


    public boolean isRespuesta() {
        return respuesta;
    }


    public String getExplicacion() {
        return explicacion;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pregunta pregunta = (Pregunta) o;
        return idPregunta == pregunta.idPregunta;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(idPregunta);
    }

    public void modPregunta(Pregunta p) {
        explicacion = p.getExplicacion();
        respuesta = p.isRespuesta();
        pregunta = p.getPregunta();
    }
}

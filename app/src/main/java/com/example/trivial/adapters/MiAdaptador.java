package com.example.trivial.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trivial.MantActivity;
import com.example.trivial.R;
import com.example.trivial.modal.Pregunta;
import com.example.trivial.modal.PreguntasDB;

import java.util.List;

public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.MiViewHolder> {

    private List<Pregunta> listaPreguntas;
    private MantActivity mantActivity;
    private Pregunta preguntaSeleccionada;


    public MiAdaptador(List<Pregunta> listaPreguntas, MantActivity mantActivity) {
        this.listaPreguntas = listaPreguntas;
        this.mantActivity = mantActivity;

    }

    public void addPregunta(Pregunta pregunta) {
        listaPreguntas.add(pregunta);
        notifyItemInserted(listaPreguntas.size());
    }


    public void modPregunta(Pregunta preguntaConCambios) {
        //un poco rollo pero necesito el index para notify
        for (int i = 0; i < listaPreguntas.size(); i++) {
            if (listaPreguntas.get(i).equals(preguntaSeleccionada)) {
                //asignamos a la nueva pregunta el id, que es lo unico que no cambia
                preguntaConCambios.setIdPregunta(preguntaSeleccionada.getIdPregunta());
                //obtemenos la pregunta y aplicamos cambios
                listaPreguntas.get(i).modPregunta(preguntaConCambios);
                notifyItemChanged(i);
                break;
            }
        }
    }

    @NonNull
    @Override
    public MiAdaptador.MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //inicializar inflater
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //inicializar view con inflater
        View view = inflater.inflate(R.layout.item_pregunta, parent, false);

        //crear el adaptador con el holder con la vista
        return new MiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MiAdaptador.MiViewHolder holder, int position) {
        final Pregunta pregunta = listaPreguntas.get(position);

        holder.itemView.setOnClickListener(v -> {
            preguntaSeleccionada = listaPreguntas.get(position);
            mantActivity.etPregunta.setText(preguntaSeleccionada.getPregunta());
            mantActivity.etExplicacion.setText(preguntaSeleccionada.getExplicacion());
            mantActivity.cbVerdadero.setChecked(preguntaSeleccionada.isRespuesta());
        });

        holder.tvPregunta.setText(pregunta.getPregunta());
        holder.btnBorrar.setOnClickListener(v -> {
            //click borrar
            showDialog(v.getContext(), position);

        });
    }

    private void showDialog(Context context, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Eliminar pregunta");
        builder.setMessage("¿Estás seguro?");
        builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {
            //Si confirma
            //remove de bd
            PreguntasDB db = new PreguntasDB(context);
            db.removePregunta(listaPreguntas.get(position).getIdPregunta());
            //remove de lista
            listaPreguntas.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, getItemCount());

        });
        builder.setNegativeButton(android.R.string.cancel, (dialog, which) -> {
        });
        builder.create().show();
    }

    @Override
    public int getItemCount() {
        if (listaPreguntas == null)
            return 0;
        else
            return listaPreguntas.size();
    }


    public class MiViewHolder extends RecyclerView.ViewHolder {
        public Button btnBorrar;
        public TextView tvPregunta;

        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPregunta = itemView.findViewById(R.id.tv_pregunta);
            btnBorrar = itemView.findViewById(R.id.btn_borrar);

        }


    }

    public Pregunta getPreguntaSeleccionada() {
        return preguntaSeleccionada;
    }


}

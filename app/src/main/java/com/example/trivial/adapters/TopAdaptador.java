package com.example.trivial.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trivial.R;
import com.example.trivial.modal.Score;

import java.util.List;

public class TopAdaptador extends RecyclerView.Adapter<TopAdaptador.MiViewHolder> {

    private List<Score> listaTop;


    public TopAdaptador(List<Score> listaTop) {
        this.listaTop = listaTop;

    }

    @NonNull
    @Override
    public TopAdaptador.MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //inicializar inflater
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //inicializar view con inflater
        View view = inflater.inflate(R.layout.item_top, parent, false);

        //crear el adaptador con el holder con la vista
        return new TopAdaptador.MiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MiViewHolder holder, int position) {
        //lo que hace por cada item que carga en vista:
        final Score score = listaTop.get(position);
        holder.tvScore.setText(String.valueOf(score.getScore()));
        holder.tvUser.setText(score.getUsername());
    }

    @Override
    public int getItemCount() {
        if (listaTop == null)
            return 0;
        else
            return listaTop.size();
    }


    public class MiViewHolder extends RecyclerView.ViewHolder {
        public Button btnVolver;
        public TextView tvUser, tvScore;

        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            btnVolver = itemView.findViewById(R.id.btn_volver_menu);
            tvUser = itemView.findViewById(R.id.tv_username);
            tvScore = itemView.findViewById(R.id.tv_score);

        }


    }
}

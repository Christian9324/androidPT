package com.example.acorutas.ui.Mapa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acorutas.Data.models.estacionInformacion;
import com.example.acorutas.R;

import java.util.List;

public class estacionesAdapter extends RecyclerView.Adapter<estacionesAdapter.ViewHolder> {

    public List<estacionInformacion> estacionesInfo;
    public Context context;
    public  OnItemClickListener listener;

    public estacionesAdapter(List<estacionInformacion> estacionesInfo, OnItemClickListener listener) {
        this.estacionesInfo = estacionesInfo;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_estacion, parent,
                 false);

         this.context = parent.getContext();

         return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final estacionInformacion estacionInfo = estacionesInfo.get(position);

        holder.setListener(estacionInfo, listener);

        holder.tv_estacionInfo.setText(estacionInfo.getInformacion());


    }

    @Override
    public int getItemCount() {
        return this.estacionesInfo.size();
    }

    public void add(estacionInformacion estacionInfo){
        if(estacionesInfo.contains(estacionInfo)){
            estacionesInfo.add(estacionInfo);
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public AppCompatImageView iv_imageEstacion;
        public AppCompatTextView tv_estacionInfo;
        public RelativeLayout rl_contenerdorEstaciones;

         public ViewHolder(@NonNull View itemView) {
             super(itemView);

             iv_imageEstacion = (AppCompatImageView) itemView.findViewById(R.id.image_estacion);
             tv_estacionInfo = (AppCompatTextView) itemView.findViewById(R.id.TV_estacionInfo);
             rl_contenerdorEstaciones = (RelativeLayout) itemView.findViewById(R.id.contenedorEstaciones);
         }

         void setListener(final estacionInformacion estacionInfo, final OnItemClickListener listener){

             rl_contenerdorEstaciones.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {

                     listener.onItemClick(estacionInfo);

                 }
             });

             rl_contenerdorEstaciones.setOnLongClickListener(new View.OnLongClickListener() {
                 @Override
                 public boolean onLongClick(View v) {

                     listener.onLongItemClick(estacionInfo);

                     return false;
                 }
             });

         }
     }
}

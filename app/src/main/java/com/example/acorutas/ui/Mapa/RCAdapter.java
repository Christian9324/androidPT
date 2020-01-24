package com.example.acorutas.ui.Mapa;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acorutas.Data.models.estacionInformacion;
import com.example.acorutas.R;

import java.util.ArrayList;

public class RCAdapter extends RecyclerView.Adapter<RCAdapter.ViewHolderDatos> {

    ArrayList<estacionInformacion> listaDatos;

    public RCAdapter(ArrayList<estacionInformacion> listaDatos) {
        this.listaDatos = listaDatos;
    }

    @NonNull
    @Override
    public RCAdapter.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_estacion, null, false);
        return new ViewHolderDatos(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RCAdapter.ViewHolderDatos holder, int position) {
        holder.asignarDatos(listaDatos.get(position));
    }

    @Override
    public int getItemCount() {
        return listaDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView tv_estacionInfo;
        ImageView imEstacion;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            tv_estacionInfo = (TextView) itemView.findViewById(R.id.TV_estacionInfo);
            imEstacion = (ImageView) itemView.findViewById(R.id.image_estacion);
        }

        public void asignarDatos(estacionInformacion estacionInformacion) {

            byte[] decodedString = Base64.decode(estacionInformacion.getImagen(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            imEstacion.setImageBitmap(decodedByte);
            tv_estacionInfo.setText(estacionInformacion.getInformacion());

        }
    }
}

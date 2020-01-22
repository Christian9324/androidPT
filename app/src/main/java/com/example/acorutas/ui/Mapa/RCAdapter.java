package com.example.acorutas.ui.Mapa;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RCAdapter extends RecyclerView.Adapter<RCAdapter.ViewHolderDatos> {
    @NonNull
    @Override
    public RCAdapter.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RCAdapter.ViewHolderDatos holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {


        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
        }
    }
}

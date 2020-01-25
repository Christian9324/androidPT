package com.example.acorutas.ui.Mapa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acorutas.Data.models.estacionInformacion;
import com.example.acorutas.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

import static com.example.acorutas.Data.databases.estaciones.estacionesMetro;
import static com.example.acorutas.Data.databases.estaciones.imgEstaciones;

public class BottomSheetDialog extends BottomSheetDialogFragment{

    public RecyclerView recyclerView;
    public LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.bottom_sheet_layout, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.RV_estaciones);
        linearLayout = (LinearLayout) v.findViewById(R.id.contenedorEstaciones);

        ArrayList<estacionInformacion> info = new ArrayList<>();

        for(int i = 0;i<195;i++){
            estacionInformacion infoAUX = new estacionInformacion(i+1,
                    estacionesMetro[i][2], "Estacion: \n" + estacionesMetro[i][2], imgEstaciones[i]);
            info.add(infoAUX);
        }

        recyclerView.setLayoutManager( new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        RCAdapter adapter = new RCAdapter(info);

        recyclerView.setAdapter(adapter);

        return v;
    }

}

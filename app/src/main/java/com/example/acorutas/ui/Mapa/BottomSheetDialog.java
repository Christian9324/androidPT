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

        String[] nombreEstacion = {estacionesMetro[0][2], estacionesMetro[1][2], estacionesMetro[2][2]};
        String[] informacion = {"estacion origen\n" + nombreEstacion[0], "siguiente estacion\n" + nombreEstacion[1], "estacion destino\n" + nombreEstacion[2]};
        String[] imagen = { imgEstaciones[0], imgEstaciones[1], imgEstaciones[2] };



        for(int i = 0;i<3;i++){
            estacionInformacion infoAUX = new estacionInformacion(i+1, nombreEstacion[i], informacion[i], imagen[i]);
            info.add(infoAUX);
        }

        recyclerView.setLayoutManager( new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        RCAdapter adapter = new RCAdapter(info);

        recyclerView.setAdapter(adapter);

        return v;
    }

}

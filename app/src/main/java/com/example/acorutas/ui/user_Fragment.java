package com.example.acorutas.ui;


import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acorutas.Data.Clases.GPS_ubicacion;
import com.example.acorutas.Data.databases.adminBDDhelper;
import com.example.acorutas.Data.models.PuntoMapa;
import com.example.acorutas.Data.remote.ApiUtils;
import com.example.acorutas.R;
import com.example.acorutas.Data.remote.DjangoRestApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class user_Fragment extends Fragment {

    private EditText latitud, longitud, username;
    private DjangoRestApi djangoRestApi;
    private TextView resultados;
    private Button Benviar, Brecolectar;

    private String nombre;

    public SharedPreferences prefs;

    public user_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user_, container, false);
        latitud = (EditText) v.findViewById(R.id.ET_latitud);
        longitud = (EditText) v.findViewById(R.id.ET_longitud);
        username = (EditText) v.findViewById(R.id.ET_username);
        djangoRestApi = ApiUtils.getAPIService();
        resultados = (TextView) v.findViewById(R.id.TV_resultados);
        Benviar = (Button) v.findViewById(R.id.btn_enviar);
        Brecolectar = (Button) v.findViewById(R.id.btn_recolectar);

        prefs = getActivity().getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        nombre = prefs.getString("nombre", "Chri");
        username.setText(nombre);

        Benviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnviarPunto();
            }
        });

        Brecolectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GPS_pos();
            }
        });

        return v;
    }

    public void GPS_pos(){

        GPS_ubicacion gpsUbicacion = new GPS_ubicacion(getActivity().getApplicationContext());
        Location location = gpsUbicacion.getLocation();
        if (location != null){
            latitud.setText("" + location.getLatitude());
            longitud.setText("" + location.getLongitude());
        }
    }

    public void EnviarPunto(){
        adminBDDhelper admin = new adminBDDhelper(getActivity().getApplicationContext(), "Ubicacion", null, 1);
        SQLiteDatabase BDD = admin.getWritableDatabase();

        String title = username.getText().toString();
        String S_lati = latitud.getText().toString();
        String S_longi = longitud.getText().toString();

        if( !title.isEmpty() && !S_lati.isEmpty() && !S_longi.isEmpty()){

            ContentValues datos_ubi_punto = new ContentValues();

            datos_ubi_punto.put("etiqueta", title);
            datos_ubi_punto.put("latitud", S_lati);
            datos_ubi_punto.put("longitud", S_longi);

            BDD.insert("miUbicacion", null, datos_ubi_punto);
            BDD.close();


            crearPunto(title, Double.parseDouble(S_lati), Double.parseDouble(S_longi));

        }else {
            Toast.makeText(getActivity().getApplicationContext(), "Rellena todos los campos", Toast.LENGTH_SHORT).show();
        }

    }

    private void crearPunto(String titulo, double lati, double longi){

        PuntoMapa punto_nuevo = new PuntoMapa(titulo,lati,longi);

        Call<PuntoMapa> call = djangoRestApi.crearMarcador(punto_nuevo);

        call.enqueue(new Callback<PuntoMapa>() {
            @Override
            public void onResponse(Call<PuntoMapa> call, Response<PuntoMapa> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(getActivity().getApplicationContext(), "Code: " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }

                PuntoMapa punto = response.body();

                String contenido = "";
                contenido += "Code: " + response.code() + "\n";
                contenido += "Titulo " + punto.getTitle() + "\n";
                contenido += "Latitud " + punto.getLatitud() + "\n";
                contenido += "Longitud" + punto.getLongitud() + "\n\n";

                resultados.append(contenido);

            }

            @Override
            public void onFailure(Call<PuntoMapa> call, Throwable t) {

                Toast.makeText(getActivity().getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }

}

package com.example.acorutas.ui;


import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acorutas.Data.databases.adminBDDhelper;
import com.example.acorutas.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import static com.example.acorutas.Data.databases.estaciones.estacionesMetro;

/**
 * A simple {@link Fragment} subclass.
 */
public class mapa_Fragment extends Fragment implements OnMapReadyCallback {

    SupportMapFragment mapFragment;
    private GoogleMap mMap;

    public mapa_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mapa_, container, false);

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        if (mapFragment == null){
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mapFragment = SupportMapFragment.newInstance();
            fragmentTransaction.replace(R.id.map, mapFragment).commit();

            mapFragment.getMapAsync(this);
        }

        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        adminBDDhelper admin = new adminBDDhelper(getActivity().getApplicationContext(), "Ubicacion", null, 1);
        SQLiteDatabase BDD = admin.getWritableDatabase();

        mMap = googleMap;

        List<LatLng> puntoslinea1 = new ArrayList<LatLng>();
        List<LatLng> puntoslinea2 = new ArrayList<LatLng>();
        List<LatLng> puntoslinea3 = new ArrayList<LatLng>();
        List<LatLng> puntoslinea4 = new ArrayList<LatLng>();
        List<LatLng> puntoslinea5 = new ArrayList<LatLng>();
        List<LatLng> puntoslinea6 = new ArrayList<LatLng>();
        List<LatLng> puntoslinea7 = new ArrayList<LatLng>();
        List<LatLng> puntoslinea8 = new ArrayList<LatLng>();
        List<LatLng> puntoslinea9 = new ArrayList<LatLng>();
        List<LatLng> puntoslineaA = new ArrayList<LatLng>();
        List<LatLng> puntoslineaB = new ArrayList<LatLng>();
        List<LatLng> puntoslinea12 = new ArrayList<LatLng>();

        double miLatitud = 19.504810;
        double miLongitud = -99.5050;



        // Add a marker in Sydney, Australia, and move the camera.
        LatLng CDMX = new LatLng(19.403232, -99.135119);
        //mMap.addMarker(new MarkerOptions().position(upiita).title("Upiita"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(CDMX));

        for (int i = 0; i<20; i++){
            puntoslinea1.add(new LatLng( Double.parseDouble(estacionesMetro[i][3]), Double.parseDouble(estacionesMetro[i][4])));
        }
        for (int i = 20; i<44; i++){
            puntoslinea2.add(new LatLng( Double.parseDouble(estacionesMetro[i][3]), Double.parseDouble(estacionesMetro[i][4])));
        }
        for (int i = 44; i<65; i++){
            puntoslinea3.add(new LatLng( Double.parseDouble(estacionesMetro[i][3]), Double.parseDouble(estacionesMetro[i][4])));
        }
        for (int i = 65; i<75; i++){
            puntoslinea4.add(new LatLng( Double.parseDouble(estacionesMetro[i][3]), Double.parseDouble(estacionesMetro[i][4])));
        }
        for (int i = 75; i<88; i++){
            puntoslinea5.add(new LatLng( Double.parseDouble(estacionesMetro[i][3]), Double.parseDouble(estacionesMetro[i][4])));
        }
        for (int i = 88; i<99; i++){
            puntoslinea6.add(new LatLng( Double.parseDouble(estacionesMetro[i][3]), Double.parseDouble(estacionesMetro[i][4])));
        }
        for (int i = 99; i<113; i++){
            puntoslinea7.add(new LatLng( Double.parseDouble(estacionesMetro[i][3]), Double.parseDouble(estacionesMetro[i][4])));
        }
        for (int i = 113; i<132; i++){
            puntoslinea8.add(new LatLng( Double.parseDouble(estacionesMetro[i][3]), Double.parseDouble(estacionesMetro[i][4])));
        }
        for (int i = 132; i<144; i++){
            puntoslinea9.add(new LatLng( Double.parseDouble(estacionesMetro[i][3]), Double.parseDouble(estacionesMetro[i][4])));
        }
        for (int i = 144; i<154; i++){
            puntoslineaA.add(new LatLng( Double.parseDouble(estacionesMetro[i][3]), Double.parseDouble(estacionesMetro[i][4])));
        }
        for (int i = 154; i<175; i++){
            puntoslineaB.add(new LatLng( Double.parseDouble(estacionesMetro[i][3]), Double.parseDouble(estacionesMetro[i][4])));
        }
        for (int i = 175; i<195; i++){
            puntoslinea12.add(new LatLng( Double.parseDouble(estacionesMetro[i][3]), Double.parseDouble(estacionesMetro[i][4])));
        }


        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(miLatitud, miLongitud))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_account_circle))
                .title("Mi ubicacion"));


        Polyline linea1 = mMap.addPolyline(new PolylineOptions()
                .addAll(puntoslinea1)
                .width(11)
                .color(getResources().getColor(R.color.linea1C)));

        Polyline linea2 = mMap.addPolyline(new PolylineOptions()
                .addAll(puntoslinea2)
                .width(11)
                .color(getResources().getColor(R.color.linea2C)));
        Polyline linea3 = mMap.addPolyline(new PolylineOptions()
                .addAll(puntoslinea3)
                .width(11)
                .color(getResources().getColor(R.color.linea3C)));
        Polyline linea4 = mMap.addPolyline(new PolylineOptions()
                .addAll(puntoslinea4)
                .width(11)
                .color(getResources().getColor(R.color.linea4C)));
        Polyline linea5 = mMap.addPolyline(new PolylineOptions()
                .addAll(puntoslinea5)
                .width(11)
                .color(getResources().getColor(R.color.linea5C)));
        Polyline linea6 = mMap.addPolyline(new PolylineOptions()
                .addAll(puntoslinea6)
                .width(11)
                .color(getResources().getColor(R.color.linea6C)));
        Polyline linea7 = mMap.addPolyline(new PolylineOptions()
                .addAll(puntoslinea7)
                .width(11)
                .color(getResources().getColor(R.color.linea7C)));
        Polyline linea8 = mMap.addPolyline(new PolylineOptions()
                .addAll(puntoslinea8)
                .width(11)
                .color(getResources().getColor(R.color.linea8C)));
        Polyline linea9 = mMap.addPolyline(new PolylineOptions()
                .addAll(puntoslinea9)
                .width(11)
                .color(getResources().getColor(R.color.linea9C)));
        Polyline lineaA = mMap.addPolyline(new PolylineOptions()
                .addAll(puntoslineaA)
                .width(11)
                .color(getResources().getColor(R.color.lineaAC)));
        Polyline lineaB = mMap.addPolyline(new PolylineOptions()
                .addAll(puntoslineaB)
                .width(11)
                .color(getResources().getColor(R.color.lineaBC)));
        Polyline linea12 = mMap.addPolyline(new PolylineOptions()
                .addAll(puntoslinea12)
                .width(11)
                .color(getResources().getColor(R.color.linea12C)));

        mMap.setMinZoomPreference(11.0f);
    }
}
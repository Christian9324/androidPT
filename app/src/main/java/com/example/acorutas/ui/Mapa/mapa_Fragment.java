package com.example.acorutas.ui.Mapa;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

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
import java.util.regex.Pattern;

import static com.example.acorutas.Data.databases.estaciones.estacionesMetro;

/**
 * A simple {@link Fragment} subclass.
 */
public class mapa_Fragment extends Fragment implements OnMapReadyCallback {

    SupportMapFragment mapFragment;
    private GoogleMap mMap;

    public List<Integer> estacionesLineasImprimir = new ArrayList<>();

    public int accion = 0;
    public String[] estacionesString;
    public String auxString;
    public String[] auxStringV;

    public List<LatLng> miRuta = new ArrayList<LatLng>();

    public Button btn_bottomSheetLayout;

    public mapa_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mapa_, container, false);

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        btn_bottomSheetLayout = (Button) v.findViewById(R.id.infoMapa);

        try {

            String info = getArguments().getString("cadena_prueba");

            Log.i("informacion", info);

            if(info.equals("[E]")){
                Log.i("peticion", "Espera");
                accion = 0;

            }else{
                Log.i("peticion", "Aceptada");

                estacionesString = info.split(", ");

                int indice = estacionesString.length - 1;

                auxString = estacionesString[0];
                auxStringV =  auxString.split(Pattern.quote("["));
                estacionesString[0] = auxStringV[1];


                auxString = estacionesString[indice];
                auxStringV =  auxString.split(Pattern.quote("]"));
                estacionesString[indice] = auxStringV[0];


                for(int index = 0; index < estacionesString.length ; index++){

                    for( int index2 = 0; index2 < 195; index2++) {

                        if (estacionesMetro[index2][5].equals(estacionesString[index])) {

                            miRuta.add(new LatLng(Double.parseDouble(estacionesMetro[index2][3]), Double.parseDouble(estacionesMetro[index2][4])));
                            estacionesLineasImprimir.add(Integer.parseInt(estacionesMetro[index2][0]));
                            Log.i("estacion", estacionesMetro[index2][0]);
                            continue;

                        }

                    }
                }

                accion = 1;

            }

        } catch (Exception e){

            Log.i("problem" , e.getMessage());

        }

        if (mapFragment == null){
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mapFragment = SupportMapFragment.newInstance();
            fragmentTransaction.replace(R.id.map, mapFragment).commit();

            mapFragment.getMapAsync(this);
        }

        btn_bottomSheetLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(accion == 1) {

                    BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(1, estacionesLineasImprimir );

                    Log.i("numero de estaciones", ""+estacionesLineasImprimir.size());

                    bottomSheetDialog.show(getFragmentManager(), "Ejemplo de bottoms sheet");

                }else{

                    BottomSheetDialog bottomSheetDialog = new BottomSheetDialog();

                    bottomSheetDialog.show(getFragmentManager(), "Ejemplo de bottoms sheet");
                    Log.i("info","entro aqui");

                }
            }
        });

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

        double miLatitud = 19.5113961;
        double miLongitud = -99.1269034;



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


        Cursor fila = BDD.rawQuery
                ("select etiqueta,latitud,longitud from miUbicacion order by id desc limit 1", null);

        if(fila.moveToFirst()){

            miLatitud = Double.parseDouble(fila.getString(1));
            miLongitud = Double.parseDouble(fila.getString(2));

            BDD.close();

        } else {

            Toast.makeText( getActivity().getApplicationContext(),
                    "No se encontro la ultima posición", Toast.LENGTH_SHORT).show();
            BDD.close();

            miLatitud = 19.5113961;
            miLongitud = -99.1269034;

        }


        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(miLatitud, miLongitud))
                .icon(bitmapDescriptorFromVector(getActivity(), R.drawable.ic_account_circle))
                .title("Mi ubicacion"));

        if ( accion == 1){

            Polyline miLinea = mMap.addPolyline(new PolylineOptions()
                    .addAll(miRuta)
                    .width(33)
                    .color(getResources().getColor(R.color.miLineaC)));

            Log.i("Linea", "Se pinto");

        }

        Polyline linea1 = mMap.addPolyline(new PolylineOptions()
                .addAll(puntoslinea1)
                .width(10)
                .color(getResources().getColor(R.color.linea1C)));

        Polyline linea2 = mMap.addPolyline(new PolylineOptions()
                .addAll(puntoslinea2)
                .width(10)
                .color(getResources().getColor(R.color.linea2C)));
        Polyline linea3 = mMap.addPolyline(new PolylineOptions()
                .addAll(puntoslinea3)
                .width(10)
                .color(getResources().getColor(R.color.linea3C)));
        Polyline linea4 = mMap.addPolyline(new PolylineOptions()
                .addAll(puntoslinea4)
                .width(10)
                .color(getResources().getColor(R.color.linea4C)));
        Polyline linea5 = mMap.addPolyline(new PolylineOptions()
                .addAll(puntoslinea5)
                .width(10)
                .color(getResources().getColor(R.color.linea5C)));
        Polyline linea6 = mMap.addPolyline(new PolylineOptions()
                .addAll(puntoslinea6)
                .width(10)
                .color(getResources().getColor(R.color.linea6C)));
        Polyline linea7 = mMap.addPolyline(new PolylineOptions()
                .addAll(puntoslinea7)
                .width(10)
                .color(getResources().getColor(R.color.linea7C)));
        Polyline linea8 = mMap.addPolyline(new PolylineOptions()
                .addAll(puntoslinea8)
                .width(10)
                .color(getResources().getColor(R.color.linea8C)));
        Polyline linea9 = mMap.addPolyline(new PolylineOptions()
                .addAll(puntoslinea9)
                .width(10)
                .color(getResources().getColor(R.color.linea9C)));
        Polyline lineaA = mMap.addPolyline(new PolylineOptions()
                .addAll(puntoslineaA)
                .width(10)
                .color(getResources().getColor(R.color.lineaAC)));
        Polyline lineaB = mMap.addPolyline(new PolylineOptions()
                .addAll(puntoslineaB)
                .width(10)
                .color(getResources().getColor(R.color.lineaBC)));
        Polyline linea12 = mMap.addPolyline(new PolylineOptions()
                .addAll(puntoslinea12)
                .width(10)
                .color(getResources().getColor(R.color.linea12C)));



        mMap.setMinZoomPreference(11.0f);
    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}

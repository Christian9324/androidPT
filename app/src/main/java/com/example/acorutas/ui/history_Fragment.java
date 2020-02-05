package com.example.acorutas.ui;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.acorutas.Data.databases.adminBDDhelper;
import com.example.acorutas.R;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.acorutas.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.regex.Pattern;

import static com.example.acorutas.Data.databases.estaciones.estacionesMetro;

/**
 * A simple {@link Fragment} subclass.
 */
public class history_Fragment extends Fragment implements OnMapReadyCallback {

    private WebView Wmapa;

    SupportMapFragment mapFragmentHistory;
    private GoogleMap mMapHistory;

    public history_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_history_, container, false);

        /*Wmapa = (WebView) v.findViewById(R.id.WV_mapa);
        Wmapa.loadUrl("https://www.google.com/maps/d/viewer?mid=1eDeBiKh_C2h9i5agIXyxrYzGLFaYR3kb&ll=19.410357954684365%2C-99.08837564999999&z=11");

        // this will enable the javascipt.
        Wmapa.getSettings().setJavaScriptEnabled(true);

        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        Wmapa.setWebViewClient(new WebViewClient());*/


        mapFragmentHistory = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.historyMap);
        if (mapFragmentHistory == null){
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mapFragmentHistory = SupportMapFragment.newInstance();
            fragmentTransaction.replace(R.id.historyMap, mapFragmentHistory).commit();

            mapFragmentHistory.getMapAsync(this);
        }

        return v;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        adminBDDhelper admin = new adminBDDhelper(getActivity().getApplicationContext(), "Ubicacion", null, 1);
        SQLiteDatabase BDD = admin.getWritableDatabase();

        mMapHistory = googleMap;

        // Add a marker in CDMX, Mexico, and move the camera.
        LatLng CDMX = new LatLng(19.403232, -99.105119);
        //mMap.addMarker(new MarkerOptions().position(upiita).title("Upiita"));
        mMapHistory.moveCamera(CameraUpdateFactory.newLatLng(CDMX));
        mMapHistory.setMinZoomPreference(11.0f);

        Cursor fila = BDD.rawQuery
                ("select ruta from misRutas order by id desc limit 5", null);

        if(fila.moveToFirst()){

            /*estacionesString = info.split(", ");

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
                        //continue;
                        break;

                    }

                }
            }*/

            BDD.close();

        } else {

            Toast.makeText( getActivity().getApplicationContext(),
                    "No se encontraron las rutas", Toast.LENGTH_SHORT).show();
            BDD.close();

        }

    }
}

package com.example.acorutas.ui;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acorutas.Data.databases.adminBDDhelper;
import com.example.acorutas.R;

import static com.example.acorutas.Data.databases.estaciones.estacionesMetro;

/**
 * A simple {@link Fragment} subclass.
 */
public class rutas_Fragment extends Fragment {

    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;
    private ImageView mImageView;
    private TextView tv_consulta;

    private WebView Wmapa;

    public rutas_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rutas_, container, false);

        Wmapa = (WebView) v.findViewById(R.id.mapaMetro);
        tv_consulta = (TextView) v.findViewById(R.id.consulta);
        Wmapa.loadUrl("https://www.metro.cdmx.gob.mx/storage/app/media/red/plano_red19ok.png");
        Wmapa.getSettings().setJavaScriptEnabled(true);
        Wmapa.setWebViewClient(new WebViewClient());

        Cercanos();

        return v;
    }


    public void Cercanos(){

        adminBDDhelper admin = new adminBDDhelper(getActivity().getApplicationContext(), "Ubicacion", null, 1);
        SQLiteDatabase BDD = admin.getWritableDatabase();

        double miLat = 0;
        double miLong = 0;

        double[][] tresM;

        Cursor fila = BDD.rawQuery
                ("select etiqueta,latitud,longitud from miUbicacion order by id desc limit 1", null);

        if(fila.moveToFirst()){

            miLat = Double.parseDouble(fila.getString(1));
            miLong = Double.parseDouble(fila.getString(2));

            tv_consulta.append(fila.getString(0));
            tv_consulta.append(Double.toString(miLat));
            tv_consulta.append(Double.toString(miLong));

            BDD.close();

        } else {

            Toast.makeText( getActivity().getApplicationContext(), "No se encontro la estacion", Toast.LENGTH_SHORT).show();
            BDD.close();

        }

        tresM = mejores(miLat,miLong);



    }



    public double[][] mejores(double latitud, double longitud)
    {

        double[][] lista;
        double[][] mejores = new double[3][2];

        lista = distancia(latitud, longitud);

        lista = burbuja(lista);

        mejores[0] = lista[1];
        mejores[1] = lista[2];
        mejores[2] = lista[3];

        return mejores;

    }

    public double[][] distancia(double latitud, double longitud){

        double[][] distancia = new double[196][2];
        int indice;
        double la;
        double lo;

        for ( String[] dato : estacionesMetro){
            indice = Integer.parseInt(dato[0]);
            la = Double.parseDouble(dato[3]);
            lo = Double.parseDouble(dato[4]);

            distancia[indice][0] = indice;
            distancia[indice][1] = Math.sqrt(Math.pow(latitud-la ,2) + Math.pow(longitud-lo ,2));
        }

        return distancia;

    }

    public double[][] burbuja(double[][] arreglo)
    {
        double auxiliar0;
        double auxiliar1;
        double[][] arregloO;

        for(int i = 2; i < 196; i++)
        {
            for(int j = 0;j < 196-i;j++)
            {
                if(arreglo[j][1] > arreglo[j+1][1])
                {
                    auxiliar0 = arreglo[j][0];
                    auxiliar1 = arreglo[j][1];

                    arreglo[j][0] = arreglo[j+1][0];
                    arreglo[j][1] = arreglo[j+1][1];
                    arreglo[j+1][0] = auxiliar1;
                    arreglo[j+1][1] = auxiliar0;
                }
            }
        }

        arregloO = arreglo;
        return arregloO;
    }

}
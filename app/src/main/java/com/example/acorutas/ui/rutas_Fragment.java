package com.example.acorutas.ui;


import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acorutas.Data.databases.adminBDDhelper;
import com.example.acorutas.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.example.acorutas.Data.databases.estaciones.estacionesMetro;

/**
 * A simple {@link Fragment} subclass.
 */
public class rutas_Fragment extends Fragment {

    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;
    private ImageView mImageView;
    private TextView tv_consulta;
    private AutoCompleteTextView estDes;
    private Spinner estOrig;

    private WebView Wmapa;

    public rutas_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rutas_, container, false);

        tv_consulta = (TextView) v.findViewById(R.id.consulta);
        Wmapa = (WebView) v.findViewById(R.id.mapaMetro);
        
        //Wmapa.loadUrl("https://www.metro.cdmx.gob.mx/storage/app/media/red/plano_red19ok.png");
        //Wmapa.getSettings().setJavaScriptEnabled(true);
        //Wmapa.setWebViewClient(new WebViewClient());

        String url = "<img src='plano_red19ok.png' " + "' style='width:100%'" + "/>";
        Wmapa.loadDataWithBaseURL("file:///android_res/drawable/", url , "text/html", "utf-8", null);

        Wmapa.getSettings().setLoadWithOverviewMode(true);
        Wmapa.getSettings().setUseWideViewPort(true);
        Wmapa.getSettings().setBuiltInZoomControls(true);

        estDes = (AutoCompleteTextView) v.findViewById(R.id.ACTV_estacionDestino);
        estOrig = (Spinner) v.findViewById(R.id.Spinner_estacionInicio);

        ACTV_datosIniciales();

        return v;
    }


    public void ACTV_datosIniciales(){

        adminBDDhelper admin = new adminBDDhelper
                (getActivity().getApplicationContext(), "Ubicacion", null, 1);
        SQLiteDatabase BDD = admin.getWritableDatabase();

        String[] Estacion = new String[195];
        // Inicializas array
        for(int i = 0; i<195; i++){

            Estacion[i] = estacionesMetro[i][2];

        }


        double miLat = 0;
        double miLong = 0;

        double[][] tresM;

        //----------  Hacer Query a la BDD

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

            Toast.makeText( getActivity().getApplicationContext(),
                    "No se encontro la estacion", Toast.LENGTH_SHORT).show();
            BDD.close();

        }

        //-----------  Generar ID de las primeras 3 estaciones mas cercanas

        tresM = mejores(miLat,miLong);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getActivity().getApplicationContext(),
                        android.R.layout.simple_dropdown_item_1line, Estacion);

        estDes.setThreshold(1);
        estDes.setAdapter(adapter);

        // numero de estacion
        //Log.d("dato mejor" , String.valueOf(tresM[0][0]));

        String[] datos_spinner = new String[3];
        datos_spinner[0] = estacionesMetro[(int)tresM[0][0] - 1][2];
        datos_spinner[1] = estacionesMetro[(int)tresM[1][0] - 1][2];
        datos_spinner[2] = estacionesMetro[(int)tresM[2][0] - 1][2];

        estOrig.setAdapter(new ArrayAdapter<String>
                (getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, datos_spinner));

        //Log.i("datos", String.valueOf(tresM[0][0]));
        //Log.i("datos", String.valueOf(tresM[1][0]));
        //Log.i("datos", String.valueOf(tresM[1][0]));

    }



    public double[][] mejores(double latitud, double longitud)
    {

        double[][] lista;
        double[][] mejores = new double[3][2];

        lista = distancia(latitud, longitud);

        lista = burbuja(lista);

        //mejores 3 sin repeticiones
        /*
        HashSet<double[][]> listaC = new HashSet<>();

        for (int s = 1; s < 196 ; s++){
            if (!listaC.contains(lista[1])){
                listaC.add(lista[s]);
            }
        }

        Log.i("datos ", String.valueOf(listaC[0][1]));
         */
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

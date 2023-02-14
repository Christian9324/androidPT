package com.example.acorutas.Data.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.acorutas.estaciones;

//import static com.example.acorutas.estaciones.estacionesMetro;



public class adminBDDhelper extends SQLiteOpenHelper {

    public adminBDDhelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("create table Metro(id integer primary key, linea text ,nombreEstacion text, latitud real, longitud real)");

        db.execSQL("create table miUbicacion(id integer primary key autoincrement, etiqueta text ,latitud real, longitud real)");

        db.execSQL("create table misRutas(id integer primary key autoincrement, estacionInicio real,estacionDestino real, ruta text)");



//        for(String[] datosM : estaciones.estaciones){
//            db.execSQL("INSERT INTO Metro VALUES " +
//                    "("+ datosM[0] + ",'" + datosM[1] + "','" + datosM[2] + "'," + datosM[3] + "," + datosM[4] + ")");
//        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
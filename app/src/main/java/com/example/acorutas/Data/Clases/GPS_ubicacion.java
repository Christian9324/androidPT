package com.example.acorutas.Data.Clases;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class GPS_ubicacion implements LocationListener {

    Context context;
    public GPS_ubicacion(Context c){
        context = c;
    }

    public Location getLocation(){

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(context, "Permiso no permitido", Toast.LENGTH_SHORT).show();
            return null;
        }

        LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);

        boolean isGPSEnable = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if(isGPSEnable){

            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 6000, 10, this);
            Location l = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            return l;

        }else {
            Toast.makeText(context, "Por favor Activa el GPS", Toast.LENGTH_LONG).show();
        }

        return null;
    }


    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
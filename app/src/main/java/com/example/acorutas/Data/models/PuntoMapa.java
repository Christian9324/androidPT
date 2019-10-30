package com.example.acorutas.Data.models;

import com.google.gson.annotations.SerializedName;

public class PuntoMapa {

    @SerializedName("title")
    private String title;

    @SerializedName("latitud")
    private double latitud;

    @SerializedName("longitud")
    private double longitud;

    public String getTitle() {
        return title;
    }

    public PuntoMapa(String title, double latitud, double longitud) {
        this.title = title;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    @Override
    public String toString() {
        return "PuntoMapa{" +
                "title='" + title + '\'' +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                '}';
    }
}
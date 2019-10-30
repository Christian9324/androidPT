package com.example.acorutas.Data.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ruta {

    @SerializedName("estacionInicio")
    @Expose
    private Integer estacionInicio;
    @SerializedName("estacionDestino")
    @Expose
    private Integer estacionDestino;

    @SerializedName("rutaNodos")
    private String rutaNodos;

    public Ruta(Integer estacionInicio, Integer estacionDestino) {
        this.estacionInicio = estacionInicio;
        this.estacionDestino = estacionDestino;
    }

    public Integer getEstacionInicio() {
        return estacionInicio;
    }

    public void setEstacionInicio(Integer estacionInicio) {
        this.estacionInicio = estacionInicio;
    }

    public Integer getEstacionDestino() {
        return estacionDestino;
    }

    public void setEstacionDestino(Integer estacionDestino) {
        this.estacionDestino = estacionDestino;
    }

    public String getRutaNodos() {
        return rutaNodos;
    }

    public void setRutaNodos(String rutaNodos) {
        this.rutaNodos = rutaNodos;
    }

    @Override
    public String toString() {
        return "Ruta{" +
                "estacionInicio=" + estacionInicio +
                ", estacionDestino=" + estacionDestino +
                ", rutaNodos='" + rutaNodos + '\'' +
                '}';
    }
}
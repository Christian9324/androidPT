package com.example.acorutas.Data.models;

import java.util.Objects;

public class estacionInformacion {

    public int id;
    public String nombre;
    public String informacion;
    public String imagen;

    public estacionInformacion() {
    }

    public estacionInformacion(int id, String nombre, String informacion, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.informacion = informacion;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof estacionInformacion)) return false;
        estacionInformacion that = (estacionInformacion) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

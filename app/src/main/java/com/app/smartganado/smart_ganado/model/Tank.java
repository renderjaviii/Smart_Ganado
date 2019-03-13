package com.app.smartganado.smart_ganado.model;

import java.io.Serializable;

public class Tank implements Serializable {

    private int id;
    private String nombre;
    private Double capacidad;

    public Tank() {
    }

    public Tank(int Id, String nombre, Double Capacidad) {
        this.id = Id;
        this.nombre = nombre;
        this.capacidad = Capacidad;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getCapacidad() {
        return capacidad;
    }

    public void setId(int Id) {
        this.id = Id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCapacidad(Double Capacidad) {
        this.capacidad = Capacidad;
    }

}

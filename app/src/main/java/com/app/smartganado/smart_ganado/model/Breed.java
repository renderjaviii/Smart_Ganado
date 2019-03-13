package com.app.smartganado.smart_ganado.model;

import java.io.Serializable;


//Raza
public class Breed implements Serializable {

    private int id;
    private String nombre;

    public Breed(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

}

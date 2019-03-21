package com.app.smartganado.smart_ganado.model.vo;

import java.io.Serializable;

public class Purpose implements Serializable {

    private int id;
    private String nombre;

    public Purpose(int id, String nombre) {
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


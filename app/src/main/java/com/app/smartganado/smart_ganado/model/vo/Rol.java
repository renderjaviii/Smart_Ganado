package com.app.smartganado.smart_ganado.model.vo;

import java.io.Serializable;

public class Rol implements Serializable {


    private Integer id;

    private String name;



    public Rol(String name) {
        this.name = name;
    }

    public Rol() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

}
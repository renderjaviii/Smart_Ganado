package com.app.smartganado.smart_ganado.model.vo;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

//Raza
public class Breed implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer id;


    private String name;



    public Breed(String name) {

        this.name = name;
    }

    public Breed() {
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

    public void setName(String name) {
        this.name = name;
    }

}

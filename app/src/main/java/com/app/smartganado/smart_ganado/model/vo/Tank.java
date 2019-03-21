package com.app.smartganado.smart_ganado.model.vo;

import java.io.Serializable;

public class Tank implements Serializable {
    private static final long serialVersionUID = 1L;


    private int id;

    private double capacity;

    private String name;

    private Integer idEstate;



    public Tank(double capacity, String name, Integer idEstate) {
        this.capacity = capacity;
        this.name = name;
        this.idEstate=idEstate;
    }

    public Integer getIdEstate() {
        return idEstate;
    }

    public void setIdEstate(Integer idEstate) {
        this.idEstate = idEstate;
    }

    public Tank() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCapacity() {
        return this.capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
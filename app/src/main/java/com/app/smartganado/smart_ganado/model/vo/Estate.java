package com.app.smartganado.smart_ganado.model.vo;


import java.io.Serializable;

public class Estate implements Serializable {


    private Integer id;

    private double area;

    private String location;

    private String name;

    private byte[] photo;

    private Long phoneUser;



    public Long getPhoneUser() {
        return phoneUser;
    }

    public void setPhoneUser(Long phoneUser) {
        this.phoneUser = phoneUser;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Estate(double area, String location, String name, byte[] photo, Long phoneUser) {
        this.area = area;
        this.location = location;
        this.name = name;
        this.photo = photo;
        this.phoneUser = phoneUser;
    }

    public Estate() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getArea() {
        return this.area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getPhoto() {
        return this.photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

}
package com.app.smartganado.smart_ganado.model.vo;

import java.io.Serializable;

public class Cattle implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;

    private Integer age;

    private String details;

    private Integer idBreed;

    private Integer idGender;

    private Integer idLot;

    private Integer idPurpose;

    private String name;

    private Integer id_estate;

    private byte[] photo;

    private double weight;



    public Integer getId_estate() {
        return id_estate;
    }

    public void setId_estate(Integer id_estate) {
        this.id_estate = id_estate;
    }

    public Cattle() {
    }

    public Cattle(Integer age, String details, Integer idBreed, Integer idGender, Integer idLot, Integer idPurpose,
                  String name, byte[] photo, double weight, Integer id_estate) {

        this.age = age;
        this.details = details;
        this.idBreed = idBreed;
        this.idGender = idGender;
        this.idLot = idLot;
        this.idPurpose = idPurpose;
        this.name = name;
        this.photo = photo;
        this.weight = weight;
        this.id_estate=id_estate;
    }



    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getIdBreed() {
        return this.idBreed;
    }

    public void setIdBreed(Integer idBreed) {
        this.idBreed = idBreed;
    }

    public Integer getIdGender() {
        return this.idGender;
    }

    public void setIdGender(Integer idGender) {
        this.idGender = idGender;
    }

    public Integer getIdLot() {
        return this.idLot;
    }

    public void setIdLot(Integer idLot) {
        this.idLot = idLot;
    }

    public Integer getIdPurpose() {
        return this.idPurpose;
    }

    public void setIdPurpose(Integer idPurpose) {
        this.idPurpose = idPurpose;
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

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

}
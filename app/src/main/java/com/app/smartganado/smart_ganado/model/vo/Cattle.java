package com.app.smartganado.smart_ganado.model.vo;

import java.io.Serializable;
import java.util.Arrays;


/**
 * The persistent class for the cattle database table.
 */
public class Cattle implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer age;
    private Integer code;
    private String details;
    private Integer idBreed;
    private Integer idEstate;
    private Integer idGender;
    private Integer idLot;
    private Integer idPurpose;
    private String name;
    private byte[] photo;
    private Double weight;

    public Cattle() {
    }

    public Cattle(Integer id, Integer age, Integer code, String details, Integer idBreed, Integer idEstate, Integer idGender, Integer idLot, Integer idPurpose, String name, byte[] photo, Double weight) {
        this.id = id;
        this.age = age;
        this.code = code;
        this.details = details;
        this.idBreed = idBreed;
        this.idEstate = idEstate;
        this.idGender = idGender;
        this.idLot = idLot;
        this.idPurpose = idPurpose;
        this.name = name;
        this.photo = photo;
        this.weight = weight;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
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

    public Integer getIdEstate() {
        return this.idEstate;
    }

    public void setIdEstate(Integer idEstate) {
        this.idEstate = idEstate;
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

    public Double getWeight() {
        return this.weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Cattle{" +
                "id=" + id +
                ", age=" + age +
                ", code=" + code +
                ", details='" + details + '\'' +
                ", idBreed=" + idBreed +
                ", idEstate=" + idEstate +
                ", idGender=" + idGender +
                ", idLot=" + idLot +
                ", idPurpose=" + idPurpose +
                ", name='" + name + '\'' +
                ", photo=" + Arrays.toString(photo) +
                ", weight=" + weight +
                '}';
    }
}
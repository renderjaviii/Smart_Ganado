package com.app.smartganado.smart_ganado.model.vo;

import java.io.Serializable;

public class UserApp implements Serializable {
    private static final long serialVersionUID = 1L;


    private Long phone;

    private String email;

    private Integer idRol;

    private String name;

    private byte[] photo;
    private String password;



    public UserApp(Long phone, String email, Integer idRol, String name, byte[] photo, String password) {
        this.phone = phone;
        this.email = email;
        this.idRol = idRol;
        this.name = name;
        this.photo = photo;
        this.password=password;
    }

    public UserApp() {
    }

    public Long getPhone() {
        return this.phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdRol() {
        return this.idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
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

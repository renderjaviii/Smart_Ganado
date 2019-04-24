package com.app.smartganado.smart_ganado.model.vo;

import java.io.Serializable;
import java.util.Arrays;


/**
 * The persistent class for the user_app database table.
 */

public class UserApp implements Serializable {
    private static final long serialVersionUID = 1L;


    private Long phone;
    private String email;
    private Integer idRol;
    private String name;
    private String password;
    private Byte[] photo;

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

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Byte[] getPhoto() {
        return this.photo;
    }

    public void setPhoto(Byte[] photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "UserApp{" +
                "phone=" + phone +
                ", email='" + email + '\'' +
                ", idRol=" + idRol +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
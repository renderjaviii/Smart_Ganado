package com.app.smartganado.smart_ganado.model.vo;

import java.io.Serializable;

public class User implements Serializable {

    private int telefono;
    private String nombre;
    private String correo;
    private String foto;
    private int id_Rol;
    public String contraseña;

    public User(int telefono, String nombre, String correo, String foto, int id_rol, String contraseña) {
        this.telefono = telefono;
        this.nombre = nombre;
        this.correo = correo;
        this.foto = foto;
        this.id_Rol = id_rol;
        this.contraseña = contraseña;
    }

    public User() {
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getFoto() {
        return foto;
    }

    public int getId_Rol() {
        return id_Rol;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setId_Rol(int id_Rol) {
        this.id_Rol = id_Rol;
    }

}

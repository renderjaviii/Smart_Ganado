package com.app.smartganado.smart_ganado.model.vo;

public class Estate {

    private int id;
    private String nombre;
    private double area;
    private String foto;
    private String ubicacion;

    public Estate(int id, String nombre, double area, String foto, String ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.area = area;
        this.foto = foto;
        this.ubicacion = ubicacion;
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

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return "{" + nombre + ", " + id + ", " + area + ", " + foto + "}";
    }
}
package com.app.smartganado.smart_ganado.model;

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


    @Override
    public String toString() {
        return "{" + nombre + ", " + id + ", " + area + ", " + foto + "}";
    }
}
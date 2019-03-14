package com.app.smartganado.smart_ganado.model.vo;

public class Cattle {

    private int id;
    private String nombre;
    private int id_Lote;
    private int id_Raza;
    private int id_Proposito;
    private int id_Genero;
    private int edad;
    private double peso;
    private String foto;
    private String detalles;


    public Cattle(int id, String nombre, int id_Lote, int id_Raza, int id_Proposito, int id_Genero, int edad, double peso, String foto, String detalles) {
        this.id = id;
        this.nombre = nombre;
        this.id_Lote = id_Lote;
        this.id_Raza = id_Raza;
        this.id_Proposito = id_Proposito;
        this.id_Genero = id_Genero;
        this.edad = edad;
        this.peso = peso;
        this.foto = foto;
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "id: " + String.valueOf(id) + ", nombre: " + nombre;
    }
}

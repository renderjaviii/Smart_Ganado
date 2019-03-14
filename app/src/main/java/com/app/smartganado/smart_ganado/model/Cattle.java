package com.app.smartganado.smart_ganado.model;

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

    public Cattle(int id, String nombre, int id_Lote, int id_Raza, int id_Proposito, int id_Genero, int edad, double peso, String foto, String detalles ) {
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

    public Cattle() {
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

    public int getId_Lote() {
        return id_Lote;
    }

    public void setId_Lote(int id_Lote) {
        this.id_Lote = id_Lote;
    }

    public int getId_Raza() {
        return id_Raza;
    }

    public void setId_Raza(int id_Raza) {
        this.id_Raza = id_Raza;
    }

    public int getId_Proposito() {
        return id_Proposito;
    }

    public void setId_Proposito(int id_Proposito) {
        this.id_Proposito = id_Proposito;
    }

    public int getId_Genero() {
        return id_Genero;
    }

    public void setId_Genero(int id_Genero) {
        this.id_Genero = id_Genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "id: " + String.valueOf(id) + ", nombre: " + nombre;
    }
}

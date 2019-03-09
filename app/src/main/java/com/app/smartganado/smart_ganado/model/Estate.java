package com.app.smartganado.model;

//Entity class from database
public class Estate {

    public int Id;
    public String Nombre;
    public String Foto;
    public int Area;

    public Estate(int id, String nombre, String foto, int area) {
        Id = id;
        Nombre = nombre;
        Foto = foto;
        Area = area;
    }
}

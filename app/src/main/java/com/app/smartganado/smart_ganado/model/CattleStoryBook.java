package com.app.smartganado.smart_ganado.model;

import java.io.Serializable;


public class CattleStoryBook implements Serializable {


    private int id;
    private String id_Ganado;
    private String nombre_Historia;
    private String fecha;
    private String detalles;

    public CattleStoryBook(int id, String id_Ganado, String nombre_Historia, String fecha, String detalles) {
        this.id = id;
        this.id_Ganado = id_Ganado;
        this.nombre_Historia = nombre_Historia;
        this.fecha = fecha;
        this.detalles = detalles;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getId_Ganado() {
        return id_Ganado;
    }

    public void setId_Ganado(String id_Ganado) {
        this.id_Ganado = id_Ganado;
    }

    public String getNombre_Historia() {
        return nombre_Historia;
    }

    public void setNombre_Historia(String nombre_Historia) {
        this.nombre_Historia = nombre_Historia;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

}

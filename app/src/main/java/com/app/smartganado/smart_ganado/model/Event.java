package com.app.smartganado.smart_ganado.model;

import java.io.Serializable;
import java.sql.Date;

public class Event implements Serializable {

    private int id_Finca;
    private Date fecha;
    private int id_Tipo_Evento;
    private String nombre;
    private String detalles;
    private String encargado;

    public Event(Date fecha, int id_Finca, int id_tipo_evento, String nombre, String detalles, String encargado) {
        this.fecha = fecha;
        this.id_Finca = id_Finca;
        this.id_Tipo_Evento = id_tipo_evento;
        this.nombre = nombre;
        this.detalles = detalles;
        this.encargado = encargado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getId_Finca() {
        return id_Finca;
    }

    public void setId_Finca(int id_Finca) {
        this.id_Finca = id_Finca;
    }

    public int getId_tipo_evento() {
        return id_Tipo_Evento;
    }

    public void setId_tipo_evento(int id_tipo_evento) {
        this.id_Tipo_Evento = id_tipo_evento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

}

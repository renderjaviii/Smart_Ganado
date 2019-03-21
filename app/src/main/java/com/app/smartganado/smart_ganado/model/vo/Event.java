package com.app.smartganado.smart_ganado.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Event implements Serializable {


    private Integer idEstate;

    private Date date;
        private Integer idEventType;

    private String name;

    private String details;

    private String manager;



    public Event(Integer idEstate, Date date, Integer idEventType, String name, String details, String manager) {
        this.idEstate = idEstate;
        this.date = date;
        this.idEventType = idEventType;
        this.name = name;
        this.details = details;
        this.manager = manager;
    }

    public Event() {
    }

    public Integer getIdEstate() {
        return idEstate;
    }

    public void setIdEstate(Integer idEstate) {
        this.idEstate = idEstate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getIdEventType() {
        return idEventType;
    }

    public void setIdEventType(Integer idEventType) {
        this.idEventType = idEventType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }



}
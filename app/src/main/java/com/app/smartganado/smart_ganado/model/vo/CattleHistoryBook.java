package com.app.smartganado.smart_ganado.model.vo;

import java.io.Serializable;
import java.sql.Date;


public class CattleHistoryBook implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer id;

    private Date date;

    private String details;
    private Integer idCattle;

    private Integer idHistoryType;

    private String namehistory;

    public CattleHistoryBook(Date date, String details, Integer idCattle, Integer idHistoryType, String namehistory) {
        this.date = date;
        this.details = details;
        this.idCattle = idCattle;
        this.idHistoryType = idHistoryType;
        this.namehistory = namehistory;
    }

    public CattleHistoryBook() {
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getIdCattle() {
        return this.idCattle;
    }

    public void setIdCattle(Integer idCattle) {
        this.idCattle = idCattle;
    }

    public Integer getIdHistoryType() {
        return this.idHistoryType;
    }

    public void setIdHistoryType(Integer idHistoryType) {
        this.idHistoryType = idHistoryType;
    }

    public String getNamehistory() {
        return this.namehistory;
    }

    public void setNamehistory(String namehistory) {
        this.namehistory = namehistory;
    }
}


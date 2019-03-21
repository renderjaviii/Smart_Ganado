package com.app.smartganado.smart_ganado.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class ProductionBook implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer idTank;

    private Date date;

    private double production;



    public ProductionBook(Integer idTank, Date date, double production) {

        this.idTank = idTank;
        this.date = date;
        this.production = production;
    }

    public ProductionBook() {
    }

    public Integer getIdTank() {
        return this.idTank;
    }

    public void setIdTank(Integer idTank) {
        this.idTank = idTank;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getProduction() {
        return this.production;
    }

    public void setProduction(double production) {
        this.production = production;
    }

}
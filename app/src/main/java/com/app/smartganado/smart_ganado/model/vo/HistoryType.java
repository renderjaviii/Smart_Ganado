package com.app.smartganado.smart_ganado.model.vo;

import java.io.Serializable;

public class HistoryType implements Serializable {

    private int id;

    private String name;



    public HistoryType(String name) {
        this.name = name;
    }

    public HistoryType() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
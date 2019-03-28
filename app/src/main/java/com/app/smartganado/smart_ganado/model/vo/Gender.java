package com.app.smartganado.smart_ganado.model.vo;

import java.io.Serializable;


/**
 * The persistent class for the gender database table.
 */

public class Gender implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer id;


    private String name;

    public Gender() {
    }

    public Gender(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String print() {
        return "Gender{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public String toString() {
        return name;
    }

}
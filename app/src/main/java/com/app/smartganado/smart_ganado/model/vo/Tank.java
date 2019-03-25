package com.app.smartganado.smart_ganado.model.vo;

import java.io.Serializable;


/**
 * The persistent class for the tank database table.
 * 
 */

public class Tank implements Serializable {
	private static final long serialVersionUID = 1L;


	private Integer id;
	private Double capacity;
	private Integer idEstate;
	private String name;

	public Tank() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getCapacity() {
		return this.capacity;
	}

	public void setCapacity(Double capacity) {
		this.capacity = capacity;
	}

	public Integer getIdEstate() {
		return this.idEstate;
	}

	public void setIdEstate(Integer idEstate) {
		this.idEstate = idEstate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
package com.app.smartganado.smart_ganado.model.vo;

import java.io.Serializable;


/**
 * The persistent class for the rol database table.
 * 
 */
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String name;

	public Rol() {
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

}
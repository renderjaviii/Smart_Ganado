package com.app.smartganado.smart_ganado.model.vo;

import java.io.Serializable;


/**
 * The persistent class for the lot database table.
 * 
 */
public class Lot implements Serializable {
	private static final long serialVersionUID = 1L;


	private Integer id;

	private String name;

	public Lot() {
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
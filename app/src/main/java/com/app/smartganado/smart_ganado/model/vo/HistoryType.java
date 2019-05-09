package com.app.smartganado.smart_ganado.model.vo;

import java.io.Serializable;


/**
 * The persistent class for the history_type database table.
 * 
 */

public class HistoryType implements Serializable {
	private static final long serialVersionUID = 1L;


	private Integer id;

	private String name;

	private Integer phoneUser;

	public HistoryType() {
	}

	public HistoryType(int id,String name){
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

	public Integer getPhoneUser() {
		return this.phoneUser;
	}

	public void setPhoneUser(Integer phoneUser) {
		this.phoneUser = phoneUser;
	}

}
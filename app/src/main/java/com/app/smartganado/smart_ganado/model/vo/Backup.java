package com.app.smartganado.smart_ganado.model.vo;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the backup database table.
 * 
 */
public class Backup implements Serializable {
	private static final long serialVersionUID = 1L;


	private Integer id;

	private Date date;

	private Integer phoneUser;

	public Backup() {
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

	public Integer getPhoneUser() {
		return this.phoneUser;
	}

	public void setPhoneUser(Integer phoneUser) {
		this.phoneUser = phoneUser;
	}

}
package com.app.smartganado.smart_ganado.model.vo;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the event database table.
 * 
 */
public class Event implements Serializable {
	private static final long serialVersionUID = 1L;


	private Integer id;

	private Date date;

	private String details;

	private Integer idEstate;

	private Integer idEventType;

	private String manager;

	private String name;

	public Event() {
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

	public Integer getIdEstate() {
		return this.idEstate;
	}

	public void setIdEstate(Integer idEstate) {
		this.idEstate = idEstate;
	}

	public Integer getIdEventType() {
		return this.idEventType;
	}

	public void setIdEventType(Integer idEventType) {
		this.idEventType = idEventType;
	}

	public String getManager() {
		return this.manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
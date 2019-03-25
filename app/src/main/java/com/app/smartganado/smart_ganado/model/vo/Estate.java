package com.app.smartganado.smart_ganado.model.vo;

import java.io.Serializable;


/**
 * The persistent class for the estate database table.
 * 
 */
public class Estate implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Double area;

	private String location;

	private String name;

	private Long phoneUser;

	private Byte[] photo;

	public Estate() {
	}

	public Estate(Double area, String location, String name, Byte[] photo, Long phoneUser) {
		this.area = area;
		this.location = location;
		this.name = name;
		this.phoneUser = phoneUser;
		this.photo = photo;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getArea() {
		return this.area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPhoneUser() {
		return this.phoneUser;
	}

	public void setPhoneUser(Long phoneUser) {
		this.phoneUser = phoneUser;
	}

	public Byte[] getPhoto() {
		return this.photo;
	}

	public void setPhoto(Byte[] photo) {
		this.photo = photo;
	}

}
package com.app.smartganado.smart_ganado.model.vo;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the production_book database table.
 * 
 */
public class ProductionBook implements Serializable {
	private static final long serialVersionUID = 1L;


	private Integer id;

	private Date date;

	private Integer idTank;
	
	private double production;

	public ProductionBook() {
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

	public Integer getIdTank() {
		return this.idTank;
	}

	public void setIdTank(Integer idTank) {
		this.idTank = idTank;
	}

	public double getProduction() {
		return this.production;
	}

	public void setProduction(double production) {
		this.production = production;
	}

}
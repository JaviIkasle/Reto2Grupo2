package com.example.Reto2Grupo2.zooHasEspecie.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="zoo_has_especie")
public class ZooHasEspecie {

	@Column
	private Integer cantidad;
	@Column
	private Integer m2;
	
	public ZooHasEspecie() {}

	public ZooHasEspecie(Integer cantidad, Integer m2) {
		super();
		this.cantidad = cantidad;
		this.m2 = m2;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getM2() {
		return m2;
	}

	public void setM2(Integer m2) {
		this.m2 = m2;
	}

	@Override
	public String toString() {
		return "ZooHasEspecie [cantidad=" + cantidad + ", m2=" + m2 + "]";
	}
}

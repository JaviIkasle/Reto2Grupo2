package com.example.Reto2Grupo2.rol.modelo;

import java.util.List;

import com.example.Reto2Grupo2.auth.persistence.Trabajador;

public class RolServiceModel {

	private int id;

	private String tipo;

	private List<Trabajador> trabajadores;

	public RolServiceModel() {
	}

	public RolServiceModel(int id, String tipo, List<Trabajador> trabajadores) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.trabajadores = trabajadores;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Trabajador> getTrabajadores() {
		return trabajadores;
	}

	public void setTrabajadores(List<Trabajador> trabajadores) {
		this.trabajadores = trabajadores;
	}

	@Override
	public String toString() {
		return "RolServiceModel [id=" + id + ", tipo=" + tipo + ", trabajadores=" + trabajadores + "]";
	}

}

package com.example.Reto2Grupo2.rol.modelo;

import java.util.List;

import com.example.Reto2Grupo2.user.modelo.UserServiceModel;
import com.fasterxml.jackson.annotation.JsonInclude;

public class RolServiceModel {

	private Integer id;
	
	private String name;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<UserServiceModel> trabajadores;

	public RolServiceModel() {
	}

	public RolServiceModel(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public RolServiceModel(Integer id, String name, List<UserServiceModel> trabajadores) {
		super();
		this.id = id;
		this.name = name;
		this.trabajadores = trabajadores;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserServiceModel> getTrabajadores() {
		return trabajadores;
	}

	public void setTrabajadores(List<UserServiceModel> trabajadores) {
		this.trabajadores = trabajadores;
	}

	@Override
	public String toString() {
		return "RolServiceModel [id=" + id + ", name=" + name + ", trabajadores=" + trabajadores + "]";
	}

}

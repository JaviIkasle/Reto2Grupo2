package com.example.Reto2Grupo2.user.modelo;

import com.example.Reto2Grupo2.rol.modelo.RolServiceModel;
import com.example.Reto2Grupo2.trabajador.modelo.TrabajadorServiceModel;

public class UserServiceModel {

	private Integer id;

	private String username;

	private String password;

	private TrabajadorServiceModel trabajador;

	private RolServiceModel rol;

	private Integer rolId;

	public UserServiceModel() {

	}

	public UserServiceModel(Integer id, String username, String password, TrabajadorServiceModel trabajador,
			RolServiceModel rol, Integer rolId) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.trabajador = trabajador;
		this.rol = rol;
		this.rolId = rolId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public TrabajadorServiceModel getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(TrabajadorServiceModel trabajador) {
		this.trabajador = trabajador;
	}

	public RolServiceModel getRol() {
		return rol;
	}

	public void setRol(RolServiceModel rol) {
		this.rol = rol;
	}

	public Integer getRolId() {
		return rolId;
	}

	public void setRolId(Integer rolId) {
		this.rolId = rolId;
	}

	@Override
	public String toString() {
		return "UserServiceModel [id=" + id + ", username=" + username + ", password=" + password + ", trabajador="
				+ trabajador + ", rol=" + rol + ", rolId=" + rolId + "]";
	}

}

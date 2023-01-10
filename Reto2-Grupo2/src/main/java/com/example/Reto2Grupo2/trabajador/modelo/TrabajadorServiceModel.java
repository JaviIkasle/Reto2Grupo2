package com.example.Reto2Grupo2.trabajador.modelo;

import com.example.Reto2Grupo2.user.modelo.User;

public class TrabajadorServiceModel {


	private String especializacion;

	private String puesto;

	private User user;

	public TrabajadorServiceModel() {
	}

	public TrabajadorServiceModel(Integer id, String especializacion, String puesto, User user) {
		super();
		this.especializacion = especializacion;
		this.puesto = puesto;
		this.user = user;
	}


	public String getEspecializacion() {
		return especializacion;
	}

	public void setEspecializacion(String especializacion) {
		this.especializacion = especializacion;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "TrabajadorServiceModel [especializacion=" + especializacion + ", puesto=" + puesto + ", user=" + user
				+ "]";
	}

}

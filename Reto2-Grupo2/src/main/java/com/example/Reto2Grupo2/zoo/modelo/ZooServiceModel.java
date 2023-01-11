package com.example.Reto2Grupo2.zoo.modelo;

import java.util.List;

import com.example.Reto2Grupo2.evento.modelo.EventoServiceModel;
import com.example.Reto2Grupo2.user.modelo.UserServiceModel;

public class ZooServiceModel {

	private int id;

	private String nombre;

	private float pvpEntrada;

	private String web;

	private String informacion;

	private float latitud;

	private float longitud;

	private String ciudad;

	private String pais;

	private List<EventoServiceModel> eventos;

	private List<UserServiceModel> trabajadores;

	public ZooServiceModel() {
	}

	public ZooServiceModel(int id, String nombre, float pvpEntrada, String web, String informacion, float latitud,
			float longitud, String ciudad, String pais, List<EventoServiceModel> eventos,
			List<UserServiceModel> trabajadores) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.pvpEntrada = pvpEntrada;
		this.web = web;
		this.informacion = informacion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.ciudad = ciudad;
		this.pais = pais;
		this.eventos = eventos;
		this.trabajadores = trabajadores;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPvpEntrada() {
		return pvpEntrada;
	}

	public void setPvpEntrada(float pvpEntrada) {
		this.pvpEntrada = pvpEntrada;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getInformacion() {
		return informacion;
	}

	public void setInformacion(String informacion) {
		this.informacion = informacion;
	}

	public float getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public List<EventoServiceModel> getEventos() {
		return eventos;
	}

	public void setEventos(List<EventoServiceModel> eventos) {
		this.eventos = eventos;
	}

	public List<UserServiceModel> getTrabajadores() {
		return trabajadores;
	}

	public void setTrabajadores(List<UserServiceModel> trabajadores) {
		this.trabajadores = trabajadores;
	}

	@Override
	public String toString() {
		return "ZooServiceModel [id=" + id + ", nombre=" + nombre + ", pvpEntrada=" + pvpEntrada + ", web=" + web
				+ ", informacion=" + informacion + ", latitud=" + latitud + ", longitud=" + longitud + ", ciudad="
				+ ciudad + ", pais=" + pais + ", eventos=" + eventos + ", trabajadores=" + trabajadores + "]";
	}

}

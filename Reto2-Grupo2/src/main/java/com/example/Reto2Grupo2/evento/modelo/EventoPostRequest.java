package com.example.Reto2Grupo2.evento.modelo;

import java.sql.Date;


public class EventoPostRequest {

	private String nombre;

	private String informacion;

	private Date fecha;

	private Integer zooId;

	public EventoPostRequest() {
	}

	public EventoPostRequest(String nombre, String informacion, Date fecha, Integer zooId) {
		super();
		this.nombre = nombre;
		this.informacion = informacion;
		this.fecha = fecha;
		this.zooId = zooId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getInformacion() {
		return informacion;
	}

	public void setInformacion(String informacion) {
		this.informacion = informacion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getZooId() {
		return zooId;
	}

	public void setZooId(Integer zooId) {
		this.zooId = zooId;
	}

	@Override
	public String toString() {
		return "EventoPostRequest [nombre=" + nombre + ", informacion=" + informacion + ", fecha=" + fecha + ", zooId="
				+ zooId + "]";
	}

}

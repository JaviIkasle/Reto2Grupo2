package com.example.Reto2Grupo2.evento.modelo;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class EventoPostRequest {

//TODO falta validación
	private String nombre;

	private String informacion;

	private Date fecha;

	public EventoPostRequest() {
	}

	public EventoPostRequest(String nombre, String informacion, Date fecha) {
		super();
		this.nombre = nombre;
		this.informacion = informacion;
		this.fecha = fecha;
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

}

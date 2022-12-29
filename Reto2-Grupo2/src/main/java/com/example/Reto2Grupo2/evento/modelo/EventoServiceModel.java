package com.example.Reto2Grupo2.evento.modelo;

import java.sql.Date;
import com.example.Reto2Grupo2.zoo.modelo.ZooServiceModel;

public class EventoServiceModel {

	private Integer id;

	private String nombre;

	private String informacion;

	private Date fecha;

	private ZooServiceModel zoo;

	private Integer zooId;

	public EventoServiceModel() {
	}

	public EventoServiceModel(Integer id, String nombre, String informacion, Date fecha, ZooServiceModel zooServiceModel,
			Integer zooId) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.informacion = informacion;
		this.fecha = fecha;
		this.zoo = zooServiceModel;
		this.zooId = zooId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public ZooServiceModel getZoo() {
		return zoo;
	}

	public void setZoo(ZooServiceModel zoo) {
		this.zoo = zoo;
	}

	public Integer getZooId() {
		return zooId;
	}

	public void setZooId(Integer idZoo) {
		this.zooId = idZoo;
	}

	@Override
	public String toString() {
		return "EventoServiceModel [id=" + id + ", nombre=" + nombre + ", informacion=" + informacion + ", fecha="
				+ fecha + ", zoo=" + zoo + ", idZoo=" + zooId + "]";
	}
}

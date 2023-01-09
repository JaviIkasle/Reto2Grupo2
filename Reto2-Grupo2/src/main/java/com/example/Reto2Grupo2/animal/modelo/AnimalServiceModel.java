package com.example.Reto2Grupo2.animal.modelo;

import com.example.Reto2Grupo2.especie.modelo.EspecieServiceModel;

public class AnimalServiceModel {

	private Integer id;
	private String nombre;
	private String informacion;
	private String foto;
	
	private EspecieServiceModel especie;
	
	private Integer idEspecie;
	
	public AnimalServiceModel() {}

	public AnimalServiceModel(Integer id, String nombre, String informacion, String foto, EspecieServiceModel especie,
			Integer idEspecie) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.informacion = informacion;
		this.foto = foto;
		this.especie = especie;
		this.idEspecie = idEspecie;
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

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public EspecieServiceModel getEspecie() {
		return especie;
	}

	public void setEspecie(EspecieServiceModel especie) {
		this.especie = especie;
	}

	public Integer getIdEspecie() {
		return idEspecie;
	}

	public void setIdEspecie(Integer idEspecie) {
		this.idEspecie = idEspecie;
	}

	@Override
	public String toString() {
		return "AnimalServiceModel [id=" + id + ", nombre=" + nombre + ", informacion=" + informacion + ", foto=" + foto
				+ ", especie=" + especie + ", idEspecie=" + idEspecie + "]";
	}
}

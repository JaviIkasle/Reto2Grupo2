package com.example.Reto2Grupo2.animal.modelo;

import java.util.Set;

import com.example.Reto2Grupo2.especie.modelo.EspecieServiceModel;
import com.example.Reto2Grupo2.zoo.modelo.ZooServiceModel;


public class AnimalServiceModel {

	private Integer id;
	private String nombre;
	private String informacion;
	private String foto;
	
	private EspecieServiceModel especie;
	
	private Integer especieId;

	private Set<ZooServiceModel> zoos;
	
	public AnimalServiceModel() {}

	public AnimalServiceModel(Integer id, String nombre, String informacion, String foto, EspecieServiceModel especie,
			Integer idEspecie, Set<ZooServiceModel> zoos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.informacion = informacion;
		this.foto = foto;
		this.especie = especie;
		this.especieId = idEspecie;
		this.zoos = zoos;
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

	public Integer getEspecieId() {
		return especieId;
	}

	public void seEspecieId(Integer idEspecie) {
		this.especieId = idEspecie;
	}

	public Set<ZooServiceModel> getZoos() {
		return zoos;
	}

	public void setZoos(Set<ZooServiceModel> zoos) {
		this.zoos = zoos;
	}

	@Override
	public String toString() {
		return "AnimalServiceModel [id=" + id + ", nombre=" + nombre + ", informacion=" + informacion + ", foto=" + foto
				+ ", especie=" + especie + ", idEspecie=" + especieId + ", zoos=" + zoos + "]";
	}
}

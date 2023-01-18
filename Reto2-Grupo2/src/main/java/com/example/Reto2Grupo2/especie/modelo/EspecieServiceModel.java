package com.example.Reto2Grupo2.especie.modelo;

import java.util.List;

import com.example.Reto2Grupo2.animal.modelo.Animal;

public class EspecieServiceModel {

	private Integer id;
	private String nombre;
	private String informacion;
	
	private List<Animal> animales;

	public EspecieServiceModel() {}
	
	public EspecieServiceModel(Integer id, String nombre, String informacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.informacion = informacion;
	}
	
	public EspecieServiceModel(Integer id, String nombre, String informacion, List<Animal> animales) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.informacion = informacion;
		this.animales = animales;
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

	public List<Animal> getAnimales() {
		return animales;
	}

	public void setAnimales(List<Animal> animales) {
		this.animales = animales;
	}

	@Override
	public String toString() {
		return "EspecieServiceModel [id=" + id + ", nombre=" + nombre + ", informacion=" + informacion + ", animales="
				+ animales + "]";
	}
}

package com.example.Reto2Grupo2.especie.modelo;

import java.util.List;

import com.example.Reto2Grupo2.animal.modelo.Animal;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="especies")
public class Especie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 100)
	private String nombre;
	@Column(length = 400)
	private String informacion;
	
	@OneToMany(mappedBy = "especie", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonBackReference
	private List<Animal> animales;
	
	public Especie() {}

	public Especie(int id, String nombre, String informacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.informacion = informacion;
	}

	public Especie(int id, String nombre, String informacion, List<Animal> animales) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.informacion = informacion;
		this.animales = animales;
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
		return "Especie [id=" + id + ", nombre=" + nombre + ", informacion=" + informacion + ", animales=" + animales
				+ "]";
	}	
}

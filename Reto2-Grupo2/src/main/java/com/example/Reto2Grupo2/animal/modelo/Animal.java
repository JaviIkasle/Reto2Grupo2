package com.example.Reto2Grupo2.animal.modelo;

import java.util.List;

import com.example.Reto2Grupo2.especie.modelo.Especie;
import com.example.Reto2Grupo2.zoo.modelo.Zoo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="animales")
public class Animal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 100)
	private String nombre;
	@Column(length = 400)
	private String informacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_especie", foreignKey=@ForeignKey(name = "FK_id_especie"))
	@JsonManagedReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Especie especie;
	
	@Column(name="id_especie", insertable=false, updatable=false)
	private int idEspecie;
	
	@ManyToMany(mappedBy = "animales")
	private List<Zoo> zoos;
	
	public Animal() {}

	public Animal(int id, String nombre, String informacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.informacion = informacion;
	}

	public Animal(int id, String nombre, String informacion, Especie especie, int idEspecie, List<Zoo> zoos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.informacion = informacion;
		this.especie = especie;
		this.idEspecie = idEspecie;
		this.zoos = zoos;
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

	public Especie getEspecie() {
		return especie;
	}

	public void setEspecie(Especie especie) {
		this.especie = especie;
	}

	public int getIdEspecie() {
		return idEspecie;
	}

	public void setIdEspecie(int idEspecie) {
		this.idEspecie = idEspecie;
	}

	public List<Zoo> getZoos() {
		return zoos;
	}

	public void setZoos(List<Zoo> zoos) {
		this.zoos = zoos;
	}

	@Override
	public String toString() {
		return "Animal [id=" + id + ", nombre=" + nombre + ", informacion=" + informacion + ", especie=" + especie
				+ ", idEspecie=" + idEspecie + ", zoos=" + zoos + "]";
	}
}

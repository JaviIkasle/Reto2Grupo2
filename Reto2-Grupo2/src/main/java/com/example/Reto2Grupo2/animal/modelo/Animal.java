package com.example.Reto2Grupo2.animal.modelo;


import java.util.Set;

import com.example.Reto2Grupo2.especie.modelo.Especie;
import com.example.Reto2Grupo2.zoo.modelo.Zoo;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@Entity
@Table(name="animales")
public class Animal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 100)
	private String nombre;
	@Column(length = 400)
	private String informacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "especie_id", foreignKey=@ForeignKey(name = "FK_id_especie"))
	@JsonManagedReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Especie especie;
	
	@Column(name="especie_id", insertable=false, updatable=false)
	private Integer especieId;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "zoo_has_animal",
			joinColumns = @JoinColumn(
					name = "animal_id", referencedColumnName = "id", foreignKey = @ForeignKey(name="FK_animal_id")
			),
			inverseJoinColumns = @JoinColumn(
					name = "zoo_id", referencedColumnName = "id", foreignKey = @ForeignKey(name="FK_zoo_id")
			)
			
	)
	private Set<Zoo> zoos;
	
	public Animal() {}

	public Animal(Integer id, String nombre, String informacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.informacion = informacion;
	}

	public Animal(Integer id, String nombre, String informacion, Especie especie, Integer especieId,
			Set<Zoo> zoos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.informacion = informacion;
		this.especie = especie;
		this.especieId = especieId;
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

	public Especie getEspecie() {
		return especie;
	}

	public void setEspecie(Especie especie) {
		this.especie = especie;
	}

	public Integer getEspecieId() {
		return especieId;
	}

	public void setEspecieId(Integer especieId) {
		this.especieId = especieId;
	}

	public Set<Zoo> getZoos() {
		return zoos;
	}

	public void setZoos(Set<Zoo> zoos) {
		this.zoos = zoos;
	}

	@Override
	public String toString() {
		return "Animal [id=" + id + ", nombre=" + nombre + ", informacion=" + informacion + ", especie=" + especie + ", idEspecie=" + especieId + ", zoos=" + zoos + "]";
	}
}

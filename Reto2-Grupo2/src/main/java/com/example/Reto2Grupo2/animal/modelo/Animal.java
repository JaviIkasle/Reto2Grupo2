package com.example.Reto2Grupo2.animal.modelo;


import com.example.Reto2Grupo2.especie.modelo.Especie;
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
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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
	@Lob
	@Column(columnDefinition="MEDIUMBLOB")
	private String foto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_especie", foreignKey=@ForeignKey(name = "FK_id_especie"))
	@JsonManagedReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Especie especie;
	
	@Column(name="id_especie", insertable=false, updatable=false)
	private Integer idEspecie;
	
//	@ManyToMany(mappedBy = "animales")
//	private List<Zoo> zoos;
	
	public Animal() {}

	public Animal(Integer id, String nombre, String informacion, String foto) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.informacion = informacion;
		this.foto = foto;
	}

//	public Animal(int id, String nombre, String informacion, String foto, Especie especie, int idEspecie,
//			List<Zoo> zoos) {
//		super();
//		this.id = id;
//		this.nombre = nombre;
//		this.informacion = informacion;
//		this.foto = foto;
//		this.especie = especie;
//		this.idEspecie = idEspecie;
//		this.zoos = zoos;
//	}

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

	public Integer getIdEspecie() {
		return idEspecie;
	}

	public void setIdEspecie(Integer idEspecie) {
		this.idEspecie = idEspecie;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
}

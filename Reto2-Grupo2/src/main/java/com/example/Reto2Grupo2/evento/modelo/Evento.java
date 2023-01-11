package com.example.Reto2Grupo2.evento.modelo;

import java.sql.Date;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "eventos")
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 60)
	private String nombre;
	@Column(length = 400)
	private String informacion;
	@Column
	private Date fecha;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "zoo_id", foreignKey = @ForeignKey(name = "FK_zoo_id_Event"))
	@JsonManagedReference
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Zoo zoo;

	@Column(name="zoo_id", insertable=false, updatable=false)
	private Integer zooId;

	public Evento() {
	}

	public Evento(Integer id, String nombre, String informacion, Date fecha, Zoo zoo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.informacion = informacion;
		this.fecha = fecha;
		this.zoo = zoo;
	}

	public Evento(Integer id, String nombre, String informacion, Date fecha, Zoo zoo, Integer zooId) {
	super();
	this.id = id;
	this.nombre = nombre;
	this.informacion = informacion;
	this.fecha = fecha;
	this.zoo = zoo;
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

	public Zoo getZoo() {
		return zoo;
	}

	public void setZoo(Zoo zoo) {
		this.zoo = zoo;
	}

	public Integer getZooId() {
		return zooId;
	}

	public void setZooId(Integer idZoo) {
		this.zooId = idZoo;
	}
}

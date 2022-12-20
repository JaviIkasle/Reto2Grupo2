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
	private int id;
	@Column(length = 60)
	private String nombre;
	@Column(length = 400)
	private String informacion;
	@Column
	private Date fecha;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_zoo", foreignKey=@ForeignKey(name = "FK_id_zoo"))
	@JsonManagedReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Zoo zoo;

	@Column(name="id_zoo", insertable=false, updatable=false)
	private Integer idZoo;

	public Evento() {}

	public Evento(int id, String nombre, String informacion, Date fecha) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.informacion = informacion;
		this.fecha = fecha;
	}

	public Evento(int id, String nombre, String informacion, Date fecha, Zoo zoo, Integer idZoo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.informacion = informacion;
		this.fecha = fecha;
		this.zoo = zoo;
		this.idZoo = idZoo;
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

	public Integer getIdZoo() {
		return idZoo;
	}

	public void setIdZoo(Integer idZoo) {
		this.idZoo = idZoo;
	}

	@Override
	public String toString() {
		return "Evento [id=" + id + ", nombre=" + nombre + ", informacion=" + informacion + ", fecha=" + fecha
				+ ", zoo=" + zoo + ", idZoo=" + idZoo + "]";
	}
}

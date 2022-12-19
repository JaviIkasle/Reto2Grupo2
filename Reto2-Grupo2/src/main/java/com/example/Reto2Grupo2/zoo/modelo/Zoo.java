package com.example.Reto2Grupo2.zoo.modelo;

import java.util.List;

import com.example.Reto2Grupo2.billete.modelo.Billete;
import com.example.Reto2Grupo2.evento.modelo.Evento;
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
@Table(name="zoos")
public class Zoo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 150)
	private String nombre;
	@Column(name = "pvp_entrada")
	private float pvpEntrada;
	@Column
	private String web;
	
	@OneToMany(mappedBy = "zoo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonBackReference
	private List<Evento> eventos;
	
	@OneToMany(mappedBy = "zoo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonBackReference
	private List<Billete> billetes; //TODO PREGUNTAR A MIKEL SI EL MAPEO=ZOO DE BILLETES INTERFIERE CON EL MAPEO=ZOO DE EVENTOS
	
	public Zoo() {}

	public Zoo(Integer id, String nombre, float pvpEntrada, String web) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.pvpEntrada = pvpEntrada;
		this.web = web;
	}

	public Zoo(Integer id, String nombre, float pvpEntrada, String web, List<Evento> eventos, List<Billete> billetes) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.pvpEntrada = pvpEntrada;
		this.web = web;
		this.eventos = eventos;
		this.billetes = billetes;
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

	public float getPvpEntrada() {
		return pvpEntrada;
	}

	public void setPvpEntrada(float pvpEntrada) {
		this.pvpEntrada = pvpEntrada;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public List<Billete> getBilletes() {
		return billetes;
	}

	public void setBilletes(List<Billete> billetes) {
		this.billetes = billetes;
	}

	@Override
	public String toString() {
		return "Zoo [id=" + id + ", nombre=" + nombre + ", pvpEntrada=" + pvpEntrada + ", web=" + web + ", eventos="
				+ eventos + ", billetes=" + billetes + "]";
	}
}

package com.example.Reto2Grupo2.zoo.modelo;

import java.util.List;

import com.example.Reto2Grupo2.animal.modelo.Animal;
import com.example.Reto2Grupo2.billete.modelo.Billete;
import com.example.Reto2Grupo2.evento.modelo.Evento;
import com.example.Reto2Grupo2.trabajador.Trabajador;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "zoos")
public class Zoo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 150)
	private String nombre;
	@Column(name = "pvp_entrada")
	private float pvpEntrada;
	@Column
	private String web;
	@Column(length = 400)
	private String informacion;
	@Column
	private float latitud;
	@Column
	private float longitud;
	@Column(length = 200)
	private String ciudad;
	@Column(length = 200)
	private String pais;

	@OneToMany(mappedBy = "zoo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonBackReference
	private List<Evento> eventos;

	@OneToMany(mappedBy = "zoo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonBackReference
	private List<Billete> billetes;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "zoo_has_animales", joinColumns = @JoinColumn(name = "id_zoo"), inverseJoinColumns = @JoinColumn(name = "id_animal"))
	private List<Animal> animales;

	@OneToMany(mappedBy = "zoo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonBackReference
	private List<Trabajador> trabajadores;
	
	public Zoo() {
	}

	public Zoo(int id, String nombre, float pvpEntrada, String web, String informacion, float latitud, float longitud,
			String ciudad, String pais) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.pvpEntrada = pvpEntrada;
		this.web = web;
		this.informacion = informacion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.ciudad = ciudad;
		this.pais = pais;
	}

	public Zoo(int id, String nombre, float pvpEntrada, String web, String informacion, float latitud, float longitud,
			String ciudad, String pais, List<Evento> eventos, List<Billete> billetes, List<Animal> animales,
			List<Trabajador> trabajadores) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.pvpEntrada = pvpEntrada;
		this.web = web;
		this.informacion = informacion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.ciudad = ciudad;
		this.pais = pais;
		this.eventos = eventos;
		this.billetes = billetes;
		this.animales = animales;
		this.trabajadores = trabajadores;
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

	public String getInformacion() {
		return informacion;
	}

	public void setInformacion(String informacion) {
		this.informacion = informacion;
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

	public List<Animal> getAnimales() {
		return animales;
	}

	public void setAnimales(List<Animal> animales) {
		this.animales = animales;
	}

	public List<Trabajador> getTrabajadores() {
		return trabajadores;
	}

	public void setTrabajadores(List<Trabajador> trabajadores) {
		this.trabajadores = trabajadores;
	}

	
	public float getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@Override
	public String toString() {
		return "Zoo [id=" + id + ", nombre=" + nombre + ", pvpEntrada=" + pvpEntrada + ", web=" + web + ", informacion="
				+ informacion + ", latitud=" + latitud + ", longitud=" + longitud + ", ciudad=" + ciudad + ", pais="
				+ pais + ", eventos=" + eventos + ", billetes=" + billetes + ", animales=" + animales
				+ ", trabajadores=" + trabajadores + "]";
	}
}

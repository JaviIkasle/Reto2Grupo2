package com.example.Reto2Grupo2.auth.persistence;

import java.util.List;

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
@Table(name = "roles")
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 150)
	private String tipo;

	@OneToMany(mappedBy = "rol", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonBackReference
	private List<Trabajador> trabajadores;
	
	public Rol() {
	}

	public Rol(int id, String tipo) {
		super();
		this.id = id;
		this.tipo = tipo;
	}
//
//	public Rol(int id, String tipo, List<Trabajador> trabajadores) {
//		super();
//		this.id = id;
//		this.tipo = tipo;
//		this.trabajadores = trabajadores;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

//	public List<Trabajador> getTrabajadores() {
//		return trabajadores;
//	}
//
//	public void setTrabajadores(List<Trabajador> trabajadores) {
//		this.trabajadores = trabajadores;
//	}
//
//	@Override
//	public String toString() {
//		return "Rol [id=" + id + ", tipo=" + tipo + ", trabajadores=" + trabajadores + "]";
//	}
}

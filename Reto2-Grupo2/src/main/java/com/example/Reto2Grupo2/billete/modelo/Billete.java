package com.example.Reto2Grupo2.billete.modelo;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "billetes")
public class Billete {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private Date fecha;
	@Column(length = 60)
	private String tipo;
	@Column
	private float importe;
	
	public Billete() {}

	public Billete(int id, Date fecha, String tipo, float importe) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.tipo = tipo;
		this.importe = importe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	@Override
	public String toString() {
		return "Billete [id=" + id + ", fecha=" + fecha + ", tipo=" + tipo + ", importe=" + importe + "]";
	}

}

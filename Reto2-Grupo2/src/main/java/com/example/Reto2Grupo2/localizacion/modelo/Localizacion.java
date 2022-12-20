package com.example.Reto2Grupo2.localizacion.modelo;

import com.example.Reto2Grupo2.zoo.modelo.Zoo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "localizaciones")
public class Localizacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 60)
	private String ciudad;
	@Column(length = 60)
	private String pais;
	@Column(length = 60)
	private String continente;
	@Column()
	private float longitud;
	@Column()
	private float latitud;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_zoo", referencedColumnName = "id")
	private Zoo zoo;
	
	public Localizacion() {}
	

	public Localizacion(int id, String ciudad, String pais, String continente, float longitud, float latitud) {
		super();
		this.id = id;
		this.ciudad = ciudad;
		this.pais = pais;
		this.continente = continente;
		this.longitud = longitud;
		this.latitud = latitud;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getContinente() {
		return continente;
	}

	public void setContinente(String continente) {
		this.continente = continente;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	public float getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}

	public Zoo getZoo() {
		return zoo;
	}


	public void setZoo(Zoo zoo) {
		this.zoo = zoo;
	}


	@Override
	public String toString() {
		return "Localizacion [id=" + id + ", ciudad=" + ciudad + ", pais=" + pais + ", continente=" + continente
				+ ", longitud=" + longitud + ", latitud=" + latitud + ", zoo=" + zoo + "]";
	}
}

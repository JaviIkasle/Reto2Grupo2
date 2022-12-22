package com.example.Reto2Grupo2.zoo.modelo;

public class ZooServiceModel {

	private Integer id;
	private String nombre;
	private float pvpEntrada;
	private String informacion;
	private float latitud;
	private float longitud;
	private String ciudad;
	private String pais;
	
	public ZooServiceModel() {}

	public ZooServiceModel(Integer id, String nombre, float pvpEntrada, String informacion, float latitud,
			float longitud, String ciudad, String pais) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.pvpEntrada = pvpEntrada;
		this.informacion = informacion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.ciudad = ciudad;
		this.pais = pais;
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

	public String getInformacion() {
		return informacion;
	}

	public void setInformacion(String informacion) {
		this.informacion = informacion;
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
		return "ZooServiceModel [id=" + id + ", nombre=" + nombre + ", pvpEntrada=" + pvpEntrada + ", informacion="
				+ informacion + ", latitud=" + latitud + ", longitud=" + longitud + ", ciudad=" + ciudad + ", pais="
				+ pais + "]";
	}
}

package com.example.Reto2Grupo2.zoo.modelo;

public class ZooPostRequest {

	//TODO validacioness, Si se ponen validaciones aqui, tambien validara a la hora de hacer un PUT 
	// y obligara a escribir los atributos validados para el POST, Queremos que pueda modificar unicamente los atributos que quiera.
	private String nombre;

	private float pvpEntrada;

	private String web;

	private String informacion;

	private float latitud;

	private float longitud;

	private String ciudad;

	private String pais;
	
	public ZooPostRequest() {}

	public ZooPostRequest(String nombre, float pvpEntrada, String web, String informacion, float latitud,
			float longitud, String ciudad, String pais) {
		super();
		this.nombre = nombre;
		this.pvpEntrada = pvpEntrada;
		this.web = web;
		this.informacion = informacion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.ciudad = ciudad;
		this.pais = pais;
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
	
	
	
} 

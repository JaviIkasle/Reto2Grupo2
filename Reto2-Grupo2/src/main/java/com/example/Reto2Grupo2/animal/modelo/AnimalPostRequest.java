package com.example.Reto2Grupo2.animal.modelo;


public class AnimalPostRequest {

	private String nombre;
	private String informacion;
	private String foto;

	private Integer idEspecie;
	
	public AnimalPostRequest() {}

	public AnimalPostRequest(String nombre, String informacion, String foto, Integer idEspecie) {
		super();
		this.nombre = nombre;
		this.informacion = informacion;
		this.foto = foto;
		this.idEspecie = idEspecie;
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

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Integer getIdEspecie() {
		return idEspecie;
	}

	public void setIdEspecie(Integer idEspecie) {
		this.idEspecie = idEspecie;
	}

	@Override
	public String toString() {
		return "AnimalPostRequest [nombre=" + nombre + ", informacion=" + informacion + ", foto=" + foto
				+ ", idEspecie=" + idEspecie + "]";
	}
}

package com.example.Reto2Grupo2.animal.modelo;


public class AnimalPostRequest {

	private String nombre;
	private String informacion;
	private String foto;
	
	private Integer especieId;
	

	public AnimalPostRequest() {}
	
	public AnimalPostRequest(String nombre, String informacion, String foto, Integer especieId) {
		super();
		this.nombre = nombre;
		this.informacion = informacion;
		this.foto = foto;
		this.especieId = especieId;
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

	public Integer getEspecieId() {
		return especieId;
	}

	public void setEspecieId(Integer especieId) {
		this.especieId = especieId;
	}

	@Override
	public String toString() {
		return "AnimalPostRequest [nombre=" + nombre + ", informacion=" + informacion + ", foto=" + foto
				+ ", idEspecie=" + especieId + "]";
	}
}

package com.example.Reto2Grupo2.especie.modelo;

public class EspeciePostRequest {

	private String nombre;
	private String informacion;
	
	public EspeciePostRequest() {}
	
	public EspeciePostRequest(String nombre, String informacion) {
		super();
		this.nombre = nombre;
		this.informacion = informacion;
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

	@Override
	public String toString() {
		return "EspeciePostRequest [nombre=" + nombre + ", informacion=" + informacion + "]";
	}	
}

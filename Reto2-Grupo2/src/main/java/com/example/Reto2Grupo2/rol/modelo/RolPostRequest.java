package com.example.Reto2Grupo2.rol.modelo;

public class RolPostRequest {

	private String name;

	public RolPostRequest() {
	}

	public RolPostRequest(String tipo) {
		super();

		this.name = tipo;
	}

	public String getName() {
		return name;
	}

	public void setName(String tipo) {
		this.name = tipo;
	}

	@Override
	public String toString() {
		return "RolPostRequest [tipo=" + name + "]";
	}

}

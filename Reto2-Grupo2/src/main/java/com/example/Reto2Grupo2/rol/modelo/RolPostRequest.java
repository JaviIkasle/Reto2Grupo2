package com.example.Reto2Grupo2.rol.modelo;

public class RolPostRequest {

	private String tipo;

	public RolPostRequest() {
	}

	public RolPostRequest(String tipo) {
		super();

		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "RolPostRequest [tipo=" + tipo + "]";
	}

}

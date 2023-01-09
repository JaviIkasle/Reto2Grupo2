package com.example.Reto2Grupo2.cliente.modelo;



public class ClientePostRequest {
	
	
	private String email;
	private String password;
	
	public ClientePostRequest() {
		
	}
	
	public ClientePostRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "ClientePostRequest [email=" + email + ", password=" + password + "]";
	}
	
	
	
	
	
	

}

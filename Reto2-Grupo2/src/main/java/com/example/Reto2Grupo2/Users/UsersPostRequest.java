package com.example.Reto2Grupo2.Users;


public class UsersPostRequest {
	
	
	private String nombre;
	 
	private String apellido;
	
	private String email;
	 
	private String password;
	
	
	
	
	
	
	
	

	public UsersPostRequest() {
		super();
	}

	public UsersPostRequest(String nombre, String apellido, String email, String password) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
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
		return "UsersPostRequest [nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", password="
				+ password ;
	}
	
	
	
	

}

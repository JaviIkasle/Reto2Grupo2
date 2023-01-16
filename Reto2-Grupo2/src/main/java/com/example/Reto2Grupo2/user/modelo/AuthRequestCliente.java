package com.example.Reto2Grupo2.user.modelo;

public class AuthRequestCliente {

	private String username;
	private String password;
	private String email;

	public AuthRequestCliente() {
	}

	public AuthRequestCliente(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "AuthRequestCliente [username=" + username + ", password=" + password + ", email=" + email + "]";
	}

}

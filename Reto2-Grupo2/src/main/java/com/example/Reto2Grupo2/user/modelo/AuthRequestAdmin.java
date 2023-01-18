package com.example.Reto2Grupo2.user.modelo;

public class AuthRequestAdmin {

	private String username;
	private String password;
	private String email;
	
	public AuthRequestAdmin() {}
	
	public AuthRequestAdmin(String username, String password, String email) {
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
		return "AuthRequestAdmin [username=" + username + ", password=" + password + ", email=" + email + "]";
	}
}

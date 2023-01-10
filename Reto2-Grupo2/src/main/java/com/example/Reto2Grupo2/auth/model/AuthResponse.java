package com.example.Reto2Grupo2.auth.model;

public class AuthResponse {

	private Integer id;
	private String username;
	private String accesToken;

	public AuthResponse() {
	}

	public AuthResponse(Integer id, String username, String accesToken) {
		super();
		this.id = id;
		this.username = username;
		this.accesToken = accesToken;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccesToken() {
		return accesToken;
	}

	public void setAccesToken(String accesToken) {
		this.accesToken = accesToken;
	}

	@Override
	public String toString() {
		return "AuthResponse [id=" + id + ", username=" + username + ", accesToken=" + accesToken + "]";
	}

}

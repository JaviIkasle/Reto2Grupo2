package com.example.Reto2Grupo2.user.modelo;

import jakarta.validation.constraints.Email;

public class ClienteUpdateByAdminRequest {

	private String username;

	private String password;

	@Email(message = "Email incorrecto")
	private String email;

	private Integer rolId;

	public ClienteUpdateByAdminRequest() {
	}

	public ClienteUpdateByAdminRequest(String username, String password, @Email(message = "Email incorrecto") String email,
			Integer rolId) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.rolId = rolId;
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

	public Integer getRolId() {
		return rolId;
	}

	public void setRolId(Integer rolId) {
		this.rolId = rolId;
	}

	@Override
	public String toString() {
		return "ClienteUpdateByAdmin [username=" + username + ", password=" + password + ", email=" + email + ", rolId="
				+ rolId + "]";
	}

}

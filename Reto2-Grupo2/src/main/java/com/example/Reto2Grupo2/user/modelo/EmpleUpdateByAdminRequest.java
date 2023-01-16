package com.example.Reto2Grupo2.user.modelo;

import jakarta.validation.constraints.Email;

public class EmpleUpdateByAdminRequest {

	private String username;

	private String password;

	@Email(message = "Email incorrecto")
	private String email;

	private Integer zooId;

	private Integer rolId;

	public EmpleUpdateByAdminRequest() {
	}

	public EmpleUpdateByAdminRequest(String username, String password, @Email(message = "Email incorrecto") String email,
			Integer zooId, Integer rolId) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.zooId = zooId;
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

	public Integer getZooId() {
		return zooId;
	}

	public void setZooId(Integer zooId) {
		this.zooId = zooId;
	}

	public Integer getRolId() {
		return rolId;
	}

	public void setRolId(Integer rolId) {
		this.rolId = rolId;
	}

	@Override
	public String toString() {
		return "UserPostRequest [username=" + username + ", password=" + password + ", email=" + email + ", zooId="
				+ zooId + ", rolId=" + rolId + "]";
	}

}

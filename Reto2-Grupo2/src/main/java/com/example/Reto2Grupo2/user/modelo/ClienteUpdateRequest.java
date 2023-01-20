package com.example.Reto2Grupo2.user.modelo;

import jakarta.validation.constraints.Email;

public class ClienteUpdateRequest {

	private String username;

	private String oldPassword;

	private String newPassword;

	@Email(message = "Email incorrecto")
	private String email;

	public ClienteUpdateRequest() {
	}

	public ClienteUpdateRequest(String username, String oldPassword, String newPassword,
			@Email(message = "Email incorrecto") String email) {
		super();
		this.username = username;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "ClienteUpdateRequest [username=" + username + ", oldPassword=" + oldPassword + ", newPassword="
				+ newPassword + ", email=" + email + "]";
	}

}

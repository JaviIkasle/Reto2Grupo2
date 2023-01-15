package com.example.Reto2Grupo2.user.modelo;

public class UpdateUserPostRequest {

	private String username;

	private String oldPassword;

	private String newPassword;

	public UpdateUserPostRequest() {
	}

	public UpdateUserPostRequest(String username, String oldPassword, String newPassword) {
		super();
		this.username = username;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
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

	@Override
	public String toString() {
		return "UpdateUserPostRequest [username=" + username + ", oldPassword=" + oldPassword + ", newPassword="
				+ newPassword + "]";
	}

}

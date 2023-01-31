package com.example.Reto2Grupo2.user.modelo;

public class ClienteUpdateAndroid {

	private String oldPassword;

	private String newPassword;

	public ClienteUpdateAndroid() {
	}

	public ClienteUpdateAndroid(String oldPassword,String newPassword) {
		super();
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
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
		return "ClienteUpdateAndroid [oldPassword=" + oldPassword + ", newPassword=" + newPassword + "]";
	}

}

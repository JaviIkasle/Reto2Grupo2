package com.example.Reto2Grupo2.user.modelo;

import java.util.Arrays;

public class ClienteUpdateAndroid {

	private String username;

	private byte[] oldPassword;

	private byte[] newPassword;

	public ClienteUpdateAndroid() {
	}

	public ClienteUpdateAndroid(String username, byte[] oldPassword, byte[] newPassword) {
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

	public byte[] getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(byte[] oldPassword) {
		this.oldPassword = oldPassword;
	}

	public byte[] getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(byte[] newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public String toString() {
		return "ClienteUpdateAndroid [username=" + username + ", oldPassword=" + Arrays.toString(oldPassword)
				+ ", newPassword=" + Arrays.toString(newPassword) + "]";
	}

}

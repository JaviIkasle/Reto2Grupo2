package com.example.Reto2Grupo2.auth.model;

import java.util.Arrays;

//PARA ANDROID Y PSP
public class LoginRequest {

	private String username;

	private byte[] password;

	public LoginRequest() {
	}

	public LoginRequest(String username, byte[] password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginRequest [username=" + username + ", password=" + Arrays.toString(password) + "]";
	}

}

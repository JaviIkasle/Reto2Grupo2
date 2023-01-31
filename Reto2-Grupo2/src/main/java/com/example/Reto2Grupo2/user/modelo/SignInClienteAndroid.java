package com.example.Reto2Grupo2.user.modelo;

import java.util.Arrays;

public class SignInClienteAndroid {

	private String username;

	private byte[] password;

	private String email;

	public SignInClienteAndroid() {
	}

	public SignInClienteAndroid(String username, byte[] password, String email) {
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

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
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
		return "SignInClienteRequest [username=" + username + ", password=" + Arrays.toString(password) + ", email="
				+ email + "]";
	}

}

package com.example.Reto2Grupo2.user.modelo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AuthRequestCliente {

	@NotNull(message = "No puede ser nulo")
	@NotEmpty(message = "No puede estar vacio")
	@NotBlank(message = "No puede estar en blanco")
	private String username;
	@NotNull(message = "No puede ser nulo")
	@NotEmpty(message = "No puede estar vacio")
	@NotBlank(message = "No puede estar en blanco")
	private String password;
	@NotNull(message = "No puede ser nulo")
	@NotEmpty(message = "No puede estar vacio")
	@NotBlank(message = "No puede estar en blanco")
	@Email(message = "Email incorrecto")
	private String email;

	public AuthRequestCliente() {
	}

	public AuthRequestCliente(
			@NotNull(message = "No puede ser nulo") @NotEmpty(message = "No puede estar vacio") @NotBlank(message = "No puede estar en blanco") String username,
			@NotNull(message = "No puede ser nulo") @NotEmpty(message = "No puede estar vacio") @NotBlank(message = "No puede estar en blanco") String password,
			@NotNull(message = "No puede ser nulo") @NotEmpty(message = "No puede estar vacio") @NotBlank(message = "No puede estar en blanco") @Email(message = "Email incorrecto") String email) {
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
		return "AuthRequestCliente [username=" + username + ", password=" + password + ", email=" + email + "]";
	}

}

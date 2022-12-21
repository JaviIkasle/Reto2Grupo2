package com.example.Reto2Grupo2.cliente;

public class ClienteServiceModel {

	private Integer id;
	private String email;
	private String password;

	public ClienteServiceModel() {
		super();
	}

	public ClienteServiceModel(Integer id, String email, String password) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "ClientePostRequest [id=" + id + ", email=" + email + ", password=" + password + "]";
	}

}

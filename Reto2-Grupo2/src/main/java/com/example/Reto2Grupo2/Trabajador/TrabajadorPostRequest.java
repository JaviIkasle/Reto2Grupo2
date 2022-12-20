package com.example.Reto2Grupo2.Trabajador;

public class TrabajadorPostRequest {

	private Integer id;

	private String username;

	private String password;

	private Integer idZoo;

	public TrabajadorPostRequest() {
		super();
	}

	public TrabajadorPostRequest(Integer id, String username, String password, Integer idZoo) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.idZoo = idZoo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getIdZoo() {
		return idZoo;
	}

	public void setIdZoo(Integer idZoo) {
		this.idZoo = idZoo;
	}

	@Override
	public String toString() {
		return "TrabajadorPostRequest [id=" + id + ", username=" + username + ", password=" + password + ", idZoo="
				+ idZoo + "]";
	}

}

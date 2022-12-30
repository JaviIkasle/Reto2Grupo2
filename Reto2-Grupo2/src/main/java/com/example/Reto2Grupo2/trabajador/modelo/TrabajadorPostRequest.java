package com.example.Reto2Grupo2.trabajador.modelo;

public class TrabajadorPostRequest {

	private String username;

	private String password;

	private Integer zooId;
	
	private Integer rolId;

	public TrabajadorPostRequest() {
	}

	public TrabajadorPostRequest(String username, String password, Integer zooId, Integer rolId) {
		super();
		this.username = username;
		this.password = password;
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
		return "TrabajadorPostRequest [username=" + username + ", password=" + password + ", zooId=" + zooId
				+ ", rolId=" + rolId + "]";
	}

}

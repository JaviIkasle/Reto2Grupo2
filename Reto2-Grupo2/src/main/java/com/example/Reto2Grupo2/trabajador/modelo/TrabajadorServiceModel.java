package com.example.Reto2Grupo2.trabajador.modelo;

import com.example.Reto2Grupo2.zoo.modelo.Zoo;

public class TrabajadorServiceModel {

	private Integer id;

	private String username;

	private String password;

	private Zoo zoo;

	private Integer zooId;

	public TrabajadorServiceModel() {

	}

	public TrabajadorServiceModel(Integer id, String username, String password, Zoo zoo, Integer zooId) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.zoo = zoo;
		this.zooId = zooId;
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

	public Zoo getZoo() {
		return zoo;
	}

	public void setZoo(Zoo zoo) {
		this.zoo = zoo;
	}

	public Integer getZooId() {
		return zooId;
	}

	public void setZooId(Integer zooId) {
		this.zooId = zooId;
	}

	@Override
	public String toString() {
		return "TrabajadorServiceModel [id=" + id + ", username=" + username + ", password=" + password + ", zoo=" + zoo
				+ ", zooId=" + zooId + "]";
	}

}

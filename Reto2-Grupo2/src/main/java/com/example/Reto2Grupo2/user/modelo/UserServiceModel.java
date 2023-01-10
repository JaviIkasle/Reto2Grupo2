package com.example.Reto2Grupo2.user.modelo;

import com.example.Reto2Grupo2.rol.modelo.RolServiceModel;
import com.example.Reto2Grupo2.zoo.modelo.ZooServiceModel;

public class UserServiceModel {

	private Integer id;

	private String username;

	private String password;

	private ZooServiceModel zoo;

	private Integer zooId;

	private RolServiceModel rol;

	private Integer rolId;

	public UserServiceModel() {

	}

	public UserServiceModel(Integer id, String username, String password, ZooServiceModel zoo, Integer zooId,
			RolServiceModel rol, Integer rolId) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.zoo = zoo;
		this.zooId = zooId;
		this.rol = rol;
		this.rolId = rolId;
	}
	
	public UserServiceModel(Integer id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
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

	public ZooServiceModel getZoo() {
		return zoo;
	}

	public void setZoo(ZooServiceModel zoo) {
		this.zoo = zoo;
	}

	public Integer getZooId() {
		return zooId;
	}

	public void setZooId(Integer zooId) {
		this.zooId = zooId;
	}

	public RolServiceModel getRol() {
		return rol;
	}

	public void setRol(RolServiceModel rol) {
		this.rol = rol;
	}

	public Integer getRolId() {
		return rolId;
	}

	public void setRolId(Integer rolId) {
		this.rolId = rolId;
	}

	@Override
	public String toString() {
		return "TrabajadorServiceModel [id=" + id + ", username=" + username + ", password=" + password + ", zoo=" + zoo
				+ ", zooId=" + zooId + ", rol=" + rol + ", rolId=" + rolId + "]";
	}

}

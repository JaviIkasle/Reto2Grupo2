package com.example.Reto2Grupo2.user.modelo;

public class UserPostRequest {

	private String username;

	private String password;
	
	private String email;

	private Integer zooId;

	private Integer rolId;

	public UserPostRequest() {
	}

	public UserPostRequest(String username, String password, String email, Integer zooId, Integer rolId) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.zooId = zooId;
		this.rolId = rolId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String email) {
		this.username = email;
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
	
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserPostRequest [username=" + username + ", password=" + password + ", email=" + email + ", zooId="
				+ zooId + ", rolId=" + rolId + "]";
	}

}

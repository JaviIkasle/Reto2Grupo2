package com.example.Reto2Grupo2.user.modelo;

public class AuthRequestEmple {

	private String username;
	private String password;
	private String email;
	private Integer zooId;

	public AuthRequestEmple() {
	}

	public AuthRequestEmple(String username, String password, String email, Integer zooId) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.zooId = zooId;
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

	public Integer getZooId() {
		return zooId;
	}

	public void setZooId(Integer zooId) {
		this.zooId = zooId;
	}

	@Override
	public String toString() {
		return "AuthRequestEmple [username=" + username + ", password=" + password + ", email=" + email + ", zooId="
				+ zooId + "]";
	}

}

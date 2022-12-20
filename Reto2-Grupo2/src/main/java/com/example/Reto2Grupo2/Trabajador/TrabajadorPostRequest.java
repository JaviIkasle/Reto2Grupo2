package com.example.Reto2Grupo2.Trabajador;






public class TrabajadorPostRequest {
	

	  private Integer id;
	  
	  private String username;
	
	  private String password;
	  
	  
	 

	public TrabajadorPostRequest() {
		super();
	}

	public TrabajadorPostRequest(Integer id, String username, String password) {
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

	@Override
	public String toString() {
		return "TrabajadorPostRequest [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
	   	
	
	

}

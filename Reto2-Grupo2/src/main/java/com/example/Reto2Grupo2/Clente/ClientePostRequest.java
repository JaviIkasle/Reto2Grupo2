package com.example.Reto2Grupo2.Clente;

import com.example.Reto2Grupo2.Users.UsersPostRequest;

public class ClientePostRequest extends UsersPostRequest {
	
	public Integer edad;

	
	
	
	
	
	public ClientePostRequest() {
		super();
	}

	public ClientePostRequest(Integer edad) {
		super();
		this.edad = edad;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	@Override
	public String toString() {
		return super.toString() +",edad=" + edad + "]";
	}
	
	
	

}

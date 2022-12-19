package com.example.Reto2Grupo2.Clente;

import com.example.Reto2Grupo2.Users.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name="Cliente")
public class Cliente extends Users{
	
	
	@Column(length = 60)
	public Integer edad;
	
	
	
	
	

	public Cliente() {
		super();
	}

	public Cliente(Integer edad) {
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
		return super.toString() +", edad=" + edad + "]";
	}
	
	
	
	
	
	
}

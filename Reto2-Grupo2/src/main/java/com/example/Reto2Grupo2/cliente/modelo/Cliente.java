package com.example.Reto2Grupo2.cliente.modelo;

import java.util.List;

import com.example.Reto2Grupo2.billete.modelo.Billete;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="Clientes")
public class Cliente {
	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
	   @Column(length = 60)
	   	private String email;
	   @Column(length = 60)
	  	private String password;
	   
	   @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	   @JsonBackReference
	   private List<Billete> billetes;
	   


	public Cliente() {
			}

	public Cliente(Integer id, String email, String password, List<Billete> billetes) {
		
		this.id = id;
		this.email = email;
		this.password = password;
		this.billetes = billetes;
	}
	
	public Cliente( String email, String password) {
		
		
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

	public List<Billete> getBilletes() {
		return billetes;
	}

	public void setBilletes(List<Billete> billetes) {
		this.billetes = billetes;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", email=" + email + ", password=" + password + "]";
	}
	   
	   
	   
	
	
	
}


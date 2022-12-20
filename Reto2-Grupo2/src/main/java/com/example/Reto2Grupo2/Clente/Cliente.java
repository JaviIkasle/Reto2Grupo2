package com.example.Reto2Grupo2.Clente;




import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="Cliente")
public class Cliente {
	
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
	   @Column(length = 60)
	   	private String email;
	   @Column(length = 60)
	  	private String password;
	   
	   
	   
	public Cliente() {
		super();
	}
	public Cliente(Integer id, String email, String password) {
		super();
		this.id = id;
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
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", email=" + email + ", password=" + password + "]";
	}
	  	
	
	
	
	
	
}

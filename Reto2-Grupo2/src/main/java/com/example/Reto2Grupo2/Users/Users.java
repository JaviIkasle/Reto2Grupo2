package com.example.Reto2Grupo2.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Users")
public class Users {
	

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
		 @Column(length = 60)
		private String nombre;
		 @Column(length = 60)
		private String apellido;
		 @Column(length = 60)
		private String email;
		 @Column(length = 30)
		private String password;
		 
	
		 
		public Users() {
			super();
		}
		public Users(Integer id, String nombre, String apellido, String email, String password) {
			super();
			this.id = id;
			this.nombre = nombre;
			this.apellido = apellido;
			this.email = email;
			this.password = password;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getApellido() {
			return apellido;
		}
		public void setApellido(String apellido) {
			this.apellido = apellido;
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
			return "Users [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email
					+ ", password=" + password ;
		}
		 
		
		
	    
	
	
}

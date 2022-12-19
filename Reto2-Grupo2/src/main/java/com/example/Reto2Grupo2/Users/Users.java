package com.example.Reto2Grupo2.Users;

import com.example.Reto2Grupo2.rol.modelo.Rol;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 60)
	private String nombre;
	@Column(length = 100)
	private String email;
	@Column(length = 60)
	private String apellido;
	@Column(length = 30)
	private String password;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_rol", foreignKey = @ForeignKey(name = "FK_id_rol"))
	@JsonManagedReference
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Rol rol;

	@Column(name = "id_rol", insertable = false, updatable = false)
	private Integer idRol;

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

	public Users(int id, String nombre, String email, String apellido, String password, Rol rol, Integer idRol) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.apellido = apellido;
		this.password = password;
		this.rol = rol;
		this.idRol = idRol;
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

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", nombre=" + nombre + ", email=" + email + ", apellido=" + apellido + ", password="
				+ password + ", rol=" + rol + ", idRol=" + idRol + "]";
	}

}

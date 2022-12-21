package com.example.Reto2Grupo2.trabajador;

import com.example.Reto2Grupo2.rol.modelo.Rol;
import com.example.Reto2Grupo2.zoo.modelo.Zoo;
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
@Table(name = "trabajadores")
public class Trabajador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 60)
	private String username;
	@Column(length = 60)
	private String password;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_zoo", foreignKey = @ForeignKey(name = "FK_id_zooTrabajador"))
	@JsonManagedReference
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Zoo zoo;

	@Column(name = "id_zoo", insertable = false, updatable = false)
	private Integer idZoo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_rol", foreignKey=@ForeignKey(name = "FK_id_trabajadorRoles"))
	@JsonManagedReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Rol rol;
	
	@Column(name="id_rol", insertable=false, updatable=false)
	private int idRol;
	
	public Trabajador() {
		super();
	}

	public Trabajador(Integer id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;

	}

	public Trabajador(Integer id, String username, String password, Zoo zoo, Integer idZoo, Rol rol, int idRol) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.zoo = zoo;
		this.idZoo = idZoo;
		this.rol = rol;
		this.idRol = idRol;
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

	public Zoo getZoo() {
		return zoo;
	}

	public void setZoo(Zoo zoo) {
		this.zoo = zoo;
	}

	public Integer getIdZoo() {
		return idZoo;
	}

	public void setIdZoo(Integer idZoo) {
		this.idZoo = idZoo;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	@Override
	public String toString() {
		return "Trabajador [id=" + id + ", username=" + username + ", password=" + password + ", zoo=" + zoo
				+ ", idZoo=" + idZoo + ", rol=" + rol + ", idRol=" + idRol + "]";
	}
}
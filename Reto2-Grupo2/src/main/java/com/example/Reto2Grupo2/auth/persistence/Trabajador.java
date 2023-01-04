package com.example.Reto2Grupo2.auth.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class Trabajador implements UserDetails{

	private static final long serialVersionUID = 1285514705158443296L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 60, unique = true)
	private String username;
	@Column(length = 60)
	private String password;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "zoo_id", foreignKey = @ForeignKey(name = "FK_zoo_id_Trab"))
	@JsonManagedReference
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Zoo zoo;

	@Column(name = "zoo_id", insertable = false, updatable = false)
	private Integer zooId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rol_id", foreignKey = @ForeignKey(name = "FK_id_trabajadorRoles"))
	@JsonManagedReference
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Rol rol;

	@Column(name = "rol_id", insertable = false, updatable = false)
	private Integer rolId;

	public Trabajador() {

	}

	public Trabajador( String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public Trabajador(Integer id, String username, String password, Zoo zoo, Integer zooId, Rol rol, int rolId) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.zoo = zoo;
		this.zooId = zooId;
		this.rol = rol;
		this.rolId = rolId;
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

	public Integer getZooId() {
		return zooId;
	}

	public void setZooId(Integer zooId) {
		this.zooId = zooId;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Integer getRolId() {
		return rolId;
	}

	public void setRolId(Integer rolId) {
		this.rolId = rolId;
	}

	@Override
	public String toString() {
		return "Trabajador [id=" + id + ", username=" + username + ", password=" + password + ", zoo=" + zoo
				+ ", zooId=" + zooId + ", rol=" + rol + ", rolId=" + rolId + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();	
		authorities.add(new SimpleGrantedAuthority(rol.getName()));		
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
	
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
	
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
	
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
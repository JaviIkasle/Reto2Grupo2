package com.example.Reto2Grupo2.user.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.Reto2Grupo2.rol.modelo.Rol;
import com.example.Reto2Grupo2.trabajador.modelo.Trabajador;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

//@Entity
//@Table(name = "users")
@Entity  
@Table(name = "users")  
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)  
@DiscriminatorColumn(name="type",discriminatorType=DiscriminatorType.STRING)  
@DiscriminatorValue(value="user")  
public class User implements UserDetails{

	private static final long serialVersionUID = 1285514705158443296L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//TODO no funciona el unique 
	@Column(length = 60, unique = true)
	private String username;
	@Column(length = 60)
	private String password;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "zoo_id", foreignKey = @ForeignKey(name = "FK_zoo_id_Trab"))
//	@JsonManagedReference
//	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
//	private Zoo zoo;
	

//	@Column(name = "zoo_id", insertable = false, updatable = false)
//	private Integer zooId;

//	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    private Trabajador trabajador;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rol_id", foreignKey = @ForeignKey(name = "FK_id_usersRole"))
	@JsonManagedReference
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Rol rol;

	@Column(name = "rol_id", insertable = false, updatable = false)
	private Integer rolId;

	public User() {

	}

	public User( String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	
	
public User(Integer id, String username, String password, Trabajador trabajador, Rol rol, Integer rolId) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;

		this.rol = rol;
		this.rolId = rolId;
	}

//	public User(Integer id, String username, String password, Zoo zoo, Integer zooId, Rol rol, int rolId) {
//		super();
//		this.id = id;
//		this.username = username;
//		this.password = password;
//		this.zoo = zoo;
//		this.zooId = zooId;
//		this.rol = rol;
//		this.rolId = rolId;
//	}

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


//
//	public Trabajador getTrabajador() {
//		return trabajador;
//	}
//
//	public void setTrabajador(Trabajador trabajador) {
//		this.trabajador = trabajador;
//	}

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
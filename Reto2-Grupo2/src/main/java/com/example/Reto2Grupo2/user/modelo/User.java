package com.example.Reto2Grupo2.user.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.Reto2Grupo2.billete.modelo.Billete;
import com.example.Reto2Grupo2.rol.modelo.Rol;
import com.example.Reto2Grupo2.zoo.modelo.Zoo;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User implements UserDetails {

	private static final long serialVersionUID = 1285514705158443296L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 60, unique = true)
	private String username;
	@Column(length = 60)
	private String password;
	@Column(length = 60)
	private String email;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "zoo_id", foreignKey = @ForeignKey(name = "FK_zoo_id_user"))
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

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonBackReference
	private List<Billete> billetes;

	public User() {

	}

	// Para un cliente 
	public User(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;

	}

	public User(String username, String password, String email, Integer rolId) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.rolId = rolId;
	}

	public User(Integer id, String username, String password, String email, Zoo zoo, Integer zooId, Rol rol,
			Integer rolId, List<Billete> billetes) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.zoo = zoo;
		this.zooId = zooId;
		this.rol = rol;
		this.rolId = rolId;
		this.billetes = billetes;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Billete> getBilletes() {
		return billetes;
	}

	public void setBilletes(List<Billete> billetes) {
		this.billetes = billetes;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", zoo="
				+ zoo + ", zooId=" + zooId + ", rol=" + rol + ", rolId=" + rolId + ", billetes=" + billetes + "]";
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
		return true;
	}

}
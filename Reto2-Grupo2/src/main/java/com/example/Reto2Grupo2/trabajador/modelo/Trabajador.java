package com.example.Reto2Grupo2.trabajador.modelo;

import com.example.Reto2Grupo2.user.modelo.User;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity  
@DiscriminatorValue("trabajadores")  
public class Trabajador  extends User{

	private static final long serialVersionUID = -5925237624899349728L;

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;

	@Column(length = 60)
	private String especializacion;

	@Column(length = 60)
	private String puesto;

//	@JoinColumn(name = "user_id")
//	@OneToOne(fetch = FetchType.LAZY)
//	private User user;

	public Trabajador() {
	}

	public Trabajador(String especializacion, String puesto) {
		super();
		this.especializacion = especializacion;
		this.puesto = puesto;
	}

	public String getEspecializacion() {
		return especializacion;
	}

	public void setEspecializacion(String especializacion) {
		this.especializacion = especializacion;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

}

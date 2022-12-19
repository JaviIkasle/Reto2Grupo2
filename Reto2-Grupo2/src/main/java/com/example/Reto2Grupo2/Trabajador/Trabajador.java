//package com.example.Reto2Grupo2.Trabajador;
//
//import java.util.Date;
//
//import com.example.Reto2Grupo2.Users.Users;
//import com.fasterxml.jackson.annotation.JsonFormat;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name="Trabajdor")
//
//public class Trabajador extends Users{
//	
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ", locale = "en_GB") 
//	private Date fec_alta;
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ", locale = "en_GB") 
//	private Date fec_baja;
//
//	@Column(length = 60)
//	private Double sueldo;
//	
//	
//	
//	
//	
//
//	public Trabajador() {
//		super();
//	}
//
//	public Trabajador(Date fec_alta, Date fec_baja, Double sueldo) {
//		super();
//		this.fec_alta = fec_alta;
//		this.fec_baja = fec_baja;
//		this.sueldo = sueldo;
//	}
//
//	public Date getFec_alta() {
//		return fec_alta;
//	}
//
//	public void setFec_alta(Date fec_alta) {
//		this.fec_alta = fec_alta;
//	}
//
//	public Date getFec_baja() {
//		return fec_baja;
//	}
//
//	public void setFec_baja(Date fec_baja) {
//		this.fec_baja = fec_baja;
//	}
//
//	public Double getSueldo() {
//		return sueldo;
//	}
//
//	public void setSueldo(Double sueldo) {
//		this.sueldo = sueldo;
//	}
//
//	@Override
//	public String toString() {
//		return super.toString() +",fec_alta=" + fec_alta + ", fec_baja=" + fec_baja + ", sueldo=" + sueldo + "]";
//	}
//	
//	
//	
//
//}

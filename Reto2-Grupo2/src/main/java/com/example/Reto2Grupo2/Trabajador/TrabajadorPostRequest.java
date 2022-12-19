package com.example.Reto2Grupo2.Trabajador;

import java.util.Date;

import com.example.Reto2Grupo2.Users.UsersPostRequest;


public class TrabajadorPostRequest extends UsersPostRequest{
	
	
	private Date fec_alta;
	private Date fec_baja;
	private Double sueldo;
	
	
	
	
	
	
	public TrabajadorPostRequest() {
		super();
	}
	public TrabajadorPostRequest(Date fec_alta, Date fec_baja, Double sueldo) {
		super();
		this.fec_alta = fec_alta;
		this.fec_baja = fec_baja;
		this.sueldo = sueldo;
	}
	public Date getFec_alta() {
		return fec_alta;
	}
	public void setFec_alta(Date fec_alta) {
		this.fec_alta = fec_alta;
	}
	public Date getFec_baja() {
		return fec_baja;
	}
	public void setFec_baja(Date fec_baja) {
		this.fec_baja = fec_baja;
	}
	public Double getSueldo() {
		return sueldo;
	}
	public void setSueldo(Double sueldo) {
		this.sueldo = sueldo;
	}
	@Override
	public String toString() {
		return super.toString() +",fec_alta=" + fec_alta + ", fec_baja=" + fec_baja + ", sueldo=" + sueldo + "]";
	}
	
	
	
	
	
	

}

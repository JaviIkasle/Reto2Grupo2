package com.example.Reto2Grupo2.billete.modelo;

import java.sql.Date;

public class BilletePostRequest {
	


	private Date fecha;
	
	private int cantidad;

	private float importe;
	
	private 
	
	

	public BilletePostRequest() {
		super();
	}



	public BilletePostRequest(Date fecha, int cantidad, float importe) {
		super();
		
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.importe = importe;
	}



	


	public Date getFecha() {
		return fecha;
	}



	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}



	public int getCantidad() {
		return cantidad;
	}



	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}



	public float getImporte() {
		return importe;
	}



	public void setImporte(float importe) {
		this.importe = importe;
	}



	@Override
	public String toString() {
		return "BilletePostRequest [ fecha=" + fecha + ", cantidad=" + cantidad + ", importe=" + importe
				+ "]";
	}

	
	
	

}

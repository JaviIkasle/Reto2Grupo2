package com.example.Reto2Grupo2.billete.modelo;

import java.sql.Date;



public class BilleteServiceModel {

	

	private int id;

	private Date fecha;
	
	private int cantidad;

	private float importe;

	
	
	


	public BilleteServiceModel() {
		super();
	}

	public BilleteServiceModel(int id, Date fecha, int cantidad, float importe) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.importe = importe;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return "BilleteServiceModel [id=" + id + ", fecha=" + fecha + ", cantidad=" + cantidad + ", importe=" + importe
				+ "]";
	}
	
	
	
	
}

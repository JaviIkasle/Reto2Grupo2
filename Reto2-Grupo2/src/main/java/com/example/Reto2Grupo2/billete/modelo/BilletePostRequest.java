package com.example.Reto2Grupo2.billete.modelo;

import java.sql.Date;

public class BilletePostRequest {

	private Date fecha;

	private int cantidad;

	private float importe;

	private Integer idZoo;

	private Integer idCliente;

	public BilletePostRequest() {
		super();
	}

	public BilletePostRequest(Date fecha, int cantidad, float importe, Integer idZoo, Integer idCliente) {
		super();
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.importe = importe;
		this.idZoo = idZoo;
		this.idCliente = idCliente;
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

	public Integer getIdZoo() {
		return idZoo;
	}

	public void setIdZoo(Integer idZoo) {
		this.idZoo = idZoo;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	@Override
	public String toString() {
		return "BilletePostRequest [fecha=" + fecha + ", cantidad=" + cantidad + ", importe=" + importe + ", idZoo="
				+ idZoo + ", idCliente=" + idCliente + "]";
	}

}

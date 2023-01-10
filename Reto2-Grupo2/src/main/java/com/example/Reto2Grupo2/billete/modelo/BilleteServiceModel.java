package com.example.Reto2Grupo2.billete.modelo;

import java.sql.Date;
import com.example.Reto2Grupo2.user.modelo.UserServiceModel;
import com.example.Reto2Grupo2.zoo.modelo.ZooServiceModel;

public class BilleteServiceModel {

	private int id;
	private Date fecha;
	private int cantidad;
	private float importe;
	private ZooServiceModel zoo;
	private Integer idZoo;
	private UserServiceModel user;
	private Integer userId;

	public BilleteServiceModel() {
		super();
	}

	public BilleteServiceModel(int id, Date fecha, int cantidad, float importe, Integer idZoo, Integer idCliente) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.importe = importe;
		this.idZoo = idZoo;
		this.userId = idCliente;
	}

	
	public BilleteServiceModel(int id, Date fecha, int cantidad, float importe, ZooServiceModel zoo, Integer idZoo,
			UserServiceModel cliente, Integer idCliente) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.importe = importe;
		this.zoo = zoo;
		this.idZoo = idZoo;
		this.user = cliente;
		this.userId = idCliente;
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

	public ZooServiceModel getZoo() {
		return zoo;
	}

	public void setZoo(ZooServiceModel zoo) {
		this.zoo = zoo;
	}

	public Integer getIdZoo() {
		return idZoo;
	}

	public void setIdZoo(Integer idZoo) {
		this.idZoo = idZoo;
	}

	public UserServiceModel getCliente() {
		return user;
	}

	public void setCliente(UserServiceModel cliente) {
		this.user = cliente;
	}

	public Integer getIdCliente() {
		return userId;
	}

	public void setIdCliente(Integer idCliente) {
		this.userId = idCliente;
	}

	@Override
	public String toString() {
		return "BilleteServiceModel [id=" + id + ", fecha=" + fecha + ", cantidad=" + cantidad + ", importe=" + importe
				+ ", zoo=" + zoo + ", idZoo=" + idZoo + ", cliente=" + user + ", idCliente=" + userId + "]";
	}

}

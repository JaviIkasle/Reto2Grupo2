package com.example.Reto2Grupo2.billete.modelo;

import java.sql.Date;

import com.example.Reto2Grupo2.cliente.Cliente;
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
@Table(name = "billetes")
public class Billete {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private Date fecha;
	@Column
	private int cantidad;
	@Column
	private float importe;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_zoo", foreignKey=@ForeignKey(name = "FK_id_zooBillete"))
	@JsonManagedReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Zoo zoo;
	
	@Column(name="id_zoo", insertable=false, updatable=false)
	private int idZoo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente", foreignKey=@ForeignKey(name = "FK_id_zooCliente"))
	@JsonManagedReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Cliente cliente;
	
	@Column(name="id_cliente", insertable=false, updatable=false)
	private int idCliente;
	
	public Billete() {}
	
	
	

	public Billete(Date fecha, int cantidad, float importe, int idZoo, int idCliente) {
		super();
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.importe = importe;
		this.idZoo = idZoo;
		this.idCliente = idCliente;
	}




	public Billete( Date fecha, int cantidad, float importe) {
		super();
		
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.importe = importe;
	}

	public Billete(int id, Date fecha, int cantidad, float importe, Zoo zoo, int idZoo, Cliente cliente,
			int idCliente) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.importe = importe;
		this.zoo = zoo;
		this.idZoo = idZoo;
		this.cliente = cliente;
		this.idCliente = idCliente;
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

	public Zoo getZoo() {
		return zoo;
	}

	public void setZoo(Zoo zoo) {
		this.zoo = zoo;
	}

	public int getIdZoo() {
		return idZoo;
	}

	public void setIdZoo(int idZoo) {
		this.idZoo = idZoo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	@Override
	public String toString() {
		return "Billete [id=" + id + ", fecha=" + fecha + ", cantidad=" + cantidad + ", importe=" + importe + ", zoo="
				+ zoo + ", idZoo=" + idZoo + ", cliente=" + cliente + ", idCliente=" + idCliente + "]";
	}
}
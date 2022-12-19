package com.example.Reto2Grupo2.billete.modelo;

import java.sql.Date;

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
	@Column(length = 60)
	private String tipo;
	@Column
	private float importe;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_zoo", foreignKey=@ForeignKey(name = "FK_id_zooBillete"))
	@JsonManagedReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Zoo zoo;
	
	@Column(name="id_zoo", insertable=false, updatable=false)
	private Integer idZoo;
	
	public Billete() {}

	public Billete(int id, Date fecha, String tipo, float importe) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.tipo = tipo;
		this.importe = importe;
	}

	public Billete(int id, Date fecha, String tipo, float importe, Zoo zoo, Integer idZoo) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.tipo = tipo;
		this.importe = importe;
		this.zoo = zoo;
		this.idZoo = idZoo;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public Integer getIdZoo() {
		return idZoo;
	}

	public void setIdZoo(Integer idZoo) {
		this.idZoo = idZoo;
	}

	@Override
	public String toString() {
		return "Billete [id=" + id + ", fecha=" + fecha + ", tipo=" + tipo + ", importe=" + importe + ", zoo=" + zoo
				+ ", idZoo=" + idZoo + "]";
	}

}

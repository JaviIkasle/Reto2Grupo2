package com.example.Reto2Grupo2.evento.modelo;

import java.sql.Date;

//©inaki©
public class EventoPostRequest {

	// harian falta validaciones en un proyecto real
	private String name;
	private String infor;
	private Date fecha;

	public EventoPostRequest() {
	}
	

	public EventoPostRequest(String name, String infor, Date fecha) {
		super();
		this.name = name;
		this.infor = infor;
		this.fecha = fecha;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfor() {
		return infor;
	}

	public void setInfor(String infor) {
		this.infor = infor;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "EventoPostRequest [name=" + name + ", infor=" + infor + ", fecha=" + fecha + "]";
	}

	
	

}

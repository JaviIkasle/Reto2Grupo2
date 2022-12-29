package com.example.Reto2Grupo2.evento.service;

import java.util.List;
import com.example.Reto2Grupo2.evento.modelo.EventoPostRequest;
import com.example.Reto2Grupo2.evento.modelo.EventoServiceModel;
import com.example.Reto2Grupo2.evento.modelo.EventosExpands;


public interface EventoServiceImpl {

	List<EventoServiceModel> getEventos();

	EventoServiceModel getEventoById(Integer id, List<EventosExpands> expand );

	EventoServiceModel create( EventoPostRequest eventoPostRequest);

	EventoServiceModel updateById(Integer id, EventoPostRequest eventoPostRequest);

//	Integer deleteById(Integer id);

}

	


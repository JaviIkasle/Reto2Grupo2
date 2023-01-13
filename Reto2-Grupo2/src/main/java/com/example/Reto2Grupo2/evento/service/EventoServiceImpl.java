package com.example.Reto2Grupo2.evento.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.Reto2Grupo2.evento.modelo.EventoPostRequest;
import com.example.Reto2Grupo2.evento.modelo.EventoServiceModel;
import com.example.Reto2Grupo2.evento.modelo.EventosExpands;


public interface EventoServiceImpl {

	List<EventoServiceModel> getEventos(Integer userId);

	EventoServiceModel getEventoById(Integer id, List<EventosExpands> expand, Integer userId);

	EventoServiceModel create( EventoPostRequest eventoPostRequest, Integer userId);

	EventoServiceModel updateById(Integer id, EventoPostRequest eventoPostRequest, Integer userId);

	ResponseEntity<EventoServiceModel>  deleteById(Integer id, Integer userId);

}

	


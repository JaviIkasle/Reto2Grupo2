package com.example.Reto2Grupo2.evento.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.example.Reto2Grupo2.evento.modelo.EventoPostRequest;
import com.example.Reto2Grupo2.evento.modelo.EventoServiceModel;
import com.example.Reto2Grupo2.evento.modelo.EventosExpands;
import com.example.Reto2Grupo2.evento.repository.EventoRepository;
import com.example.Reto2Grupo2.evento.service.EventoServiceImpl;

@RestController
@RequestMapping("api")
public class EventoController {

	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private EventoServiceImpl eventoService;



	@GetMapping("/eventos/get")
	public ResponseEntity<List<EventoServiceModel>> getEventos() {

		List<EventoServiceModel> response = eventoService.getEventos();
		return new ResponseEntity<List<EventoServiceModel>>(response, HttpStatus.OK);
	}

	@GetMapping("/eventos/{id}")
	public ResponseEntity<EventoServiceModel> getEventoById(@PathVariable("id") Integer id,
			@RequestParam(required = false) List<EventosExpands> expand) {

		EventoServiceModel response = eventoService.getEventoById(id, expand);
		return new ResponseEntity<EventoServiceModel>(response, HttpStatus.OK);
	}

	@PostMapping("/eventos/create")
	public ResponseEntity<EventoServiceModel> createEvento(@RequestBody EventoPostRequest eventoPostRequest) {

		EventoServiceModel eventoResponse = eventoService.create(eventoPostRequest);
		return new ResponseEntity<EventoServiceModel>(eventoResponse, HttpStatus.CREATED);
	}

	@PutMapping("/eventos/update/{id}")
	public ResponseEntity<EventoServiceModel> updateEvento(@PathVariable("id") Integer id,
			@RequestBody EventoPostRequest eventoPostRequest) {

		EventoServiceModel eventoResponse = eventoService.updateById(id, eventoPostRequest);
		return new ResponseEntity<EventoServiceModel>(eventoResponse, HttpStatus.OK);
	}

	@DeleteMapping("/eventos/delete/{id}")
	public ResponseEntity<Integer> deleteEventoById(@PathVariable("id") Integer id) {

		try {
			eventoRepository.deleteById(id);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} catch (EmptyResultDataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Evento no encontrado");
		}
	}

}

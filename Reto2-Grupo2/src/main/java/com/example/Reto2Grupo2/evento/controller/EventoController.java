package com.example.Reto2Grupo2.evento.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.Reto2Grupo2.evento.modelo.Evento;
import com.example.Reto2Grupo2.evento.modelo.EventoPostRequest;
import com.example.Reto2Grupo2.evento.repository.EventoRepository;

@RestController
@RequestMapping("api")
public class EventoController {

	@Autowired
	private EventoRepository eventoRepository;

	@GetMapping("/eventos")
	public ResponseEntity<Iterable<Evento>> getEventos() {
		return new ResponseEntity<Iterable<Evento>>(eventoRepository.findAll(), HttpStatus.OK);
	}

	@GetMapping("/eventos/{id}")
	public ResponseEntity<Evento> getEventoById(@PathVariable("id") Integer id) {
		Evento evento = eventoRepository.findById(id).orElseThrow(

				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Evento no encontrado"));
		return new ResponseEntity<Evento>(evento, HttpStatus.OK);
	}

	@PostMapping("/eventos")
	public ResponseEntity<Evento> createEvento(@RequestBody EventoPostRequest eventoPostRequest) {
		Evento evento = new Evento(
				eventoPostRequest.getNombre(), 
				eventoPostRequest.getInformacion(),
				eventoPostRequest.getFecha());

		Evento eventoResponse = eventoRepository.save(evento);
		return new ResponseEntity<Evento>(eventoResponse, HttpStatus.CREATED);
	}

	@PutMapping("/eventos/{id}")
	public ResponseEntity<Evento> updateEvento(
			@PathVariable("id") Integer id,
			@RequestBody EventoPostRequest eventoPostRequest) {
		
		boolean eventoExist = eventoRepository.existsById(id);
		//controlar que se modifica un registro que no existe, como en ZooController.Put
		Evento evento = new Evento(
				id,
				eventoPostRequest.getNombre(), 
				eventoPostRequest.getInformacion(),
				eventoPostRequest.getFecha());

		Evento eventoResponse = eventoRepository.save(evento);
		
		if(eventoExist) {
			return new ResponseEntity<Evento>(eventoResponse, HttpStatus.OK);
		}else {
			return new ResponseEntity<Evento>(eventoResponse, HttpStatus.CREATED);
		}
		
	}

	@DeleteMapping("/eventos/{id}")
	public ResponseEntity<Integer> deleteEventoById(@PathVariable("id") Integer id) {

		try {
			eventoRepository.deleteById(id);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} catch (EmptyResultDataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Evento no encontrado");
		}
	}

}

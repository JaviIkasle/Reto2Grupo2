package com.example.Reto2Grupo2.evento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public ResponseEntity<Iterable<Evento>> getEmployees() {
		return new ResponseEntity<Iterable<Evento>>(eventoRepository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/evento/{id}")
	public ResponseEntity<Evento> getEmployeeById(@PathVariable("id") Integer id) {

		Evento evento = eventoRepository.findById(id).orElseThrow(

				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "No existe el evento"));
		return new ResponseEntity<Evento>(evento, HttpStatus.OK);

	}

	@PostMapping("/eventos")
	public ResponseEntity<Evento> createEvento(@RequestBody EventoPostRequest eventoPostRequest) {
		Evento evento = new Evento(eventoPostRequest.getName(), eventoPostRequest.getInfor(),
				eventoPostRequest.getFecha());
		Evento eventoResponse = eventoRepository.save(evento);
		return new ResponseEntity<Evento>(eventoResponse, HttpStatus.CREATED);
	}
}

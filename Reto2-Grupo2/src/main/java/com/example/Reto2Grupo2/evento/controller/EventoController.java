package com.example.Reto2Grupo2.evento.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.Reto2Grupo2.evento.modelo.EventoPostRequest;
import com.example.Reto2Grupo2.evento.modelo.EventoServiceModel;
import com.example.Reto2Grupo2.evento.modelo.EventosExpands;
import com.example.Reto2Grupo2.evento.service.EventoServiceImpl;
import com.example.Reto2Grupo2.user.modelo.User;

@RestController
@RequestMapping("api")
public class EventoController {

	@Autowired
	private EventoServiceImpl eventoService;


	@GetMapping("/eventos")
	public ResponseEntity<List<EventoServiceModel>> getEventos(Authentication authentication) {
		
		User userDetails = (User) authentication.getPrincipal();

		List<EventoServiceModel> response = eventoService.getEventos(userDetails.getId());
		return new ResponseEntity<List<EventoServiceModel>>(response, HttpStatus.OK);
	}


	//TODO comprobar expands, necesarios? hacerlo sieso
	@GetMapping("/eventos/{id}")
	public ResponseEntity<EventoServiceModel> getEventoById(@PathVariable("id") Integer id,
			@RequestParam(required = false) List<EventosExpands> expand,Authentication authentication ) {

		User userDetails = (User) authentication.getPrincipal();
		
		EventoServiceModel response = eventoService.getEventoById(id, expand, userDetails.getId());
		return new ResponseEntity<EventoServiceModel>(response, HttpStatus.OK);
	}

	
	@PostMapping("/eventos")
	public ResponseEntity<EventoServiceModel> createEvento(@RequestBody EventoPostRequest eventoPostRequest, Authentication authentication) {

		User userDetails = (User) authentication.getPrincipal();
		
		EventoServiceModel eventoResponse = eventoService.create(eventoPostRequest, userDetails.getId());
		return new ResponseEntity<EventoServiceModel>(eventoResponse, HttpStatus.CREATED);
	}

	@PutMapping("/eventos/{id}")
	public ResponseEntity<EventoServiceModel> updateEvento(@PathVariable("id") Integer id,
			@RequestBody EventoPostRequest eventoPostRequest, Authentication authentication) {

		User userDetails = (User) authentication.getPrincipal();
		
		EventoServiceModel eventoResponse = eventoService.updateById(id, eventoPostRequest, userDetails.getId());
		return new ResponseEntity<EventoServiceModel>(eventoResponse, HttpStatus.OK);
	}

	@DeleteMapping("/eventos/{id}")
	public void deleteEventoById(@PathVariable("id") Integer id, Authentication authentication ) {
		User userDetails = (User) authentication.getPrincipal();
		eventoService.deleteById(id, userDetails.getId());
		
		
//		try {
//			eventoRepository.deleteById(id);
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		} catch (EmptyResultDataAccessException e) {
//			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Evento no encontrado");
//		}
	}

}

package com.example.Reto2Grupo2.evento.controller;

import java.util.ArrayList;
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

import com.example.Reto2Grupo2.evento.modelo.Evento;
import com.example.Reto2Grupo2.evento.modelo.EventoPostRequest;
import com.example.Reto2Grupo2.evento.modelo.EventoServiceModel;
import com.example.Reto2Grupo2.evento.modelo.EventosExpands;
import com.example.Reto2Grupo2.evento.repository.EventoRepository;
import com.example.Reto2Grupo2.zoo.modelo.Zoo;
import com.example.Reto2Grupo2.zoo.modelo.ZooServiceModel;
import com.example.Reto2Grupo2.zoo.repository.ZooRepository;

@RestController
@RequestMapping("api")
public class EventoController {

	@Autowired
	private EventoRepository eventoRepository;
	
	@Autowired
	private ZooRepository zooRepository;

	@GetMapping("/eventos")
	public ResponseEntity<List<EventoServiceModel>> getEventos() {
		Iterable<Evento> eventos = eventoRepository.findAll();
		List<EventoServiceModel> response = new ArrayList<EventoServiceModel>();
		for (Evento evento : eventos) {
			response.add(
					new EventoServiceModel(
							evento.getId(),
							evento.getNombre(),
							evento.getInformacion(),
							evento.getFecha(),
							null,
							evento.getIdZoo()));
		}
		
		return new ResponseEntity<List<EventoServiceModel>>(response, HttpStatus.OK);
	}

	@GetMapping("/eventos/{id}")
	public ResponseEntity<EventoServiceModel> getEventoById(
			@PathVariable("id")Integer id,
			@RequestParam(required= false) List<EventosExpands> expand 
			) {
		Evento evento = eventoRepository.findById(id).orElseThrow(

				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Evento no encontrado"));
		
		//TODO deberia ir en el servicio
		ZooServiceModel zooResponse = null;
		
		if (expand != null&& expand.indexOf(EventosExpands.ZOO) != -1) {
			Zoo zooBD = evento.getZoo();
			
			zooResponse = new ZooServiceModel(
					zooBD.getId(),
					zooBD.getNombre(),
					zooBD.getPvpEntrada(),
					zooBD.getWeb(),
					zooBD.getInformacion(),
					zooBD.getLatitud(),
					zooBD.getLongitud(),
					zooBD.getCiudad(),
					zooBD.getPais(),
					null);
			
		}		
 		
		EventoServiceModel response = new EventoServiceModel(
													evento.getId(),
													evento.getNombre(),
													evento.getInformacion(),
													evento.getFecha(),
													zooResponse,
													evento.getIdZoo())
										;
		
		return new ResponseEntity<EventoServiceModel>(response, HttpStatus.OK);
	}

	@PostMapping("/eventos")
	public ResponseEntity<EventoServiceModel> createEvento(@RequestBody EventoPostRequest eventoPostRequest) {
		
		Zoo zoo = zooRepository.findById(eventoPostRequest.getZooId()).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Zoo no encontrado"));
		
		
		Evento evento = new Evento(
				null,
				eventoPostRequest.getNombre(), 
				eventoPostRequest.getInformacion(),
				eventoPostRequest.getFecha(),
				zoo
				);

		 evento = eventoRepository.save(evento);
		 EventoServiceModel eventoResponse = new EventoServiceModel(
				 										evento.getId(),
				 										evento.getNombre(),
				 										evento.getInformacion(),
				 										evento.getFecha(),
				 										null,
				 										zoo.getId()); //trampeado, evento.getZooId**
		 							
		return new ResponseEntity<EventoServiceModel>(eventoResponse, HttpStatus.CREATED);
	}

	@PutMapping("/eventos/{id}")
	public ResponseEntity<EventoServiceModel> updateEvento(
			@PathVariable("id") Integer id,
			@RequestBody EventoPostRequest eventoPostRequest) {
		
		//SI SE MODIFICA UN registro QUE NO EXISTE, PROVOCAMOS ESTE ERROR
		Evento evento = eventoRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.CONFLICT, "Evento no encontrado")
		);
		
		if(eventoPostRequest.getNombre()!=null && eventoPostRequest.getNombre()!= "") {
			evento.setNombre(eventoPostRequest.getNombre());
		}	
		if(eventoPostRequest.getInformacion()!=null && eventoPostRequest.getInformacion()!= "") {
			evento.setInformacion(eventoPostRequest.getInformacion());
		}
		if(eventoPostRequest.getFecha()!=null ) {
			evento.setFecha(eventoPostRequest.getFecha());
		}
		evento = eventoRepository.save(evento);
		
		
		Zoo zoo = zooRepository.findById(eventoPostRequest.getZooId()).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Zoo no encontrado"));
		
		 EventoServiceModel eventoResponse = new EventoServiceModel(
					evento.getId(),
					evento.getNombre(),
					evento.getInformacion(),
					evento.getFecha(),
					null,
					zoo.getId()); //trampeado, evento.getZooId**
			
		 return new ResponseEntity<EventoServiceModel>(eventoResponse,HttpStatus.OK);
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

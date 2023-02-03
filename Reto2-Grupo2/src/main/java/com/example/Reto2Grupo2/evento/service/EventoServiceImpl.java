package com.example.Reto2Grupo2.evento.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.Reto2Grupo2.evento.modelo.Evento;
import com.example.Reto2Grupo2.evento.modelo.EventoPostRequest;
import com.example.Reto2Grupo2.evento.modelo.EventoServiceModel;
import com.example.Reto2Grupo2.evento.modelo.EventosExpands;
import com.example.Reto2Grupo2.evento.repository.EventoRepository;
import com.example.Reto2Grupo2.user.modelo.User;
import com.example.Reto2Grupo2.user.repository.UserRepository;
import com.example.Reto2Grupo2.zoo.modelo.Zoo;
import com.example.Reto2Grupo2.zoo.repository.ZooRepository;

@Service
public class EventoServiceImpl  implements EventoService{

	@Autowired
	private EventoRepository eventoRepository;
	
	@Autowired
	private ZooRepository zooRepository;
	
	@Autowired
	private UserRepository userRepository ;	
	
	@Override
	public List<EventoServiceModel> getEventos(Integer userId) {
		User user = getUsuarioLogueado(userId);	
		
		Iterable<Evento> eventos = eventoRepository.findAll();		
		List<EventoServiceModel> response = new ArrayList<EventoServiceModel>();
		
		// si es un empleado, devuelve unicamente los eventos del zoo del empleado
		if (user.getZooId() != null) {
			
			for (Evento evento: eventos) {
				
				if(evento.getZooId() == user.getZooId()) {
					
					response.add(
							new EventoServiceModel(
									evento.getId(),
									evento.getNombre(),
									evento.getInformacion(),
									evento.getFecha(),
									null,
									evento.getZooId()));
				}						
			}
			
		}else {
			
			for (Evento evento : eventos) {
				response.add(
						new EventoServiceModel(
								evento.getId(),
								evento.getNombre(),
								evento.getInformacion(),
								evento.getFecha(),
								null,
								evento.getZooId()));
			}
		}
		
			return response;
	}



	@Override
	public EventoServiceModel getEventoById(Integer id, List<EventosExpands> expand, Integer userId ) {
		User user = getUsuarioLogueado(userId);	
		
		Evento evento = eventoRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Evento no encontrado"));
		
		EventoServiceModel response;
		
		// si es un empleado, devuelve unicamente los eventos del zoo del empleado
		if (user.getZooId() != null) {
									
			//si el evento buscado pertenece a tu zoo
			if (evento.getZooId() == user.getZooId()) {
				
				 response = new EventoServiceModel(
						evento.getId(),
						evento.getNombre(),
						evento.getInformacion(),
						evento.getFecha(),
						null,
						evento.getZooId());
			// sino pertenece a tu zoo...	
			}else {		
				
				throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Evento no pertenece a tu zoo");
		
			}
		//si no es un empleado..	
		}else{
			 response = new EventoServiceModel(
						evento.getId(),
						evento.getNombre(),
						evento.getInformacion(),
						evento.getFecha(),
						null,
						evento.getZooId());
			
		}

			
 		

		return response;
	}

	@Override
	public EventoServiceModel create(EventoPostRequest eventoPostRequest, Integer userId) {

		User user = getUsuarioLogueado(userId);
		
		// Creara un evento para el Zoo del empleado
		Zoo zoo = zooRepository.findById(user.getZooId()).orElseThrow(
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
				 										zoo.getId());
		 return eventoResponse;
	
	}

	@Override
	public EventoServiceModel updateById(Integer id, EventoPostRequest eventoPostRequest, Integer userId) {

		User user = getUsuarioLogueado(userId);
		
				//SI SE MODIFICA UN registro QUE NO EXISTE, PROVOCAMOS ESTE ERROR
				Evento evento = eventoRepository.findById(id).orElseThrow(
						() -> new ResponseStatusException(HttpStatus.CONFLICT, "Evento no encontrado"));
				
				Zoo zoo = zooRepository.findById(evento.getZooId()).orElseThrow(
						() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Zoo no encontrado"));
				
				 EventoServiceModel eventoResponse ;
								 
				 // si el evento pertenece a tu Zoo
					if (evento.getZooId() == user.getZooId()) {
						
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
						
							    eventoResponse = new EventoServiceModel(
								evento.getId(),
								evento.getNombre(),
								evento.getInformacion(),
								evento.getFecha(),
								null,
								zoo.getId());
						
					// si el evento a modificar no pertenece a tu Zoo	
					}else {												
						throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Evento no pertenece a tu zoo");
					}
			 
			
		return eventoResponse;
	}
	
	@Override
	public ResponseEntity<EventoServiceModel> deleteById(Integer id, Integer userId) {	
		User user = getUsuarioLogueado(userId);
		Evento evento = eventoRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.CONFLICT, "Evento no encontrado"));
		
		if (evento.getZooId() == user.getZooId()) {
			
			try {
				eventoRepository.deleteById(id);
				return new ResponseEntity<>(HttpStatus.OK);
			}catch (EmptyResultDataAccessException e) {
				throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Id de Evento no encontrada");
			}
			
		}else {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Evento no pertenece a tu zoo");
		}		
	}
	
	private User getUsuarioLogueado(Integer userId) {
		User user  = userRepository.findById(userId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "User no encontrado"));
		return user;
	}
}
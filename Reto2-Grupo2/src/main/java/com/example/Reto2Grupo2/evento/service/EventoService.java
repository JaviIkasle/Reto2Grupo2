package com.example.Reto2Grupo2.evento.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.Reto2Grupo2.evento.modelo.Evento;
import com.example.Reto2Grupo2.evento.modelo.EventoPostRequest;
import com.example.Reto2Grupo2.evento.modelo.EventoServiceModel;
import com.example.Reto2Grupo2.evento.modelo.EventosExpands;
import com.example.Reto2Grupo2.evento.repository.EventoRepository;
import com.example.Reto2Grupo2.zoo.modelo.Zoo;
import com.example.Reto2Grupo2.zoo.modelo.ZooServiceModel;
import com.example.Reto2Grupo2.zoo.repository.ZooRepository;

@Service
public class EventoService  implements EventoServiceImpl{

	@Autowired
	private EventoRepository eventoRepository;
	
	@Autowired
	private ZooRepository zooRepository;
	
	@Override
	public List<EventoServiceModel> getEventos() {
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
							evento.getZooId()));
		}		return response;
	}

	@Override
	public EventoServiceModel getEventoById(Integer id, List<EventosExpands> expand ) {
		Evento evento = eventoRepository.findById(id).orElseThrow(

				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Evento no encontrado"));
		
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
													evento.getZooId());
		return response;
	}

	@Override
	public EventoServiceModel create(EventoPostRequest eventoPostRequest) {

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
		 return eventoResponse;
	}

	@Override
	public EventoServiceModel updateById(Integer id, EventoPostRequest eventoPostRequest) {

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
							zoo.getId()); //Trampeado, evento.getZooId**, lo trae null......
		return eventoResponse;
	}

//	@Override
//	public Integer deleteById(Integer id) {	
//		Integer respuesta =0;
//		try {
//			eventoRepository.deleteById(id);
//			respuesta = 1;
//		} catch (EmptyResultDataAccessException e) {
//			respuesta = 2;
//		}
//		return 	respuesta;
//	}
}
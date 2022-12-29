package com.example.Reto2Grupo2.zoo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.Reto2Grupo2.zoo.modelo.Zoo;
import com.example.Reto2Grupo2.zoo.modelo.ZooPostRequest;
import com.example.Reto2Grupo2.zoo.modelo.ZooServiceModel;
import com.example.Reto2Grupo2.zoo.repository.ZooRepository;

@Service
public class ZooService implements ZooServiceImpl{
	@Autowired
	private ZooRepository zooRepository;
	
	@Override
	public List<ZooServiceModel> getZoos() {
		Iterable<Zoo> zoos = zooRepository.findAll();
		List<ZooServiceModel> response = new ArrayList<ZooServiceModel>();
		for (Zoo zoo : zoos) {
			response.add(
					new ZooServiceModel(
							zoo.getId(),
							zoo.getNombre(), 
							zoo.getPvpEntrada(),
							zoo.getWeb(),
							zoo.getInformacion(),
							zoo.getLatitud(),
							zoo.getLongitud(),
							zoo.getCiudad(),
							zoo.getPais(),
							null));
		}		return response;
	}

	@Override
	public ZooServiceModel getZoosById(Integer id) {

		Zoo zoo = zooRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Zoo no encontrado"));
		
		//List<Evento> eventos = zoo.getEventos();
	
		ZooServiceModel response = new ZooServiceModel(
				zoo.getId(),
				zoo.getNombre(), 
				zoo.getPvpEntrada(),
				zoo.getWeb(),
				zoo.getInformacion(),
				zoo.getLatitud(),
				zoo.getLongitud(),
				zoo.getCiudad(),
				zoo.getPais(),
				null);
	
		return response;
	}

	@Override
	public ZooServiceModel create(ZooPostRequest zooPostRequest) {

		Zoo zoo = new Zoo(
				zooPostRequest.getNombre(), 
				zooPostRequest.getPvpEntrada(),
				zooPostRequest.getWeb(),
				zooPostRequest.getInformacion(),
				zooPostRequest.getLatitud(),
				zooPostRequest.getLongitud(),
				zooPostRequest.getCiudad(),
				zooPostRequest.getPais());

		zoo = zooRepository.save(zoo);
		
		ZooServiceModel zooResponse = new ZooServiceModel(
				zoo.getId(),
				zoo.getNombre(), 
				zoo.getPvpEntrada(),
				zoo.getWeb(),
				zoo.getInformacion(),
				zoo.getLatitud(),
				zoo.getLongitud(),
				zoo.getCiudad(),
				zoo.getPais(),
				null); //trampeado, evento.getZooId**
		
		return zooResponse;
	}

	@Override
	public ZooServiceModel updateById(Integer id, ZooPostRequest zooPostRequest) {

		//SI SE MODIFICA UN ID QUE NO EXISTE, PROVOCAMOS ESTE ERROR
				Zoo zoo = zooRepository.findById(id).orElseThrow(
						() -> new ResponseStatusException(HttpStatus.CONFLICT, "Zoo no encontrado")
				);
				
				if(zooPostRequest.getNombre()!=null && zooPostRequest.getNombre()!= "") {
					zoo.setNombre(zooPostRequest.getNombre());
				}	
				if(zooPostRequest.getPvpEntrada()!=0 ) {
					zoo.setPvpEntrada(zooPostRequest.getPvpEntrada());
				}
				if(zooPostRequest.getWeb()!=null && zooPostRequest.getWeb()!= "") {
					zoo.setWeb(zooPostRequest.getWeb());
				}
				if(zooPostRequest.getInformacion()!=null && zooPostRequest.getInformacion()!= "") {
					zoo.setInformacion(zooPostRequest.getInformacion());
				}
				if(zooPostRequest.getLatitud()!=0 ) {
					zoo.setLatitud(zooPostRequest.getLatitud());
				}
				if(zooPostRequest.getLongitud()!=0 ) {
					zoo.setLongitud(zooPostRequest.getLongitud());
				}
				if(zooPostRequest.getCiudad()!=null && zooPostRequest.getCiudad()!= "") {
					zoo.setCiudad(zooPostRequest.getCiudad());
				}
				if(zooPostRequest.getPais()!=null && zooPostRequest.getPais()!= "") {
					zoo.setPais(zooPostRequest.getPais());
				}

				 zoo = zooRepository.save(zoo);
				 			
				 ZooServiceModel  zooResponse = new ZooServiceModel(
						 zoo.getId(),
							zoo.getNombre(), 
							zoo.getPvpEntrada(),
							zoo.getWeb(),
							zoo.getInformacion(),
							zoo.getLatitud(),
							zoo.getLongitud(),
							zoo.getCiudad(),
							zoo.getPais(),
							null);  //trampeado, evento.getZooId**
		
		return zooResponse;
	}

}

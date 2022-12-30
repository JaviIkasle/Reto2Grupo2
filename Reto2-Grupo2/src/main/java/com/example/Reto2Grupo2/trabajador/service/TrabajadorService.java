package com.example.Reto2Grupo2.trabajador.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.Reto2Grupo2.trabajador.modelo.Trabajador;
import com.example.Reto2Grupo2.trabajador.modelo.TrabajadorExpands;
import com.example.Reto2Grupo2.trabajador.modelo.TrabajadorPostRequest;
import com.example.Reto2Grupo2.trabajador.modelo.TrabajadorServiceModel;
import com.example.Reto2Grupo2.trabajador.repository.TrabajadorRepository;
import com.example.Reto2Grupo2.zoo.modelo.Zoo;
import com.example.Reto2Grupo2.zoo.modelo.ZooServiceModel;
import com.example.Reto2Grupo2.zoo.repository.ZooRepository;


@Service
public class TrabajadorService implements TrabajadorServiceImpl{

	@Autowired
	private TrabajadorRepository trabajadorRepository;
	//TODO podríamos llamar al servicio del Zoo y de ahí al repositorio
	@Autowired
	private ZooRepository zooRepository;
	
	@Override
	public List<TrabajadorServiceModel> getTrabajadores() {
		Iterable<Trabajador> trabajadores = trabajadorRepository.findAll();
		List<TrabajadorServiceModel> response = new ArrayList<TrabajadorServiceModel>();
		for (Trabajador trabajador : trabajadores) {
			response.add(
					new TrabajadorServiceModel(
							trabajador.getId(),
							trabajador.getUsername(),
							trabajador.getPassword(),
							null,
							trabajador.getZooId()));
		}		return response;
	}

	@Override
	public TrabajadorServiceModel getTrabajadorById(Integer id, List<TrabajadorExpands> expand) {
		
		Trabajador trabajador  = trabajadorRepository.findById(id).orElseThrow(

				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Trabajador no encontrado"));
		
		ZooServiceModel zooResponse = null;
		
		if (expand != null&& expand.indexOf(TrabajadorExpands.ZOO) != -1) {
			
			Zoo zooBD = trabajador.getZoo();		
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
					null,
					null);		
		}	
 		
		TrabajadorServiceModel response = new TrabajadorServiceModel(
				trabajador.getId(),
				trabajador.getUsername(),
				trabajador.getPassword(),
				zooResponse,
				trabajador.getZooId());
		
		return response;
	}

	@Override
	public TrabajadorServiceModel create(TrabajadorPostRequest trabajadorPostRequest) {
		
		Zoo zoo = zooRepository.findById(trabajadorPostRequest.getZooId()).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Zoo no encontrado"));
		
		
		Trabajador trabajador = new Trabajador(
				null,
				trabajadorPostRequest.getUsername(), 
				trabajadorPostRequest.getPassword(),
				zoo,
				trabajadorPostRequest.getZooId()
				);

		trabajador = trabajadorRepository.save(trabajador);
		
		
		TrabajadorServiceModel trabajadorResponse = new TrabajadorServiceModel(
				trabajador.getId(),
				trabajador.getUsername(),
				trabajador.getPassword(),
				null,
				zoo.getId()); //trampeado, evento.getZooId**Si no sale null
		 return trabajadorResponse;			
	}

	@Override
	public TrabajadorServiceModel updateById(Integer id, TrabajadorPostRequest trabajadorPostRequest) {
		
		//SI SE MODIFICA UN registro QUE NO EXISTE, PROVOCAMOS ESTE ERROR
		Trabajador trabajador = trabajadorRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.CONFLICT, "Trabajador no encontrado")
		);
		
		if(trabajadorPostRequest.getUsername()!=null && trabajadorPostRequest.getUsername()!= "") {
			trabajador.setUsername(trabajadorPostRequest.getUsername());
		}	
		if(trabajadorPostRequest.getPassword()!=null && trabajadorPostRequest.getPassword()!= "") {
			trabajador.setPassword(trabajadorPostRequest.getPassword());
		}

		trabajador = trabajadorRepository.save(trabajador);
				
		Zoo zoo = zooRepository.findById(trabajadorPostRequest.getZooId()).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Zoo no encontrado"));
		
		TrabajadorServiceModel trabajadorResponse = new TrabajadorServiceModel(
				trabajador.getId(),
				trabajador.getUsername(),
				trabajador.getPassword(),
				null,
				zoo.getId()); //Trampeado, evento.getZooId**, lo trae null......
		return trabajadorResponse;
				
	}
	
//	@Override
//	public Integer deleteById(Integer id) {	
//		Integer respuesta =0;
//		try {
//			trabajadorRepository.deleteById(id);
//			respuesta = 1;
//		} catch (EmptyResultDataAccessException e) {
//			respuesta = 2;
//		}
//		return 	respuesta;
//	}

}

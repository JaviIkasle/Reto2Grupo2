package com.example.Reto2Grupo2.especie.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.Reto2Grupo2.especie.modelo.Especie;
import com.example.Reto2Grupo2.especie.modelo.EspeciePostRequest;
import com.example.Reto2Grupo2.especie.modelo.EspecieServiceModel;
import com.example.Reto2Grupo2.especie.repository.EspecieRepository;

@Service
public class EspecieService implements EspecieServiceImpl{

	@Autowired
	private EspecieRepository especieRepository;

	@Override
	public List<EspecieServiceModel> getAllEspecies() {
		Iterable<Especie> especies = especieRepository.findAll();
		List<EspecieServiceModel> response = new ArrayList<EspecieServiceModel>();
		
		for (Especie especie : especies) {
			response.add(new EspecieServiceModel(
					especie.getId(),
					especie.getInformacion(),
					especie.getNombre(),
					null)
			);
		}
		return response;
	}

	@Override
	public EspecieServiceModel getEspecieById(Integer id) {
		
		Especie especie= especieRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Especie no encontrada"));
		
		EspecieServiceModel response = new EspecieServiceModel(
				especie.getId(),
				especie.getInformacion(),
				especie.getNombre(),
				null
		);
		
		return response;
	}

	@Override
	public EspecieServiceModel create(EspeciePostRequest especiePostRequest) {
		
		Especie especie = new Especie(
				null,
				especiePostRequest.getInformacion(),
				especiePostRequest.getNombre(),
				null
		);
		
		especie = especieRepository.save(especie);
		
		EspecieServiceModel response = new EspecieServiceModel(
				especie.getId(),
				especie.getInformacion(),
				especie.getNombre(),
				null
		);
		return response;
	}

	@Override
	public EspecieServiceModel updateById(Integer id, EspeciePostRequest especiePostRequest) {
		
		//SI SE MODIFICA UN ID QUE NO EXISTE, POROVOCAMOS ESTE ERROR
		Especie especie = especieRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.CONFLICT, "Especie no encontrada")
		);
		
		if (especiePostRequest.getInformacion() != null && especiePostRequest.getInformacion() != "") {
			especie.setInformacion(especiePostRequest.getInformacion());
		}
		if (especiePostRequest.getNombre() != null && especiePostRequest.getNombre() != "") {
			especie.setNombre(especiePostRequest.getNombre());
		}
		
		especie = especieRepository.save(especie);
		
		EspecieServiceModel response = new EspecieServiceModel(
				especie.getId(),
				especie.getInformacion(),
				especie.getNombre(),
				null
		);
		
		return response;
	}

	@Override
	public ResponseEntity<EspecieServiceModel> deleteById(Integer id) {
		
		try {
			especieRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (EmptyResultDataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Id de Especie no encontrada");
		}
	}
}

package com.example.Reto2Grupo2.animal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.Reto2Grupo2.animal.modelo.Animal;
import com.example.Reto2Grupo2.animal.modelo.AnimalPostRequest;
import com.example.Reto2Grupo2.animal.modelo.AnimalServiceModel;
import com.example.Reto2Grupo2.animal.repository.AnimalRepository;
import com.example.Reto2Grupo2.especie.modelo.Especie;
import com.example.Reto2Grupo2.especie.modelo.EspecieServiceModel;
import com.example.Reto2Grupo2.especie.repository.EspecieRepository;

@Service
public class AnimalService implements AnimalServiceImpl{

	@Autowired
	private AnimalRepository animalRepository;
	@Autowired
	private EspecieRepository especieRepository;
	
	@Override
	public List<AnimalServiceModel> getAllAnimales() {
		Iterable<Animal> animales = animalRepository.findAll();
		List<AnimalServiceModel> response = new ArrayList<AnimalServiceModel>();
		
		for (Animal animal : animales) {
			response.add(new AnimalServiceModel(
					animal.getId(),
					animal.getNombre(),
					animal.getInformacion(),
					animal.getFoto(),
					null,
					animal.getEspecieId(),
					null)
			);
		}
		return response;
	}

	@Override
	public AnimalServiceModel getAnimalById(Integer id) {

		Animal animal = animalRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Animal no encontrado"));
		
		AnimalServiceModel response = new AnimalServiceModel(
				animal.getId(),
				animal.getNombre(),
				animal.getInformacion(),
				animal.getFoto(),
				null,
				animal.getEspecieId(),
				null
		);
		
		return response;
	}

	@Override
	public AnimalServiceModel create(AnimalPostRequest animalPostRequest) {

		Especie especie= especieRepository.findById(animalPostRequest.getEspecieId()).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Especie no encontrada"));
		
		Animal animal = new Animal(
				null,
				animalPostRequest.getNombre(),
				animalPostRequest.getInformacion(),
				animalPostRequest.getFoto(),
				especie,
				animalPostRequest.getEspecieId(),
				null
		);
		
		animal = animalRepository.save(animal);
		
		AnimalServiceModel response = new AnimalServiceModel(
				animal.getId(),
				animal.getNombre(),
				animal.getInformacion(),
				animal.getFoto(),
				null,
				animal.getEspecieId(),
				null
		);
		return response;
	}

	@Override
	public AnimalServiceModel updateById(Integer id, AnimalPostRequest animalPostRequest) {
		
		Especie especie= especieRepository.findById(animalPostRequest.getEspecieId()).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Especie no encontrada"));
		//SI SE MODIFICA UN ID QUE NO EXISTE, POROVOCAMOS ESTE ERROR
		Animal animal = animalRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.CONFLICT, "Animal no encontrado")
		);
		
		if (animalPostRequest.getNombre() != null && animalPostRequest.getNombre() != "") {
			animal.setNombre(animalPostRequest.getNombre());
		}
		if (animalPostRequest.getInformacion() != null && animalPostRequest.getInformacion() != "") {
			animal.setInformacion(animalPostRequest.getInformacion());
		}
		if (animalPostRequest.getFoto() != null & !animalPostRequest.getFoto().equals("")) {
			animal.setFoto(animalPostRequest.getFoto());
		}
		if (animalPostRequest.getEspecieId() != null && animalPostRequest.getEspecieId() > 0) {
			animal.setEspecie(especie);
			animal.setEspecieId(especie.getId());
		}

		animal = animalRepository.save(animal);
		
		AnimalServiceModel response = new AnimalServiceModel(
				animal.getId(),
				animal.getNombre(),
				animal.getInformacion(),
				animal.getFoto(),
				null,
				animal.getEspecieId(),
				null	
		);
		
		return response;
	}

	@Override
	public ResponseEntity<AnimalServiceModel> deleteById(Integer id) {
		
		try {
			animalRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch (EmptyResultDataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Id de Animal no encontrada");
		}
		
	}

	@Override
	public List<AnimalServiceModel> getZooAnimals(Integer id) {
		// TODO Auto-generated method stub
		Iterable<Animal> animales = animalRepository.findAnimalByZoosId(id);

		List<AnimalServiceModel> response = new ArrayList<AnimalServiceModel>();
		EspecieServiceModel especieSacada;
		
		for (Animal animal : animales) {
			Especie especie= especieRepository.findById(animal.getEspecieId()).orElseThrow(
					() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Especie no encontrada"));
			
			
			especieSacada = new EspecieServiceModel(
					especie.getId(),
					especie.getInformacion(),
					especie.getNombre(),
					null
			);
				
			response.add(new AnimalServiceModel(
					animal.getId(),
					animal.getNombre(),
					animal.getInformacion(),
					animal.getFoto(),
					especieSacada,
					animal.getEspecieId(),
					null)
			);
		}
		return response;
	}
}

package com.example.Reto2Grupo2.animal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.Reto2Grupo2.animal.modelo.AnimalPostRequest;
import com.example.Reto2Grupo2.animal.modelo.AnimalServiceModel;
import com.example.Reto2Grupo2.animal.service.AnimalServiceImpl;

@RestController
@RequestMapping("api")
public class AnimalController {

	@Autowired
	private AnimalServiceImpl animalService;
	
	@GetMapping("/animales")
	public ResponseEntity<List<AnimalServiceModel>> getAnimales() {
		
		List<AnimalServiceModel> response = animalService.getAllAnimales();
		return new ResponseEntity<List<AnimalServiceModel>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/animales/{id}")
	public ResponseEntity<AnimalServiceModel> getAnimalById(@PathVariable("id") Integer id) {
		
		AnimalServiceModel response = animalService.getAnimalById(id);
		return new ResponseEntity<AnimalServiceModel>(response, HttpStatus.OK);
	}
	
	@PostMapping("/animales")
	public ResponseEntity<AnimalServiceModel> createAnimal(@RequestBody AnimalPostRequest animalPostRequest) {
		
		AnimalServiceModel animalResponse = animalService.create(animalPostRequest);
		
		return new ResponseEntity<AnimalServiceModel>(animalResponse, HttpStatus.CREATED);
	}
	
	@PutMapping("/animales/{id}")
	public ResponseEntity<AnimalServiceModel> updateAnimal(@PathVariable("id") Integer id, @RequestBody AnimalPostRequest animalPostRequet) {
		
		AnimalServiceModel response = animalService.updateById(id, animalPostRequet);
		
		return new ResponseEntity<AnimalServiceModel>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/animales/{id}")
	public void deleteAnimalById(@PathVariable("id") Integer id) {
		animalService.deleteById(id);
	}
}

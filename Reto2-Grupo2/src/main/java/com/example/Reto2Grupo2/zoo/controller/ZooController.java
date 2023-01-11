package com.example.Reto2Grupo2.zoo.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.example.Reto2Grupo2.zoo.modelo.ZooPostRequest;
import com.example.Reto2Grupo2.zoo.modelo.ZooServiceModel;
import com.example.Reto2Grupo2.zoo.repository.ZooRepository;
import com.example.Reto2Grupo2.zoo.service.ZooServiceImpl;

@RestController
@RequestMapping("api")
public class ZooController {

	@Autowired
	private ZooRepository zooRepository;
	@Autowired
	private ZooServiceImpl zooService;
	
	@GetMapping("/zoos")
	public ResponseEntity<List<ZooServiceModel>> getZoos() {
		
		List<ZooServiceModel> response =zooService.getZoos();
		return new ResponseEntity<List<ZooServiceModel>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/zoos/{id}")
	public ResponseEntity<ZooServiceModel> getZooById(@PathVariable("id") Integer id) {
		
		ZooServiceModel response=	zooService.getZoosById(id);	
		return new ResponseEntity<ZooServiceModel>(response, HttpStatus.OK);
	}
	
	@PostMapping("/zoos")
	public ResponseEntity<ZooServiceModel> createZoo(@RequestBody ZooPostRequest zooPostRequest) {
		
		ZooServiceModel zooResponse = 	zooService.create(zooPostRequest); 							
		return new ResponseEntity<ZooServiceModel>(zooResponse, HttpStatus.CREATED);
	}
	
	@PutMapping("/zoos/{id}")
	public ResponseEntity<ZooServiceModel> updateEvento(
			@PathVariable("id") Integer id,
			@RequestBody ZooPostRequest zooPostRequest) {
		
		 ZooServiceModel  zooResponse =	zooService.updateById(id, zooPostRequest);	 
		 return new ResponseEntity<ZooServiceModel>(zooResponse,HttpStatus.OK);
	}
	
	@DeleteMapping("/zoos/{id}")
	public ResponseEntity<Integer> deleteZooById(@PathVariable("id") Integer id) {

		try {
			zooRepository.deleteById(id);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} catch (EmptyResultDataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Zoo no encontrado");
		}
	}
	
}

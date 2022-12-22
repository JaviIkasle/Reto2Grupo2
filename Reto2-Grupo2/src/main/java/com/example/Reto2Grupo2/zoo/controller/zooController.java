package com.example.Reto2Grupo2.zoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.Reto2Grupo2.zoo.modelo.Zoo;
import com.example.Reto2Grupo2.zoo.repository.zooRepository;

@RestController
@RequestMapping("api")
public class zooController {

	@Autowired
	private zooRepository zooRepository;
	
	@GetMapping("/zoo")
	public ResponseEntity<Iterable<Zoo>>getAllZoos(){
		return new ResponseEntity<Iterable<Zoo>>(zooRepository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/zoo/{id}")
	public ResponseEntity<Zoo>getZoById(@PathVariable("id")Integer id){
		
		Zoo zoo = zooRepository.findById(id).orElseThrow(
				()-> new ResponseStatusException(HttpStatus.NO_CONTENT, "No existe el zoo")
				);
		return new ResponseEntity<Zoo>(zoo, HttpStatus.OK);
	}
	
}

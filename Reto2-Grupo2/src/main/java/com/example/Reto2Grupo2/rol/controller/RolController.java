package com.example.Reto2Grupo2.rol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Reto2Grupo2.rol.modelo.RolServiceModel;
import com.example.Reto2Grupo2.rol.service.RolServiceImpl;
import com.example.Reto2Grupo2.zoo.repository.ZooRepository;

@RestController
@RequestMapping("api")
public class RolController {
	
	@Autowired
	private ZooRepository zooRepository;
	@Autowired
	RolServiceImpl rolService;
	
	@GetMapping("/roles")
	public ResponseEntity<List<RolServiceModel>> getZoos() {
		
		List<RolServiceModel> response =rolService.getRoles();
		return new ResponseEntity<List<RolServiceModel>>(response, HttpStatus.OK);
	}
}

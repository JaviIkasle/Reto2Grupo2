package com.example.Reto2Grupo2.trabajador.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.Reto2Grupo2.trabajador.modelo.TrabajadorServiceModel;
import com.example.Reto2Grupo2.trabajador.service.TrabajadorServiceImpl;

@RestController
@RequestMapping("api")
public class TrabajadorController {

	@Autowired
	TrabajadorServiceImpl trabajadorService;
	

	@GetMapping("/trabajadores")
	public ResponseEntity<List<TrabajadorServiceModel>> getTrabajadores() {
		List<TrabajadorServiceModel> response = trabajadorService.getTrabajadores();
		return new ResponseEntity<List<TrabajadorServiceModel>>(response, HttpStatus.OK);
	}
}

package com.example.Reto2Grupo2.trabajador.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.Reto2Grupo2.trabajador.modelo.TrabajadorExpands;
import com.example.Reto2Grupo2.trabajador.modelo.TrabajadorPostRequest;
import com.example.Reto2Grupo2.trabajador.modelo.TrabajadorServiceModel;
import com.example.Reto2Grupo2.trabajador.repository.TrabajadorRepository;
import com.example.Reto2Grupo2.trabajador.service.TrabajadorService;

@RestController
@RequestMapping("api")
public class TrabajadorController {

	@Autowired
	private TrabajadorService trabajadorService;
	
	@Autowired
	private TrabajadorRepository trabajadorRepository;
	
	@GetMapping("/trabajadores")
	public ResponseEntity<List<TrabajadorServiceModel>> getTrabajadores() {
		List<TrabajadorServiceModel> response = trabajadorService.getTrabajadores();
		return new ResponseEntity<List<TrabajadorServiceModel>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/trabajadores/{id}")
	public ResponseEntity<TrabajadorServiceModel> getTrabajadorById(@PathVariable("id") Integer id,
			@RequestParam(required = false) List<TrabajadorExpands> expand) {

		TrabajadorServiceModel response = trabajadorService.getTrabajadorById(id, expand);
		return new ResponseEntity<TrabajadorServiceModel>(response, HttpStatus.OK);
	}
	
	@PostMapping("/trabajadores")
	public ResponseEntity<TrabajadorServiceModel> createTrabajador(@RequestBody TrabajadorPostRequest trabajadorPostRequest) {

		TrabajadorServiceModel trabajadorResponse = trabajadorService.create(trabajadorPostRequest);
		return new ResponseEntity<TrabajadorServiceModel>(trabajadorResponse, HttpStatus.CREATED);
	}
	
	@PutMapping("/trabajadores/{id}")
	public ResponseEntity<TrabajadorServiceModel> updateTrabajador(@PathVariable("id") Integer id,
			@RequestBody TrabajadorPostRequest trabajadorPostRequest) {

		TrabajadorServiceModel trabajadorResponse = trabajadorService.updateById(id, trabajadorPostRequest);
		return new ResponseEntity<TrabajadorServiceModel>(trabajadorResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/trabajadores/{id}")
	public ResponseEntity<Integer> deleteTrabajadorById(@PathVariable("id") Integer id) {

		try {
			trabajadorRepository.deleteById(id);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} catch (EmptyResultDataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Trabajador no encontrado");
		}
	}
	
	
}

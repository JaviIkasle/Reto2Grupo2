package com.example.Reto2Grupo2.user.controller;

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

import com.example.Reto2Grupo2.user.modelo.UserExpands;
import com.example.Reto2Grupo2.user.modelo.UserPostRequest;
import com.example.Reto2Grupo2.user.modelo.UserServiceModel;
import com.example.Reto2Grupo2.user.repository.UserRepository;
import com.example.Reto2Grupo2.user.service.UserService;

@RestController
@RequestMapping("api")
public class UserController {

	@Autowired
	private UserService trabajadorService;
	
	@Autowired
	private UserRepository trabajadorRepository;
	
	@GetMapping("/users")
	public ResponseEntity<List<UserServiceModel>> getTrabajadores() {
		List<UserServiceModel> response = trabajadorService.getTrabajadores();
		return new ResponseEntity<List<UserServiceModel>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<UserServiceModel> getTrabajadorById(@PathVariable("id") Integer id,
			@RequestParam(required = false) List<UserExpands> expand) {

		UserServiceModel response = trabajadorService.getTrabajadorById(id, expand);
		return new ResponseEntity<UserServiceModel>(response, HttpStatus.OK);
	}
	
	@PostMapping("/users")
	public ResponseEntity<UserServiceModel> createTrabajador(@RequestBody UserPostRequest trabajadorPostRequest) {

		UserServiceModel trabajadorResponse = trabajadorService.create(trabajadorPostRequest);
		return new ResponseEntity<UserServiceModel>(trabajadorResponse, HttpStatus.CREATED);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<UserServiceModel> updateTrabajador(@PathVariable("id") Integer id,
			@RequestBody UserPostRequest trabajadorPostRequest) {

		UserServiceModel trabajadorResponse = trabajadorService.updateById(id, trabajadorPostRequest);
		return new ResponseEntity<UserServiceModel>(trabajadorResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Integer> deleteTrabajadorById(@PathVariable("id") Integer id) {

		try {
			trabajadorRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (EmptyResultDataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Trabajador no encontrado");
		}
	}
	
	//TODO update pass
	@PutMapping("/users/updatePassCliente")
	public ResponseEntity<UserServiceModel> updateUserLog(@PathVariable("id") Integer id,
			@RequestBody UserPostRequest trabajadorPostRequest) {

		UserServiceModel trabajadorResponse = trabajadorService.updateById(id, trabajadorPostRequest);
		return new ResponseEntity<UserServiceModel>(trabajadorResponse, HttpStatus.OK);
	}
	
	
}

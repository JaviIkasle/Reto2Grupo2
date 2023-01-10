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
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/users")
	public ResponseEntity<List<UserServiceModel>> getUser() {
		List<UserServiceModel> response = userService.getTrabajadores();
		return new ResponseEntity<List<UserServiceModel>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<UserServiceModel> getUserById(@PathVariable("id") Integer id,
			@RequestParam(required = false) List<UserExpands> expand) {

		UserServiceModel response = userService.getTrabajadorById(id, expand);
		return new ResponseEntity<UserServiceModel>(response, HttpStatus.OK);
	}
	
	@PostMapping("/users")
	public ResponseEntity<UserServiceModel> createUser(@RequestBody UserPostRequest userPostRequest) {

		UserServiceModel userResponse = userService.create(userPostRequest);
		return new ResponseEntity<UserServiceModel>(userResponse, HttpStatus.CREATED);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<UserServiceModel> updateUser(@PathVariable("id") Integer id,
			@RequestBody UserPostRequest userPostRequest) {

		UserServiceModel userResponse = userService.updateById(id, userPostRequest);
		return new ResponseEntity<UserServiceModel>(userResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Integer> deleteUserById(@PathVariable("id") Integer id) {

		try {
			userRepository.deleteById(id);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} catch (EmptyResultDataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "User no encontrado");
		}
	}
	
	
}

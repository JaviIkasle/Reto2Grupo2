package com.example.Reto2Grupo2.user.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.Reto2Grupo2.auth.exception.UserCantCreateException;
import com.example.Reto2Grupo2.user.modelo.AuthRequestAdmin;
import com.example.Reto2Grupo2.user.modelo.AuthRequestCliente;
import com.example.Reto2Grupo2.user.modelo.AuthRequestEmple;
import com.example.Reto2Grupo2.user.modelo.ClienteUpdateAndroid;
import com.example.Reto2Grupo2.user.modelo.ClienteUpdateByAdminRequest;
import com.example.Reto2Grupo2.user.modelo.ClienteUpdateRequest;
import com.example.Reto2Grupo2.user.modelo.EmpleUpdateByAdminRequest;
import com.example.Reto2Grupo2.user.modelo.User;
import com.example.Reto2Grupo2.user.modelo.UserExpands;
import com.example.Reto2Grupo2.user.modelo.UserServiceModel;
import com.example.Reto2Grupo2.user.service.UserService;

import jakarta.validation.Valid;
@CrossOrigin
@RestController
@RequestMapping("api")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public ResponseEntity<List<UserServiceModel>> getUsers() {
		List<UserServiceModel> response = userService.getUsers();
		return new ResponseEntity<List<UserServiceModel>>(response, HttpStatus.OK);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<UserServiceModel> getUserById(@PathVariable("id") Integer id,
			@RequestParam(required = false) List<UserExpands> expand) {
		UserServiceModel response = userService.getUserById(id, expand);
		return new ResponseEntity<UserServiceModel>(response, HttpStatus.OK);
	}

	@PostMapping("/auth/signup/empleados")
	public ResponseEntity<?> signupEmpleado(@Valid @RequestBody AuthRequestEmple requestEmple) {
	
		try {
			userService.signupEmpleado(requestEmple);
		} catch (UserCantCreateException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PostMapping("/auth/signup/clientes")
	public ResponseEntity<?> signupCliente(@Valid @RequestBody AuthRequestCliente request) {

		try {
			userService.signupCliente(request);
		} catch (UserCantCreateException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PostMapping("/auth/signup/admin")
	public ResponseEntity<?> signupAdmin(@RequestBody AuthRequestAdmin request) {
		
		try {
			userService.signUpAdmin(request);
		} catch (UserCantCreateException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/users/empleados/{id}")
	public ResponseEntity<UserServiceModel> updateEmpleByAdmin(@PathVariable("id") Integer id,
			@Valid @RequestBody EmpleUpdateByAdminRequest userPostRequest) {

		UserServiceModel userResponse = userService.updateEmpleByAdmin(id, userPostRequest);
		return new ResponseEntity<UserServiceModel>(userResponse, HttpStatus.OK);
	}
	@PutMapping("/users/clientes/{id}")
	public ResponseEntity<UserServiceModel> updateClienteByAdmin(@PathVariable("id") Integer id,
			@Valid @RequestBody ClienteUpdateByAdminRequest clienteUpdateByAdmin) {

		UserServiceModel userResponse = userService.updateClienteByAdmin(id, clienteUpdateByAdmin);
		return new ResponseEntity<UserServiceModel>(userResponse, HttpStatus.OK);
	}

	@PutMapping("/users/cliente")
	public ResponseEntity<UserServiceModel> updateCliente(@Valid @RequestBody ClienteUpdateRequest clienteUpdateRequest,
			Authentication authentication) {

		
		User userDetails = (User) authentication.getPrincipal();
	
		UserServiceModel userResponse = userService.updateCliente(clienteUpdateRequest, userDetails.getId());
		return new ResponseEntity<UserServiceModel>(userResponse, HttpStatus.OK);
	}

	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable("id") Integer id) {
		userService.deleteById(id);

	}
	
	@DeleteMapping("/users/cliente")
	public void deleteCliente(Authentication authentication) {

		User userDetails = (User) authentication.getPrincipal();
		userService.deleteCliente(userDetails.getId());
	}
	
	/*
	 * 
	 * PARA ANDROID Y PSP
	 * 
	 *
	 * 
	 */
	
	@PutMapping("/users/cliente/android")
	public ResponseEntity<UserServiceModel> updateClienteAndroid(@Valid @RequestBody ClienteUpdateAndroid clienteUpdateRequest,
			Authentication authentication) {
		
		User userDetails = (User) authentication.getPrincipal();
	

		UserServiceModel userResponse = userService.updateClienteAndroid(clienteUpdateRequest, userDetails.getId());
		return new ResponseEntity<UserServiceModel>(userResponse, HttpStatus.OK);
	}
	
	@PostMapping("/auth/signup/clientes/android")
	public ResponseEntity<?> signupClienteAndroid(@Valid @RequestBody AuthRequestCliente request) {

		try {
			userService.signupClienteAndroid(request);
		} catch (UserCantCreateException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/users/clientePass/{email}")
	public ResponseEntity<UserServiceModel> updateClientePassword(@PathVariable("email") String email) {
		
		UserServiceModel userResponse = userService.generateClientePassword(email);
		return new ResponseEntity<UserServiceModel>(userResponse, HttpStatus.OK);
	}

}

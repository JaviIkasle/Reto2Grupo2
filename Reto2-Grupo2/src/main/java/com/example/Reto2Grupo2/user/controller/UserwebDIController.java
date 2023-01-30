package com.example.Reto2Grupo2.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




import com.example.Reto2Grupo2.user.modelo.UserExpands;

import com.example.Reto2Grupo2.user.modelo.UserServiceModel;

import com.example.Reto2Grupo2.user.service.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin
public class UserwebDIController {

	@Autowired
	private UserService userService;
	


	@GetMapping("/usersWEB")
	public ResponseEntity<List<UserServiceModel>> getTrabajadores() {
		List<UserServiceModel> response = userService.getUsers();
		return new ResponseEntity<List<UserServiceModel>>(response, HttpStatus.OK);
	}

	@GetMapping("/usersWEB/{id}")
	public ResponseEntity<UserServiceModel> getTrabajadorById(@PathVariable("id") Integer id,
			@RequestParam(required = false) List<UserExpands> expand) {

		UserServiceModel response = userService.getUserById(id, expand);
		return new ResponseEntity<UserServiceModel>(response, HttpStatus.OK);
	}


	
	
	

}

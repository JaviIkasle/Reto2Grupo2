package com.example.Reto2Grupo2.rol.controller;

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
import com.example.Reto2Grupo2.rol.modelo.RolPostRequest;
import com.example.Reto2Grupo2.rol.modelo.RolServiceModel;
import com.example.Reto2Grupo2.rol.service.RolService;

@RestController
@RequestMapping("api")
public class RolController {
	
	@Autowired
	RolService rolService;
	
	@GetMapping("/roles")
	public ResponseEntity<List<RolServiceModel>> getZoos() {
		
		List<RolServiceModel> response =rolService.getRoles();
		return new ResponseEntity<List<RolServiceModel>>(response, HttpStatus.OK);	
	}
	
	@GetMapping("/roles/{id}")
	public ResponseEntity<RolServiceModel> getRolById(@PathVariable("id") Integer id) {

		RolServiceModel response = rolService.getRolesById(id);
		return new ResponseEntity<RolServiceModel>(response, HttpStatus.OK);
	}
	
	@PostMapping("/roles")
	public ResponseEntity<RolServiceModel> createRol(@RequestBody RolPostRequest rolPostRequest ) {

		RolServiceModel rolResponse = rolService.create(rolPostRequest);
		return new ResponseEntity<RolServiceModel>(rolResponse, HttpStatus.CREATED);
	}
	
	@PutMapping("/roles/{id}")
	public ResponseEntity<RolServiceModel> updateRol(@PathVariable("id") Integer id,
			@RequestBody RolPostRequest rolPostRequest) {

		RolServiceModel rolResponse = rolService.updateById(id, rolPostRequest);
		return new ResponseEntity<RolServiceModel>(rolResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/roles/{id}")
	public void deleteRolById(@PathVariable("id") Integer id) {
		
		rolService.deleteById(id);

	}
}

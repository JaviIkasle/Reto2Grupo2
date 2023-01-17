package com.example.Reto2Grupo2.especie.controller;

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
import com.example.Reto2Grupo2.especie.modelo.EspeciePostRequest;
import com.example.Reto2Grupo2.especie.modelo.EspecieServiceModel;
import com.example.Reto2Grupo2.especie.service.EspecieService;

@RestController
@RequestMapping("api")
public class EspecieController {

	@Autowired
	private EspecieService especieService;
	
	
	@GetMapping("/especies")
	public ResponseEntity<List<EspecieServiceModel>> getEspecies() {
		
		List<EspecieServiceModel> response = especieService.getAllEspecies();
		return new ResponseEntity<List<EspecieServiceModel>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/especies/{id}")
	public ResponseEntity<EspecieServiceModel> getEspecieById(@PathVariable("id") Integer id) {
		
		EspecieServiceModel response = especieService.getEspecieById(id);
		return new ResponseEntity<EspecieServiceModel>(response, HttpStatus.OK);
	}
	
	@PostMapping("/especies")
	public ResponseEntity<EspecieServiceModel> createEspecie(@RequestBody EspeciePostRequest especiePostRequest) {
		
		EspecieServiceModel especieResponse = especieService.create(especiePostRequest);
		return new ResponseEntity<EspecieServiceModel>(especieResponse, HttpStatus.OK);
	}
	
	@PutMapping("/especies/{id}")
	public ResponseEntity<EspecieServiceModel> updateEspecie(@PathVariable("id") Integer id, @RequestBody EspeciePostRequest especiePostRequest) {
		
		EspecieServiceModel response = especieService.updateById(id, especiePostRequest);
		
		return new ResponseEntity<EspecieServiceModel>(response, HttpStatus.OK);		
	}
	
	@DeleteMapping("/especies/{id}")
	public void deleteEspecieById(@PathVariable("id") Integer id) {
		
		
		especieService.deleteById(id);
		
		
	}
}

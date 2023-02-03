package com.example.Reto2Grupo2.billete.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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


import com.example.Reto2Grupo2.billete.modelo.BilleteExpands;
import com.example.Reto2Grupo2.billete.modelo.BilletePostRequest;
import com.example.Reto2Grupo2.billete.modelo.BilleteServiceModel;
import com.example.Reto2Grupo2.billete.repository.BilleteRepository;
import com.example.Reto2Grupo2.billete.service.BilleteService;
import com.example.Reto2Grupo2.user.modelo.User;

@RestController
@RequestMapping("api")
public class BIleteController {

	@Autowired
	private BilleteRepository billeteRepository;

	@Autowired
	private BilleteService  billeteService;
	
	@GetMapping("/billetes")
	public ResponseEntity<List<BilleteServiceModel>> getEventos(Authentication authentication) {

		User userDetails = (User) authentication.getPrincipal();
		
		List<BilleteServiceModel> response = billeteService.getBilletes(userDetails.getId());
		return new ResponseEntity<List<BilleteServiceModel>>(response, HttpStatus.OK);
	}
	

	@GetMapping("/billetes/{id}")
	public ResponseEntity<BilleteServiceModel> getBilleteById(@PathVariable("id") Integer id,
			@RequestParam(required = false) List<BilleteExpands> expand) {

		BilleteServiceModel response = billeteService.getBilleteById(id, expand);
		return new ResponseEntity<BilleteServiceModel>(response, HttpStatus.OK);
	}

	@PostMapping("/billetes")
	public ResponseEntity<BilleteServiceModel> createBillete(@RequestBody BilletePostRequest billetePostRequest, Authentication authentication) {

		User userDetails = (User) authentication.getPrincipal();
		
		BilleteServiceModel billeteResponse = billeteService.create(billetePostRequest, userDetails.getId());
		
		
		
		return new ResponseEntity<BilleteServiceModel>(billeteResponse, HttpStatus.CREATED);
	}

	@PutMapping("/billetes/{id}")
	public ResponseEntity<BilleteServiceModel> updateBillete(@PathVariable("id") Integer id,
			@RequestBody BilletePostRequest billetePostRequest) {

		BilleteServiceModel billeteResponse = billeteService.updateById(id, billetePostRequest);
		return new ResponseEntity<BilleteServiceModel>(billeteResponse, HttpStatus.OK);
	}

	@DeleteMapping("/billetes/{id}")
	public ResponseEntity<Integer> deleteBilleteById(@PathVariable("id") Integer id) {

		try {
			billeteRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (EmptyResultDataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Billete no encontrado");
		}
	}
}

package com.example.Reto2Grupo2.billete.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import com.example.Reto2Grupo2.billete.modelo.Billete;
import com.example.Reto2Grupo2.billete.modelo.BilletePostRequest;
import com.example.Reto2Grupo2.billete.repository.BilleteRepository;

@RestController
@RequestMapping("api")
public class BIleteController {

	@Autowired
	private BilleteRepository billeteRepository;
	
	@GetMapping("/Billetes")
	public ResponseEntity<Iterable<Billete>> getBilletes() {
		return new ResponseEntity<Iterable<Billete>>(billeteRepository.findAll(), HttpStatus.OK);

	}
	

	@GetMapping("Billete/{id}")
	public ResponseEntity<Billete> getBilletebyid(@PathVariable("id") Integer id) {
		
		Billete billete = billeteRepository.findById(id).orElseThrow(
				
				() ->  new ResponseStatusException(HttpStatus.NO_CONTENT,"Billete no encontrado")
				
				);

		return new ResponseEntity<Billete>(billete, HttpStatus.OK);
	}
	
	
	@PostMapping("/BilleteCreate")
	public ResponseEntity<?> createBillete(@RequestBody BilletePostRequest billetePostRequest) {

		Billete billete = new Billete(

				billetePostRequest.getFecha(), billetePostRequest.getCantidad(), billetePostRequest.getImporte()

		);
		billete = billeteRepository.save(billete);
		return new ResponseEntity<Billete>(billete, HttpStatus.CREATED);

	}
	
	@PutMapping("/BilleteUpdate/{id}")
	public ResponseEntity<Billete> updateBillete(

			@PathVariable("id") Integer id,

			@RequestBody BilletePostRequest billetePostRequest) {


		Billete billete = billeteRepository.findById(id).orElseThrow(

				() -> new ResponseStatusException(HttpStatus.CONFLICT, "No existe el Billete"));

		if (billetePostRequest.getFecha() != null ) {
			billete.setFecha(billetePostRequest.getFecha());
		}
		if ( billetePostRequest.getCantidad() != 0) {
			billete.setCantidad(billetePostRequest.getCantidad());
		}
		if (billetePostRequest.getImporte() != 0) {
			billete.setImporte(billetePostRequest.getImporte());
		}
		
		billete = billeteRepository.save(billete);
		return new ResponseEntity<Billete>(billete, HttpStatus.OK);

	}

	@DeleteMapping("/BilleteDelete/{id}")
	public ResponseEntity<Billete> deleteBilleteById(@PathVariable("id") Integer id) {
		try {

			billeteRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		} catch (EmptyResultDataAccessException e) {

			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No existe ");
		}

	}
	
	
}

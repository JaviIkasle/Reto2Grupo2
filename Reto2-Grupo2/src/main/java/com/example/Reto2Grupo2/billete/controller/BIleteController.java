package com.example.Reto2Grupo2.billete.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.Ejercicio2A.department.Department;
import com.example.Ejercicio2A.department.DepartmentPostRequest;
import com.example.Reto2Grupo2.billete.modelo.Billete;
import com.example.Reto2Grupo2.billete.modelo.BilletePostRequest;
import com.example.Reto2Grupo2.billete.repository.BilleteRepository;

@RestController
@RequestMapping("api")
public class BIleteController {

	@Autowired
	private BilleteRepository billeteRepository;
	
	@GetMapping("/Billetes")
	public ResponseEntity<Iterable<Billete>> getDepartments() {
		return new ResponseEntity<Iterable<Billete>>(billeteRepository.findAll(), HttpStatus.OK);

	}
	
	
	@GetMapping("Billetes/{id}")
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
		return new ResponseEntity<Billete>(billete, HttpStatus.OK);

	}
	
	@PutMapping("/BilletesUpdate/{id}")
	public ResponseEntity<Billete> updateBillete(

			@PathVariable("id") Integer id,

			@RequestBody BilletePostRequest billetePostRequest) {

		Billete billete = billeteRepository.findById(id).orElseThrow(

				() -> new ResponseStatusException(HttpStatus.CONFLICT, "No existe el Billete"));

		if (billetePostRequest.getCantidad() != null && billetePostRequest.getCantidad() != "") {
			billete.setCantidad(billetePostRequest.getCantidad());
		}
		if (billetePostRequest.getFecha() != null && billetePostRequest.getFecha() != "") {
			billete.setFecha(billetePostRequest.getFecha());
		}
		if (billetePostRequest.getImporte() != null && billetePostRequest.getImporte() != "") {
			billete.setImporte(billetePostRequest.getImporte());
		}

		billete = billeteRepository.save(billete);

		return new ResponseEntity<Billete>(billete, HttpStatus.OK);

	}

	
	
	
}

package com.example.Reto2Grupo2.cliente.controller;

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


import com.example.Reto2Grupo2.cliente.modelo.Cliente;
import com.example.Reto2Grupo2.cliente.modelo.ClientePostRequest;
import com.example.Reto2Grupo2.cliente.repository.ClienteRepository;


@RestController
@RequestMapping("api")
public class ClienteController {
	
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("/Clientes")
	public ResponseEntity<Iterable<Cliente>> getClientes() {
		return new ResponseEntity<Iterable<Cliente>>(clienteRepository.findAll(), HttpStatus.OK);

	}
	

	@GetMapping("Cliente/{id}")
	public ResponseEntity<Cliente> getClientebyid(@PathVariable("id") Integer id) {
		
		Cliente cliente = clienteRepository.findById(id).orElseThrow(
				
				() ->  new ResponseStatusException(HttpStatus.NO_CONTENT,"Cliente no encontrado")
				
				);

		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
	
	
	@PostMapping("/ClienteCreate")
	public ResponseEntity<?> createCliente(@RequestBody ClientePostRequest clientePostRequest) {

		Cliente cliente = new Cliente(

			clientePostRequest.getEmail(),clientePostRequest.getPassword()

		);
		cliente = clienteRepository.save(cliente);
		return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);

	}
	
	@PutMapping("/ClienteUpdate/{id}")
	public ResponseEntity<Cliente> updateCliente(

			@PathVariable("id") Integer id,

			@RequestBody ClientePostRequest clientePostRequest) {


		Cliente cliente = clienteRepository.findById(id).orElseThrow(

				() -> new ResponseStatusException(HttpStatus.CONFLICT, "No existe el Cliente"));

		if (clientePostRequest.getEmail() != null ) {
			cliente.setEmail(clientePostRequest.getEmail());
		}
		if ( clientePostRequest.getPassword() != null) {
			cliente.setPassword(clientePostRequest.getPassword());
		}
		
		
		cliente = clienteRepository.save(cliente);
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);

	}

	@DeleteMapping("/ClienteDelete/{id}")
	public ResponseEntity<Cliente> deleteClienteById(@PathVariable("id") Integer id) {
		try {

			clienteRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		} catch (EmptyResultDataAccessException e) {

			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No existe ");
		}

	}
	

}

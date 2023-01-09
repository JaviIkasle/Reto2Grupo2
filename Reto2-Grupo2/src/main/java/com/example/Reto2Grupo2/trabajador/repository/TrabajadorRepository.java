package com.example.Reto2Grupo2.trabajador.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.Reto2Grupo2.trabajador.modelo.Trabajador;

public interface TrabajadorRepository extends CrudRepository<Trabajador, Integer>{

	Optional<Trabajador> findByUsername(String username);
	
}

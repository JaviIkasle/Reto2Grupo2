package com.example.Reto2Grupo2.rol.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.Reto2Grupo2.rol.modelo.Rol;

public interface RolRepository extends CrudRepository<Rol, Integer>{
	
	//TODO Optional <Rol> me da error
	Rol findByName (String rol);
	//Optional <Rol> findByName (String rol);
	
}

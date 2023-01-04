package com.example.Reto2Grupo2.rol.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.Reto2Grupo2.auth.persistence.Rol;

public interface RolRepository extends CrudRepository<Rol, Integer>{

	Optional <Rol> findByName (String rol);
	
}

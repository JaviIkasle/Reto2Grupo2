package com.example.Reto2Grupo2.rol.repository;


import org.springframework.data.repository.CrudRepository;
import com.example.Reto2Grupo2.rol.modelo.Rol;

public interface RolRepository extends CrudRepository<Rol, Integer>{

	Rol findByName (String rol);	
}

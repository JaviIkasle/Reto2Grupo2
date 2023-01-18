package com.example.Reto2Grupo2.animal.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.Reto2Grupo2.animal.modelo.Animal;


public interface AnimalRepository extends CrudRepository<Animal, Integer>, JpaRepository<Animal, Integer>{

	Iterable<Animal> findAnimalByZoosId(@Param("idZoo") Integer id);
	
}

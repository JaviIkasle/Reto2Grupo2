package com.example.Reto2Grupo2.animal.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.Reto2Grupo2.animal.modelo.AnimalPostRequest;
import com.example.Reto2Grupo2.animal.modelo.AnimalServiceModel;

import com.example.Reto2Grupo2.animal.repository.AnimalRepository;
import com.example.Reto2Grupo2.especie.modelo.Especie;
import com.example.Reto2Grupo2.especie.modelo.EspecieServiceModel;
import com.example.Reto2Grupo2.especie.repository.EspecieRepository;


public interface AnimalService {

	List<AnimalServiceModel> getAllAnimales();

	AnimalServiceModel getAnimalById(Integer id);

	AnimalServiceModel create(AnimalPostRequest animalPostRequest);

	AnimalServiceModel updateById(Integer id, AnimalPostRequest animalPostRequet);

	ResponseEntity<AnimalServiceModel> deleteById(Integer id);

	List<AnimalServiceModel> getZooAnimals(Integer id);
}

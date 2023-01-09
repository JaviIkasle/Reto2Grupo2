package com.example.Reto2Grupo2.especie.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.Reto2Grupo2.especie.modelo.EspeciePostRequest;
import com.example.Reto2Grupo2.especie.modelo.EspecieServiceModel;

public interface EspecieServiceImpl {

	List<EspecieServiceModel> getAllEspecies();

	EspecieServiceModel getEspecieById(Integer id);

	EspecieServiceModel create(EspeciePostRequest especiePostRequest);

	EspecieServiceModel updateById(Integer id, EspeciePostRequest especiePostRequest);

	ResponseEntity<EspecieServiceModel> deleteById(Integer id);

}

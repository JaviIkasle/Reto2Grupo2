package com.example.Reto2Grupo2.trabajador.service;

import java.util.List;

import com.example.Reto2Grupo2.auth.persistence.Trabajador;
import com.example.Reto2Grupo2.trabajador.modelo.TrabajadorExpands;
import com.example.Reto2Grupo2.trabajador.modelo.TrabajadorPostRequest;
import com.example.Reto2Grupo2.trabajador.modelo.TrabajadorServiceModel;

//TODO invertir nombres de las clases service
public interface TrabajadorServiceImpl {

	Trabajador signUp(Trabajador trabajador);
	
	List<TrabajadorServiceModel> getTrabajadores();

	TrabajadorServiceModel getTrabajadorById(Integer id, List<TrabajadorExpands> expand );

	TrabajadorServiceModel create( TrabajadorPostRequest trabajadorPostRequest);

	TrabajadorServiceModel updateById(Integer id, TrabajadorPostRequest trabajadorPostRequest);
	
}

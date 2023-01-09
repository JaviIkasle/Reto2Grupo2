package com.example.Reto2Grupo2.trabajador.service;

import java.util.List;

import com.example.Reto2Grupo2.auth.exception.UserCantCreateException;
import com.example.Reto2Grupo2.trabajador.modelo.Trabajador;
import com.example.Reto2Grupo2.trabajador.modelo.TrabajadorExpands;
import com.example.Reto2Grupo2.trabajador.modelo.TrabajadorPostRequest;
import com.example.Reto2Grupo2.trabajador.modelo.TrabajadorServiceModel;

//TODO invertir nombres de las clases service
public interface TrabajadorService {

	Trabajador signUp(Trabajador trabajador) throws UserCantCreateException;
	
	List<TrabajadorServiceModel> getTrabajadores();

	TrabajadorServiceModel getTrabajadorById(Integer id, List<TrabajadorExpands> expand );

	TrabajadorServiceModel create( TrabajadorPostRequest trabajadorPostRequest);

	TrabajadorServiceModel updateById(Integer id, TrabajadorPostRequest trabajadorPostRequest);
	
}

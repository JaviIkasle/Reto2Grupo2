package com.example.Reto2Grupo2.user.service;

import java.util.List;

import com.example.Reto2Grupo2.auth.exception.UserCantCreateException;
import com.example.Reto2Grupo2.user.modelo.User;
import com.example.Reto2Grupo2.user.modelo.UserExpands;
import com.example.Reto2Grupo2.user.modelo.UserPostRequest;
import com.example.Reto2Grupo2.user.modelo.UserServiceModel;

//TODO invertir nombres de las clases service
public interface UserService {

	User signupEmpleado(User trabajador) throws UserCantCreateException;
	
	User signupCliente(User cliente) throws UserCantCreateException;
	
	List<UserServiceModel> getTrabajadores();

	UserServiceModel getTrabajadorById(Integer id, List<UserExpands> expand );

	UserServiceModel create( UserPostRequest trabajadorPostRequest);

	UserServiceModel updateById(Integer id, UserPostRequest trabajadorPostRequest);
	
}

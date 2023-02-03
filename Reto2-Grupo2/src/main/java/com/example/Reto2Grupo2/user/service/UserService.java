package com.example.Reto2Grupo2.user.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.example.Reto2Grupo2.auth.exception.UserCantCreateException;
import com.example.Reto2Grupo2.user.modelo.AuthRequestAdmin;
import com.example.Reto2Grupo2.user.modelo.AuthRequestCliente;
import com.example.Reto2Grupo2.user.modelo.AuthRequestEmple;
import com.example.Reto2Grupo2.user.modelo.ClienteUpdateAndroid;
import com.example.Reto2Grupo2.user.modelo.ClienteUpdateByAdminRequest;
import com.example.Reto2Grupo2.user.modelo.ClienteUpdateRequest;
import com.example.Reto2Grupo2.user.modelo.EmpleUpdateByAdminRequest;
import com.example.Reto2Grupo2.user.modelo.User;
import com.example.Reto2Grupo2.user.modelo.UserExpands;
import com.example.Reto2Grupo2.user.modelo.UserServiceModel;



public interface UserService {

	User signupEmpleado(AuthRequestEmple empleado) throws UserCantCreateException;
	
	User signupCliente(AuthRequestCliente cliente) throws UserCantCreateException;
	
	List<UserServiceModel> getUsers();

	UserServiceModel getUserById(Integer id, List<UserExpands> expand );

	UserServiceModel updateEmpleByAdmin(Integer id, EmpleUpdateByAdminRequest userPostRequest);

	UserServiceModel updateCliente(ClienteUpdateRequest clienteUpdateRequest, Integer userId);

	ResponseEntity<UserServiceModel>  deleteById(Integer id);

	ResponseEntity<UserServiceModel> deleteCliente(Integer id);

	UserServiceModel updateClienteByAdmin(Integer id, ClienteUpdateByAdminRequest clienteUpdateByAdmin  );

	User signUpAdmin(AuthRequestAdmin request) throws UserCantCreateException;
	
	/*
	 * 							PARA ANDROID Y PSP
	 * 
	 */
	
	User signupClienteAndroid(AuthRequestCliente cliente) throws UserCantCreateException;
	
	UserServiceModel updateClienteAndroid(ClienteUpdateAndroid clienteUpdateAndroid, Integer userId);
	
	UserServiceModel generateClientePassword(String email);
		
}

package com.example.Reto2Grupo2.rol.service;

import java.util.List;

import com.example.Reto2Grupo2.rol.modelo.RolPostRequest;
import com.example.Reto2Grupo2.rol.modelo.RolServiceModel;


public interface RolServiceImpl {

	List<RolServiceModel> getRoles();

	RolServiceModel getRolesById(Integer id);

	RolServiceModel create( RolPostRequest rolPostRequest);

	RolServiceModel updateById(Integer id, RolPostRequest rolPostRequest);

	
}

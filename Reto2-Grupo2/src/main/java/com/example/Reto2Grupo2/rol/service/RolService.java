package com.example.Reto2Grupo2.rol.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Reto2Grupo2.auth.persistence.Rol;
import com.example.Reto2Grupo2.rol.modelo.RolPostRequest;
import com.example.Reto2Grupo2.rol.modelo.RolServiceModel;
import com.example.Reto2Grupo2.rol.repository.RolRepository;

@Service
public class RolService implements RolServiceImpl{

	@Autowired
	private RolRepository rolRepository;
	
	@Override
	public List<RolServiceModel> getRoles() {	
		Iterable<Rol> roles = rolRepository.findAll();
		List<RolServiceModel> response = new ArrayList<RolServiceModel>();
		for (Rol rol : roles) {
			response.add(
					new RolServiceModel(
							rol.getId(),
							rol.getName(),
							null));
		}		return response;
		
	}

	@Override
	public RolServiceModel getRolesById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RolServiceModel create(RolPostRequest rolPostRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RolServiceModel updateById(Integer id, RolPostRequest rolPostRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}

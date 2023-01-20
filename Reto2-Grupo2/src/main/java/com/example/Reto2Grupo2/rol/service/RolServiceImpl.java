package com.example.Reto2Grupo2.rol.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.Reto2Grupo2.rol.modelo.Rol;
import com.example.Reto2Grupo2.rol.modelo.RolPostRequest;
import com.example.Reto2Grupo2.rol.modelo.RolServiceModel;
import com.example.Reto2Grupo2.rol.repository.RolRepository;

@Service
public class RolServiceImpl implements RolService{

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
						
		Rol rol = rolRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Rol no encontrado"));
	
		RolServiceModel response = new RolServiceModel(
				rol.getId(),
				rol.getName(), 
				null);
			
		return response;
	}

	@Override
	public RolServiceModel create(RolPostRequest rolPostRequest) {
			
		Rol rol = new Rol(
				rolPostRequest.getName()
				);

		rol = rolRepository.save(rol);
		
		RolServiceModel rolResponse = new RolServiceModel(
				rol.getId(),
				rol.getName()
				); 
		
		return rolResponse;
	}



	@Override
	public RolServiceModel updateById(Integer id, RolPostRequest rolPostRequest) {
		
		Rol rol = rolRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.CONFLICT, "Rol no encontrado")
		);
		
		if(rolPostRequest.getName()!=null && rolPostRequest.getName()!= "") {
			rol.setName(rolPostRequest.getName());
		}	

		 rol = rolRepository.save(rol);
		 			
		 RolServiceModel  rolResponse = new RolServiceModel(
				 	rol.getId(),
				 	rol.getName()
				 	); 
		
		return rolResponse;
	}

	@Override
	public ResponseEntity<RolServiceModel> deleteById(Integer id) {
		
		try {
		rolRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (EmptyResultDataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Id de Rol no encontrada");
		}
	}

}

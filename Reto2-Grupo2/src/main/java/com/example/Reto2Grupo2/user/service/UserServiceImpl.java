package com.example.Reto2Grupo2.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.Reto2Grupo2.auth.exception.UserCantCreateException;
import com.example.Reto2Grupo2.auth.model.RolEnum;
import com.example.Reto2Grupo2.rol.modelo.Rol;
import com.example.Reto2Grupo2.rol.modelo.RolServiceModel;
import com.example.Reto2Grupo2.rol.repository.RolRepository;
import com.example.Reto2Grupo2.user.modelo.User;
import com.example.Reto2Grupo2.user.modelo.UserExpands;
import com.example.Reto2Grupo2.user.modelo.UserPostRequest;
import com.example.Reto2Grupo2.user.modelo.UserServiceModel;
import com.example.Reto2Grupo2.user.repository.UserRepository;
import com.example.Reto2Grupo2.zoo.modelo.Zoo;
import com.example.Reto2Grupo2.zoo.modelo.ZooServiceModel;
import com.example.Reto2Grupo2.zoo.repository.ZooRepository;


@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService{

	@Autowired
	private UserRepository trabajadorRepository;
	//TODO podríamos llamar al servicio del Zoo y de ahí al repositorio
	@Autowired
	private ZooRepository zooRepository;
	@Autowired
	private RolRepository rolRepository;
	
	@Override
	public List<UserServiceModel> getTrabajadores() {
		Iterable<User> trabajadores = trabajadorRepository.findAll();
		List<UserServiceModel> response = new ArrayList<UserServiceModel>();
		for (User trabajador : trabajadores) {
			response.add(
					new UserServiceModel(
							trabajador.getId(),
							trabajador.getUsername(),
							trabajador.getPassword(),
							trabajador.getEmail(),
							null,
							trabajador.getZooId(),
							null,
							trabajador.getRolId(),						
							null));
		}		return response;
	}

	@Override
	public UserServiceModel getTrabajadorById(Integer id, List<UserExpands> expand) {
		
		User trabajador  = trabajadorRepository.findById(id).orElseThrow(

				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Trabajador no encontrado"));
		
		ZooServiceModel zooResponse = null;
		
		if (expand != null && expand.indexOf (UserExpands.ZOO) != -1) {
			
			Zoo zooBD = trabajador.getZoo();		
			zooResponse = new ZooServiceModel(
					zooBD.getId(),
					zooBD.getNombre(),
					zooBD.getPvpEntrada(),
					zooBD.getWeb(),
					zooBD.getInformacion(),
					zooBD.getLatitud(),
					zooBD.getLongitud(),
					zooBD.getCiudad(),
					zooBD.getPais(),
					null,
					null);		
		}	
		RolServiceModel rolResponse = null;
		
		if (expand != null&& expand.indexOf(UserExpands.ROL) != -1) {
			
			Rol rolBD = trabajador.getRol();		
			rolResponse = new RolServiceModel(
					rolBD.getId(),
					rolBD.getName(),
					null);		
		}	
 		
		UserServiceModel response = new UserServiceModel(
				trabajador.getId(),
				trabajador.getUsername(),
				trabajador.getPassword(),
				trabajador.getEmail(),
				zooResponse,
				trabajador.getZooId(),
				rolResponse,
				trabajador.getRolId(),
				null // TODO poner los billetes
				);
		
		return response;
	}

	@Override
	public UserServiceModel create(UserPostRequest trabajadorPostRequest) {
		
		Zoo zoo = zooRepository.findById(trabajadorPostRequest.getZooId()).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Zoo no encontrado"));
		
		Rol rol = rolRepository.findById(trabajadorPostRequest.getRolId()).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Rol no encontrado"));
		
		
		User trabajador = new User(
				null,
				trabajadorPostRequest.getUsername(), 
				trabajadorPostRequest.getPassword(),
				trabajadorPostRequest.getEmail(),
				zoo,
				trabajadorPostRequest.getZooId(),
				rol,
				trabajadorPostRequest.getRolId(),
				null // TODO poner los billetes
				);

		trabajador = trabajadorRepository.save(trabajador);
		
		
		UserServiceModel trabajadorResponse = new UserServiceModel(
				trabajador.getId(),
				trabajador.getUsername(),
				trabajador.getPassword(),
				trabajador.getEmail(),
				null,
				zoo.getId(),//trampeado, evento.getZooId**Si no sale null
				null,
				rol.getId(),
				null); //TODO poner los billetes
		 return trabajadorResponse;			
	}

	@Override
	public UserServiceModel updateById(Integer id, UserPostRequest trabajadorPostRequest) {
		
		//SI SE MODIFICA UN registro QUE NO EXISTE, PROVOCAMOS ESTE ERROR
		User trabajador = trabajadorRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.CONFLICT, "Trabajador no encontrado")
		);
		
		if ( trabajadorPostRequest.getUsername()!=null && trabajadorPostRequest.getUsername()!= "") {
			trabajador.setUsername(trabajadorPostRequest.getUsername());
		}	
		if ( trabajadorPostRequest.getPassword()!=null && trabajadorPostRequest.getPassword()!= "") {
			trabajador.setPassword(trabajadorPostRequest.getPassword());
		}
		if ( trabajadorPostRequest.getZooId()!= null ) {
			trabajador.setZooId(trabajadorPostRequest.getZooId());
		}
		if ( trabajadorPostRequest.getRolId()!=null ) {
			trabajador.setRolId(trabajadorPostRequest.getRolId());
		}
				
		Zoo zoo = zooRepository.findById(trabajadorPostRequest.getZooId()).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Zoo no encontrado"));
		
		Rol rol = rolRepository.findById(trabajadorPostRequest.getRolId()).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Rol no encontrado"));
		
		if (zoo != null) {
			trabajador.setZoo(zoo);
		}
		if (rol != null) {
			trabajador.setRol(rol);
		}
			trabajador = trabajadorRepository.save(trabajador);
			
		UserServiceModel trabajadorResponse = new UserServiceModel(
				trabajador.getId(),
				trabajador.getUsername(),
				trabajador.getPassword(),
				trabajador.getEmail(),
				null,
				zoo.getId(),//Trampeado, evento.getZooId**, lo trae null......
				null,
				rol.getId(),
				null); //TODO poner billete
		return trabajadorResponse;
				
	}

	@Override
	public User signupEmpleado(User trabajador) throws UserCantCreateException {
			
		BCryptPasswordEncoder  passEncoder = new BCryptPasswordEncoder();
		String password = passEncoder.encode(trabajador.getPassword());		
		trabajador.setPassword(password);

		Zoo zoo = zooRepository.findById(trabajador.getZooId()).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Zoo no encontrado"));
		trabajador.setZoo(zoo);// esto es lo que hace que se inserte el zooID
		
		Rol trabajadorRol = rolRepository.findByName(RolEnum.EMPLEADO.name()); 	
		trabajador.setRol(trabajadorRol);
			
		try{
			return trabajadorRepository.save(trabajador);
		}catch (DataAccessException e) {
			throw new UserCantCreateException(e.getMessage());
		}		
	
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 return trabajadorRepository.findByUsername(username)
                 .orElseThrow(
                         () -> new UsernameNotFoundException("User " + username + " not found"));
	}


	@Override
	public User signupCliente(User cliente) throws UserCantCreateException {
		BCryptPasswordEncoder  passEncoder = new BCryptPasswordEncoder();
		String password = passEncoder.encode(cliente.getPassword());		
		cliente.setPassword(password);

		
		Rol trabajadorRol = rolRepository.findByName(RolEnum.CLIENTE.name()); 	
		cliente.setRol(trabajadorRol);
			
		try{
			return trabajadorRepository.save(cliente);
		}catch (DataAccessException e) {
			throw new UserCantCreateException(e.getMessage());
		}		
	}
	
//	@Override
//	public Integer deleteById(Integer id) {	
//		Integer respuesta =0;
//		try {
//			trabajadorRepository.deleteById(id);
//			respuesta = 1;
//		} catch (EmptyResultDataAccessException e) {
//			respuesta = 2;
//		}
//		return 	respuesta;
//	}

}

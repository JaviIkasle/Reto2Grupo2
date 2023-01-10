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
import com.example.Reto2Grupo2.trabajador.modelo.Trabajador;
import com.example.Reto2Grupo2.trabajador.modelo.TrabajadorServiceModel;
import com.example.Reto2Grupo2.trabajador.repository.TrabajadorRepository;
import com.example.Reto2Grupo2.user.modelo.User;
import com.example.Reto2Grupo2.user.modelo.UserExpands;
import com.example.Reto2Grupo2.user.modelo.UserPostRequest;
import com.example.Reto2Grupo2.user.modelo.UserServiceModel;
import com.example.Reto2Grupo2.user.repository.UserRepository;


@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	//TODO podríamos llamar al servicio del Zoo y de ahí al repositorio
	@Autowired
	private TrabajadorRepository trabajadorRepository;
	@Autowired
	private RolRepository rolRepository;
	

	
	@Override
	public List<UserServiceModel> getTrabajadores() {
		Iterable<User> users = userRepository.findAll();
		List<UserServiceModel> response = new ArrayList<UserServiceModel>();
		for (User user : users) {
			response.add(
					new UserServiceModel(
							user.getId(),
							user.getUsername(),
							user.getPassword(),
							null,
							null,
							user.getRolId()));
		}		return response;
	}

	@Override
	public UserServiceModel getTrabajadorById(Integer id, List<UserExpands> expand) {
		
		User user  = userRepository.findById(id).orElseThrow(

				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "User no encontrado"));
		
		TrabajadorServiceModel userResponse = null;
		
//		if (expand != null && expand.indexOf (UserExpands.TRABAJADOR) != -1) {
//								
//			Trabajador trabajadorBd = user.getTrabajador();	
//			
//			
//			userResponse = new TrabajadorServiceModel(
//					trabajadorBd.getId(),
//					trabajadorBd.getPuesto(),
//					trabajadorBd.getEspecializacion(),
//					null);		
//		}	
		
		RolServiceModel rolResponse = null;
		
		if (expand != null && expand.indexOf(UserExpands.ROL) != -1) {
			
			Rol rolBD = user.getRol();		
			rolResponse = new RolServiceModel(
					rolBD.getId(),
					rolBD.getName(),
					null);		
		}	
		
//		if (expand != null && expand.indexOf(UserExpands.ROL) != -1) {
//			
//			Rol rolBD = user.getRol();		
//			rolResponse = new RolServiceModel(
//					rolBD.getId(),
//					rolBD.getName(),
//					null);		
//		}	
 		
		UserServiceModel response = new UserServiceModel(
				user.getId(),
				user.getUsername(),
				user.getPassword(),
				userResponse,
				rolResponse,
				user.getRolId()
				);
		
		return response;
	}

	@Override
	public UserServiceModel create(UserPostRequest userPostRequest) {
				
		
		Rol rol = rolRepository.findById(userPostRequest.getRolId()).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Rol no encontrado"));
		
		
		User user = new User(
				null,
				userPostRequest.getUsername(), 
				userPostRequest.getPassword(),
				null,//TODO se podria crear otro constructor sin este atributo
				rol,
				userPostRequest.getRolId()
				);

		user = userRepository.save(user);
		
		
		UserServiceModel userResponse = new UserServiceModel(
				user.getId(),
				user.getUsername(),
				user.getPassword(),
				null,
				null,
				rol.getId()); //trampeado, evento.getZooId**Si no sale null
			
		 return userResponse;			
	}

	@Override
	public UserServiceModel updateById(Integer id, UserPostRequest userPostRequest) {
		
		//SI SE MODIFICA UN registro QUE NO EXISTE, PROVOCAMOS ESTE ERROR
		User user = userRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.CONFLICT, "User no encontrado")
		);
		
		if ( userPostRequest.getUsername()!=null && userPostRequest.getUsername()!= "") {
			user.setUsername(userPostRequest.getUsername());
		}	
		if ( userPostRequest.getPassword()!=null && userPostRequest.getPassword()!= "") {
			user.setPassword(userPostRequest.getPassword());
		}
		if ( userPostRequest.getRolId()!=null ) {
			user.setRolId(userPostRequest.getRolId());
		}
				
		
		Rol rol = rolRepository.findById(userPostRequest.getRolId()).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Rol no encontrado"));
		
		if (rol != null) {
			user.setRol(rol);
		}
			user = userRepository.save(user);
			
		UserServiceModel userResponse = new UserServiceModel(
				user.getId(),
				user.getUsername(),
				user.getPassword(),
				null,
				null,
				rol.getId()); //trampeado, evento.getZooId**Si no sale null); 
		
		return userResponse;				
	}

	@Override
	public User signUpUser(User user) throws UserCantCreateException {
			
		BCryptPasswordEncoder  passEncoder = new BCryptPasswordEncoder();
		String password = passEncoder.encode(user.getPassword());		
		user.setPassword(password);

		Rol userRol = rolRepository.findByName(RolEnum.ADMIN.name()); 	
		user.setRol(userRol);
				
		try{
			return userRepository.save(user);
		}catch (DataAccessException e) {
			throw new UserCantCreateException(e.getMessage());
		}		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 return userRepository.findByUsername(username)
                 .orElseThrow(
                         () -> new UsernameNotFoundException("User " + username + " not found"));
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

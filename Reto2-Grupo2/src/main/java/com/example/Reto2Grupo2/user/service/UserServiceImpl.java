package com.example.Reto2Grupo2.user.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.Reto2Grupo2.auth.exception.UserCantCreateException;
import com.example.Reto2Grupo2.rol.modelo.Rol;
import com.example.Reto2Grupo2.rol.modelo.RolEnum;
import com.example.Reto2Grupo2.rol.modelo.RolServiceModel;
import com.example.Reto2Grupo2.rol.repository.RolRepository;
import com.example.Reto2Grupo2.user.modelo.AuthRequestEmple;
import com.example.Reto2Grupo2.user.modelo.ClienteUpdateByAdminRequest;
import com.example.Reto2Grupo2.user.modelo.ClienteUpdateRequest;
import com.example.Reto2Grupo2.user.modelo.User;
import com.example.Reto2Grupo2.user.modelo.UserExpands;
import com.example.Reto2Grupo2.user.modelo.UserServiceModel;
import com.example.Reto2Grupo2.user.modelo.EmpleUpdateByAdminRequest;
import com.example.Reto2Grupo2.user.repository.UserRepository;
import com.example.Reto2Grupo2.zoo.modelo.Zoo;
import com.example.Reto2Grupo2.zoo.modelo.ZooServiceModel;
import com.example.Reto2Grupo2.zoo.repository.ZooRepository;

import jakarta.validation.Valid;


@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ZooRepository zooRepository;
	@Autowired
	private RolRepository rolRepository;
	
	@Override
	public List<UserServiceModel> getUsers() {
		Iterable<User> trabajadores = userRepository.findAll();
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
	public UserServiceModel getUserById(Integer id, List<UserExpands> expand) {
		
		User trabajador  = userRepository.findById(id).orElseThrow(

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
	public UserServiceModel updateClienteByAdmin(Integer id, @Valid ClienteUpdateByAdminRequest clienteUpdateByAdmin ) {
		//SI SE MODIFICA UN registro QUE NO EXISTE, PROVOCAMOS ESTE ERROR
				User user = userRepository.findById(id).orElseThrow(
						() -> new ResponseStatusException(HttpStatus.CONFLICT, "User no encontrado")
				);	
				
				if(user.getRol().getName().equalsIgnoreCase("CLIENTE")) {
								
					
					Rol rolOld = rolRepository.findById(user.getRolId()).orElseThrow(
							() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Rol no encontrado"));
					
					if ( clienteUpdateByAdmin.getUsername()!=null && clienteUpdateByAdmin.getUsername()!= "") {
						user.setUsername(clienteUpdateByAdmin.getUsername());
					}	
					if ( clienteUpdateByAdmin.getPassword()!=null && clienteUpdateByAdmin.getPassword()!= "") {
						BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
						user.setPassword(passwordEncoder.encode(clienteUpdateByAdmin.getPassword()));
					}
					if ( clienteUpdateByAdmin.getEmail()!=null && clienteUpdateByAdmin.getEmail()!= "") {
						user.setEmail(clienteUpdateByAdmin.getEmail());
					}	
																
					if ( clienteUpdateByAdmin.getRolId()!=null ) {			
						Rol rolNew = rolRepository.findById(clienteUpdateByAdmin.getRolId()).orElseThrow(
								() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Rol no encontrado"));
						user.setRol(rolNew);
						user.setRolId(clienteUpdateByAdmin.getRolId());
					}else {			
						user.setRol(rolOld);
					}
															
						user = userRepository.save(user);
					
						UserServiceModel trabajadorResponse = new UserServiceModel(
								user.getId(),
								user.getUsername(),
								user.getPassword(),
								user.getEmail(),
								null,
								user.getZooId(),
								null,
								user.getRolId(),
								null); //billete
						
						return trabajadorResponse;
				}else{
					throw new ResponseStatusException(HttpStatus.CONFLICT, "No es un cliente");
				}
	}


	@Override
	public UserServiceModel updateEmpleByAdmin(Integer id, EmpleUpdateByAdminRequest userUpdateRequest) {


		//SI SE MODIFICA UN registro QUE NO EXISTE, PROVOCAMOS ESTE ERROR
		User user = userRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.CONFLICT, "User no encontrado")
		);	
		
		if(user.getRol().getName().equalsIgnoreCase("EMPLEADO")) {
			
			Zoo zooOld = zooRepository.findById(user.getZooId()).orElseThrow(
					() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Zoo no encontrado"));
			
			
			Rol rolOld = rolRepository.findById(user.getRolId()).orElseThrow(
					() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Rol no encontrado"));
			
			if ( userUpdateRequest.getUsername()!=null && userUpdateRequest.getUsername()!= "") {
				user.setUsername(userUpdateRequest.getUsername());
			}	
			if ( userUpdateRequest.getPassword()!=null && userUpdateRequest.getPassword()!= "") {
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				user.setPassword(passwordEncoder.encode(userUpdateRequest.getPassword()));
			}
			if ( userUpdateRequest.getEmail()!=null && userUpdateRequest.getEmail()!= "") {
				user.setEmail(userUpdateRequest.getEmail());
			}	
					
				
			if ( userUpdateRequest.getZooId()!= null ) {
				Zoo zooNew = zooRepository.findById(userUpdateRequest.getZooId()).orElseThrow(
							() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Zoo no encontrado"));
				user.setZooId(userUpdateRequest.getZooId());
				user.setZoo(zooNew);
			}else {										
				user.setZoo(zooOld);									
			}
			
			
			if ( userUpdateRequest.getRolId()!=null ) {			
				Rol rolNew = rolRepository.findById(userUpdateRequest.getRolId()).orElseThrow(
						() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Rol no encontrado"));
				user.setRol(rolNew);
				user.setRolId(userUpdateRequest.getRolId());
			}else {			
				user.setRol(rolOld);
			}
													
				user = userRepository.save(user);
			
				UserServiceModel trabajadorResponse = new UserServiceModel(
						user.getId(),
						user.getUsername(),
						user.getPassword(),
						user.getEmail(),
						null,
						user.getZooId(),
						null,
						user.getRolId(),
						null); //billete
				
				return trabajadorResponse;
		}else{
			throw new ResponseStatusException(HttpStatus.CONFLICT, "No es un empleado");
		}
			
	}

	@Override
	public User signupEmpleado(AuthRequestEmple requestEmple) throws UserCantCreateException {
			
		BCryptPasswordEncoder  passEncoder = new BCryptPasswordEncoder();
		String password = passEncoder.encode(requestEmple.getPassword());		
		requestEmple.setPassword(password);

		
		
		Zoo zoo = zooRepository.findById(requestEmple.getZooId()).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Zoo no encontrado"));
		
		
		Rol trabajadorRol = rolRepository.findByName(RolEnum.EMPLEADO.name()); 	
		
		
		
		User empleado = new User(
		null,
		requestEmple.getUsername(), 
		requestEmple.getPassword(),
		requestEmple.getEmail(),
		zoo,
		zoo.getId(),
		trabajadorRol,
		trabajadorRol.getId(),
		null //billete
		);
			
		try{
			return userRepository.save(empleado);
		}catch (DataAccessException e) {
			throw new UserCantCreateException(e.getMessage());
		}		
	
	}


	@Override
	public User signupCliente(User cliente) throws UserCantCreateException {
		BCryptPasswordEncoder  passEncoder = new BCryptPasswordEncoder();
		String password = passEncoder.encode(cliente.getPassword());		
		cliente.setPassword(password);
		
		Rol trabajadorRol = rolRepository.findByName(RolEnum.CLIENTE.name()); 	
		cliente.setRol(trabajadorRol);
			
		try{
			return userRepository.save(cliente);
		}catch (DataAccessException e) {
			throw new UserCantCreateException(e.getMessage());
		}		
	}
	
	@Override
	public UserServiceModel updateCliente(ClienteUpdateRequest clienteUpdateRequest, Integer userId) {

		System.out.println("eeeeeeeee " + userId);
		
		User cliente = userRepository.findById(userId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.CONFLICT, "Cliente no encontrado")
		);
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

				
				if ( clienteUpdateRequest.getUsername()!=null && clienteUpdateRequest.getUsername()!= "") {
					cliente.setUsername(clienteUpdateRequest.getUsername());
				}	
				
				
				if ( clienteUpdateRequest.getOldPassword()!=null && clienteUpdateRequest.getOldPassword()!= "" 
							&& clienteUpdateRequest.getNewPassword() != null && clienteUpdateRequest.getNewPassword() != "") {
						
							String oldPass = clienteUpdateRequest.getOldPassword();
							String newPass = clienteUpdateRequest.getNewPassword();
												
							if (passwordEncoder.matches(oldPass, cliente.getPassword())) {																	
							cliente.setPassword(passwordEncoder.encode(newPass));
							
							} else {
								throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Contraseña incorrecta");		
							}																													
								
				}

				if ( clienteUpdateRequest.getEmail()!=null && clienteUpdateRequest.getEmail()!= "") {
					cliente.setEmail(clienteUpdateRequest.getEmail());
				}	
						
				cliente = userRepository.save(cliente);
					
				UserServiceModel clienteResponse = new UserServiceModel(
						cliente.getId(),
						cliente.getUsername(),
						cliente.getPassword(),
						cliente.getEmail());
				return clienteResponse;
		
	}
	
	// carga los detalles de usuario.
	// la validez de la contraseña es automatica. Si es incorrecta no se loguea y devuelve 401
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 return userRepository.findByUsername(username)
                 .orElseThrow(
                         () -> new UsernameNotFoundException("User " + username + " not found"));
	}

	@Override
	public ResponseEntity<UserServiceModel> deleteById(Integer id) {	
		
		try {
			userRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch (EmptyResultDataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Id del User no encontrada");
		}		
	}

	@Override
	public ResponseEntity<UserServiceModel> deleteCliente(Integer id) {

		try {
			userRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch (EmptyResultDataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Id del Cliente no encontrada");
		}		
				
	}


	
	

//	YA LO HACE EL SIGNUP
//@Override
//public UserServiceModel create(UserPostRequest trabajadorPostRequest) {
//	
//	Zoo zoo = zooRepository.findById(trabajadorPostRequest.getZooId()).orElseThrow(
//			() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Zoo no encontrado"));
//	
//	Rol rol = rolRepository.findById(trabajadorPostRequest.getRolId()).orElseThrow(
//			() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Rol no encontrado"));
//	
//	
//	User trabajador = new User(
//			null,
//			trabajadorPostRequest.getUsername(), 
//			trabajadorPostRequest.getPassword(),
//			trabajadorPostRequest.getEmail(),
//			zoo,
//			trabajadorPostRequest.getZooId(),
//			rol,
//			trabajadorPostRequest.getRolId(),
//			null //billete
//			);
//
//	trabajador = trabajadorRepository.save(trabajador);
//	
//	
//	UserServiceModel trabajadorResponse = new UserServiceModel(
//			trabajador.getId(),
//			trabajador.getUsername(),
//			trabajador.getPassword(),
//			trabajador.getEmail(),
//			null,
//			zoo.getId(),
//			null,
//			rol.getId(),
//			null); 
//	 return trabajadorResponse;			
//}
	
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

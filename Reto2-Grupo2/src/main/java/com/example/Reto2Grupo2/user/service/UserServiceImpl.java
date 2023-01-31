package com.example.Reto2Grupo2.user.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
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
import com.example.Reto2Grupo2.email.modelo.GeneradorPass;
import com.example.Reto2Grupo2.email.modelo.Mensaje;
import com.example.Reto2Grupo2.cifradoRSA.CifradoRSA;
import com.example.Reto2Grupo2.cifradoRSA.OurPassEncoder;
import com.example.Reto2Grupo2.rol.modelo.Rol;
import com.example.Reto2Grupo2.rol.modelo.RolEnum;
import com.example.Reto2Grupo2.rol.modelo.RolServiceModel;
import com.example.Reto2Grupo2.rol.repository.RolRepository;
import com.example.Reto2Grupo2.rol.service.RolService;
import com.example.Reto2Grupo2.user.modelo.AuthRequestAdmin;
import com.example.Reto2Grupo2.user.modelo.AuthRequestCliente;
import com.example.Reto2Grupo2.user.modelo.AuthRequestEmple;
import com.example.Reto2Grupo2.user.modelo.ClienteUpdateAndroid;
import com.example.Reto2Grupo2.user.modelo.ClienteUpdateByAdminRequest;
import com.example.Reto2Grupo2.user.modelo.ClienteUpdateRequest;
import com.example.Reto2Grupo2.user.modelo.EmailService;
import com.example.Reto2Grupo2.user.modelo.EmpleUpdateByAdminRequest;
import com.example.Reto2Grupo2.user.modelo.User;
import com.example.Reto2Grupo2.user.modelo.UserExpands;
import com.example.Reto2Grupo2.user.modelo.UserServiceModel;
import com.example.Reto2Grupo2.user.repository.UserRepository;
import com.example.Reto2Grupo2.zoo.modelo.Zoo;
import com.example.Reto2Grupo2.zoo.modelo.ZooServiceModel;
import com.example.Reto2Grupo2.zoo.repository.ZooRepository;


import jakarta.validation.Valid;

@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService{

	@Autowired
	RolService rolService ;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ZooRepository zooRepository; 
	@Autowired
	private RolRepository rolRepository;
	@Autowired
	private CifradoRSA cifradoRSA;
	
	@Override
	public List<UserServiceModel> getUsers() {
		Iterable<User> users = userRepository.findAll();
		List<UserServiceModel> response = new ArrayList<UserServiceModel>();
		for (User user : users) {
			response.add(
					new UserServiceModel(
							user.getId(),
							user.getUsername(),
							user.getEmail(),
							null,
							user.getZooId(),
							null,
							user.getRolId(),						
							null));
		}		return response;
	}

	@Override
	public UserServiceModel getUserById(Integer id, List<UserExpands> expand) {
		
		User user  = userRepository.findById(id).orElseThrow(

				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "User no encontrado"));
		
		ZooServiceModel zooResponse = null;
		
		if (expand != null && expand.indexOf (UserExpands.ZOO) != -1) {
			
			Zoo zooBD = user.getZoo();		
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
			
			Rol rolBD = user.getRol();		
			rolResponse = new RolServiceModel(
					rolBD.getId(),
					rolBD.getName(),
					null);		
		}	
 		
		UserServiceModel response = new UserServiceModel(
				user.getId(),
				user.getUsername(),
				user.getEmail(),
				zooResponse,
				user.getZooId(),
				rolResponse,
				user.getRolId(),
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
													
				RolServiceModel rolServiceModel =rolService.getRolesById(user.getRolId());
				
				Rol rolOld = new Rol(
								rolServiceModel.getId(),
								rolServiceModel.getName(),
								null);
					
					
					if ( clienteUpdateByAdmin.getUsername()!=null && clienteUpdateByAdmin.getUsername()!= "") {
						user.setUsername(clienteUpdateByAdmin.getUsername());
					}	
					if ( clienteUpdateByAdmin.getPassword()!=null && clienteUpdateByAdmin.getPassword()!= "") {
						//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
						OurPassEncoder encoder = new OurPassEncoder();	
						
						user.setPassword(encoder.encode(clienteUpdateByAdmin.getPassword()));
					}
					if ( clienteUpdateByAdmin.getEmail()!=null && clienteUpdateByAdmin.getEmail()!= "") {
						user.setEmail(clienteUpdateByAdmin.getEmail());
					}	
																
					if ( clienteUpdateByAdmin.getRolId()!=null ) {		
						
						 rolServiceModel =rolService.getRolesById(clienteUpdateByAdmin.getRolId());
						
						Rol rolNew = new Rol(
										rolServiceModel.getId(),
										rolServiceModel.getName(),
										null);
						
						user.setRol(rolNew);
						
						user.setRolId(clienteUpdateByAdmin.getRolId());
					}else {			
						user.setRol(rolOld);
					}
															
						user = userRepository.save(user);
					
						UserServiceModel userResponse = new UserServiceModel(
								user.getId(),
								user.getUsername(),

								user.getEmail(),
								null,
								user.getZooId(),
								null,
								user.getRolId(),
								null); //billete
						
						return userResponse;
				}else{
					throw new ResponseStatusException(HttpStatus.CONFLICT, "No es un cliente");
				}
	}


	@Override
	public UserServiceModel updateEmpleByAdmin(Integer id, EmpleUpdateByAdminRequest empleUpdateRequest) {


		//SI SE MODIFICA UN registro QUE NO EXISTE, PROVOCAMOS ESTE ERROR
		User user = userRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.CONFLICT, "User no encontrado")
		);	
		
		if(user.getRol().getName().equalsIgnoreCase("EMPLEADO")) {
								
			Zoo zooOld = zooRepository.findById(user.getZooId()).orElseThrow(
					() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Zoo no encontrado"));
			
			
			RolServiceModel rolServiceModel =rolService.getRolesById(user.getRolId());
			
			Rol rolOld = new Rol(
							rolServiceModel.getId(),
							rolServiceModel.getName(),
							null);
						
			
			if ( empleUpdateRequest.getUsername()!=null && empleUpdateRequest.getUsername()!= "") {
				user.setUsername(empleUpdateRequest.getUsername());
			}	
			if ( empleUpdateRequest.getPassword()!=null && empleUpdateRequest.getPassword()!= "") {
				//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				
				OurPassEncoder encoder = new OurPassEncoder();	
				
				user.setPassword(encoder.encode(empleUpdateRequest.getPassword()));
			}
			if ( empleUpdateRequest.getEmail()!=null && empleUpdateRequest.getEmail()!= "") {
				user.setEmail(empleUpdateRequest.getEmail());
			}	
							
			if ( empleUpdateRequest.getZooId()!= null ) {
				Zoo zooNew = zooRepository.findById(empleUpdateRequest.getZooId()).orElseThrow(
							() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Zoo no encontrado"));
				user.setZooId(empleUpdateRequest.getZooId());
				user.setZoo(zooNew);
			}else {										
				user.setZoo(zooOld);									
			}
						
			if ( empleUpdateRequest.getRolId()!=null ) {	
				
							
				rolServiceModel =rolService.getRolesById(empleUpdateRequest.getRolId());
				
				Rol rolNew = new Rol(
								rolServiceModel.getId(),
								rolServiceModel.getName(),
								null);

				user.setRol(rolNew);
				user.setRolId(empleUpdateRequest.getRolId());
			}else {			
				user.setRol(rolOld);
			}
													
				user = userRepository.save(user);
			
				UserServiceModel userResponse = new UserServiceModel(
						user.getId(),
						user.getUsername(),

						user.getEmail(),
						null,
						user.getZooId(),
						null,
						user.getRolId(),
						null); //billete
				
				return userResponse;
		}else{
			throw new ResponseStatusException(HttpStatus.CONFLICT, "No es un empleado");
		}
			
	}

	@Override
	public User signupEmpleado(AuthRequestEmple requestEmple) throws UserCantCreateException {
			
//		//TODO to OurpassEncoder
//		BCryptPasswordEncoder  passEncoder = new BCryptPasswordEncoder();
//		String password = passEncoder.encode();
		
		OurPassEncoder encoder = new OurPassEncoder();	
		
		requestEmple.setPassword(encoder.encode(requestEmple.getPassword()));
	
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
	public User signupCliente(AuthRequestCliente request) throws UserCantCreateException {
		
		User cliente = new User(request.getUsername(), request.getPassword(), request.getEmail());	
		
		OurPassEncoder encoder = new OurPassEncoder();	
		
		cliente.setPassword(encoder.encode(cliente.getPassword()));
		
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

		
		User cliente = userRepository.findById(userId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.CONFLICT, "Cliente no encontrado")
		);
		
		//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				
				if ( clienteUpdateRequest.getUsername()!=null && clienteUpdateRequest.getUsername()!= "") {
					cliente.setUsername(clienteUpdateRequest.getUsername());
				}	
								
				if ( clienteUpdateRequest.getOldPassword()!=null && clienteUpdateRequest.getOldPassword()!= "" 
							&& clienteUpdateRequest.getNewPassword() != null && clienteUpdateRequest.getNewPassword() != "") {
						
							String oldPass = clienteUpdateRequest.getOldPassword();
							String newPass = clienteUpdateRequest.getNewPassword();
													
							OurPassEncoder encoder = new OurPassEncoder();						
															
							if (encoder.matches(oldPass, cliente.getPassword())) {							
							cliente.setPassword(encoder.encode(newPass));
							
							} else {
								throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Contraseña incorrecta");		
							}																													
				}

				if ( clienteUpdateRequest.getEmail()!=null && clienteUpdateRequest.getEmail()!= "") {
					cliente.setEmail(clienteUpdateRequest.getEmail());
				}	
						
				cliente = userRepository.save(cliente);
				
				new Mensaje().enviarMensaje();
					
				UserServiceModel clienteResponse = new UserServiceModel(
						cliente.getId(),
						cliente.getUsername(),

						cliente.getEmail());
				return clienteResponse;
		
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

	@Override
	public User signUpAdmin(AuthRequestAdmin request) throws UserCantCreateException {

		BCryptPasswordEncoder  passEncoder = new BCryptPasswordEncoder();
		String password = passEncoder.encode(request.getPassword());
		
		OurPassEncoder encoder = new OurPassEncoder();	
		
		request.setPassword(encoder.encode(password));
		
		Rol rolAdmin = rolRepository.findByName(RolEnum.ADMIN.name());
		
		User admin = new User(
				null,
				request.getUsername(), 
				request.getPassword(),
				request.getEmail(),
				null,
				null,
				rolAdmin,
				rolAdmin.getId(),
				null
				);
		
		try{
			return userRepository.save(admin);
		}catch (DataAccessException e) {
			throw new UserCantCreateException(e.getMessage());
		}		
	}
	
	// carga los detalles de usuario.
	// la validez de la contraseña es automatica. Si es incorrecta no se loguea y devuelve 401
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 return userRepository.findByUsername(username)
                 .orElseThrow(
                         () -> new UsernameNotFoundException("User " + username + " not found"));
	}


	/*
	 * 
	 * 							PARA ANDROID Y PSP
	 * 
	 *
	 * 
	 */

	@Override
	public User signupClienteAndroid(AuthRequestCliente  request) throws UserCantCreateException {
		
		byte[] decodedString=null;
			
		try {
			// Convierte BASE64 pass en bytes
			decodedString = Base64.getDecoder().decode(request.getPassword().getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

			// Descifra los bytes para sacar la pass
		byte[] passDescifrada = cifradoRSA.descifrarTexto(decodedString);	
			//La pass en String
		String pass = new String(passDescifrada);	
		
		User cliente = new User(request.getUsername(), pass, request.getEmail());
		
		OurPassEncoder encoder = new OurPassEncoder();	
		
		cliente.setPassword(encoder.encode(cliente.getPassword()));
		
		Rol trabajadorRol = rolRepository.findByName(RolEnum.CLIENTE.name()); 	
		cliente.setRol(trabajadorRol);
			
		try{
			return userRepository.save(cliente);
		}catch (DataAccessException e) {
			throw new UserCantCreateException(e.getMessage());
		}		
	}

	@Override
	public UserServiceModel updateClienteAndroid(ClienteUpdateAndroid clienteUpdateAndroid, Integer userId) {
		
		
		//String pass = null;
		String oldPass = null;
		String newPass = null;
		
		byte[] decodedOld;
		byte[] decodedNew;


			try {
				decodedOld = Base64.getDecoder().decode(clienteUpdateAndroid.getOldPassword().getBytes("UTF-8"));
				decodedNew = Base64.getDecoder().decode(clienteUpdateAndroid.getNewPassword().getBytes("UTF-8"));
				
				byte[] oldPassCifrada = cifradoRSA.descifrarTexto(decodedOld);		
				byte[] newPassCifrada = cifradoRSA.descifrarTexto(decodedNew);
				
				oldPass = new String(oldPassCifrada);
				newPass = new String(newPassCifrada);
				
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		
		
		User cliente = userRepository.findById(userId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.CONFLICT, "Cliente no encontrado")
		);
		
		//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				
				if ( clienteUpdateAndroid.getUsername()!=null && clienteUpdateAndroid.getUsername()!= "") {
					cliente.setUsername(clienteUpdateAndroid.getUsername());
				}	
								
				if ( clienteUpdateAndroid.getOldPassword()!=null && oldPass != "" 
							&& clienteUpdateAndroid.getNewPassword() != null && newPass != "") {
												
							
							OurPassEncoder encoder = new OurPassEncoder();						
															
							if (encoder.matches(oldPass, cliente.getPassword())) {							
							cliente.setPassword(encoder.encode(newPass));
							
							} else {
								throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Contraseña incorrecta");		
							}																													
				}
	
						
				cliente = userRepository.save(cliente);
					
				UserServiceModel clienteResponse = new UserServiceModel(
						cliente.getId(),
						cliente.getUsername(),

						cliente.getEmail());
				return clienteResponse;
	}
	
	@Override
	public UserServiceModel generateClientePassword(String email) {
		// TODO Auto-generated method stub
		
	
		User user =userRepository.findUserByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User " + email + " not found"));

		
			GeneradorPass pass = new GeneradorPass();
			String contra = pass.generatePassword();
			
			user.setPassword(contra);
			
			
			new Mensaje().enviarPassAleatoria(contra);
			
			user = userRepository.save(user);
			
			UserServiceModel ret = new UserServiceModel(
					null,
					user.getUsername(),
					user.getEmail()
					);
		
		return ret;
	}
	
}

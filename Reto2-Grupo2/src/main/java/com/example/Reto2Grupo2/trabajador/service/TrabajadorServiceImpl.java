package com.example.Reto2Grupo2.trabajador.service;

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
import com.example.Reto2Grupo2.auth.persistence.Rol;
import com.example.Reto2Grupo2.auth.persistence.Trabajador;
import com.example.Reto2Grupo2.rol.modelo.RolServiceModel;
import com.example.Reto2Grupo2.rol.repository.RolRepository;
import com.example.Reto2Grupo2.trabajador.modelo.TrabajadorExpands;
import com.example.Reto2Grupo2.trabajador.modelo.TrabajadorPostRequest;
import com.example.Reto2Grupo2.trabajador.modelo.TrabajadorServiceModel;
import com.example.Reto2Grupo2.trabajador.repository.TrabajadorRepository;
import com.example.Reto2Grupo2.zoo.modelo.Zoo;
import com.example.Reto2Grupo2.zoo.modelo.ZooServiceModel;
import com.example.Reto2Grupo2.zoo.repository.ZooRepository;


@Service("userDetailsService")
public class TrabajadorServiceImpl implements TrabajadorService, UserDetailsService{

	@Autowired
	private TrabajadorRepository trabajadorRepository;
	//TODO podríamos llamar al servicio del Zoo y de ahí al repositorio
	@Autowired
	private ZooRepository zooRepository;
	@Autowired
	private RolRepository rolRepository;
	
	@Override
	public List<TrabajadorServiceModel> getTrabajadores() {
		Iterable<Trabajador> trabajadores = trabajadorRepository.findAll();
		List<TrabajadorServiceModel> response = new ArrayList<TrabajadorServiceModel>();
		for (Trabajador trabajador : trabajadores) {
			response.add(
					new TrabajadorServiceModel(
							trabajador.getId(),
							trabajador.getUsername(),
							trabajador.getPassword(),
							null,
							trabajador.getZooId(),
							null,
							trabajador.getRolId()));
		}		return response;
	}

	@Override
	public TrabajadorServiceModel getTrabajadorById(Integer id, List<TrabajadorExpands> expand) {
		
		Trabajador trabajador  = trabajadorRepository.findById(id).orElseThrow(

				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Trabajador no encontrado"));
		
		ZooServiceModel zooResponse = null;
		
		if (expand != null&& expand.indexOf(TrabajadorExpands.ZOO) != -1) {
			
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
		
		if (expand != null&& expand.indexOf(TrabajadorExpands.ROL) != -1) {
			
			Rol rolBD = trabajador.getRol();		
			rolResponse = new RolServiceModel(
					rolBD.getId(),
					rolBD.getName(),
					null);		
		}	
 		
		TrabajadorServiceModel response = new TrabajadorServiceModel(
				trabajador.getId(),
				trabajador.getUsername(),
				trabajador.getPassword(),
				zooResponse,
				trabajador.getZooId(),
				rolResponse,
				trabajador.getRolId()
				);
		
		return response;
	}

	@Override
	public TrabajadorServiceModel create(TrabajadorPostRequest trabajadorPostRequest) {
		
		Zoo zoo = zooRepository.findById(trabajadorPostRequest.getZooId()).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Zoo no encontrado"));
		
		Rol rol = rolRepository.findById(trabajadorPostRequest.getRolId()).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Rol no encontrado"));
		
		
		Trabajador trabajador = new Trabajador(
				null,
				trabajadorPostRequest.getUsername(), 
				trabajadorPostRequest.getPassword(),
				zoo,
				trabajadorPostRequest.getZooId(),
				rol,
				trabajadorPostRequest.getRolId()
				);

		trabajador = trabajadorRepository.save(trabajador);
		
		
		TrabajadorServiceModel trabajadorResponse = new TrabajadorServiceModel(
				trabajador.getId(),
				trabajador.getUsername(),
				trabajador.getPassword(),
				null,
				zoo.getId(),//trampeado, evento.getZooId**Si no sale null
				null,
				rol.getId()); //trampeado, evento.getZooId**Si no sale null
		 return trabajadorResponse;			
	}

	@Override
	public TrabajadorServiceModel updateById(Integer id, TrabajadorPostRequest trabajadorPostRequest) {
		
		//SI SE MODIFICA UN registro QUE NO EXISTE, PROVOCAMOS ESTE ERROR
		Trabajador trabajador = trabajadorRepository.findById(id).orElseThrow(
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
			
		TrabajadorServiceModel trabajadorResponse = new TrabajadorServiceModel(
				trabajador.getId(),
				trabajador.getUsername(),
				trabajador.getPassword(),
				null,
				zoo.getId(),//Trampeado, evento.getZooId**, lo trae null......
				null,
				rol.getId()); //trampeado, evento.getZooId**Si no sale null); 
		return trabajadorResponse;
				
	}

	@Override
	public Trabajador signUp(Trabajador trabajador) throws UserCantCreateException {
			
		BCryptPasswordEncoder  passEncoder = new BCryptPasswordEncoder();
		String password = passEncoder.encode(trabajador.getPassword());		
		trabajador.setPassword(password);
		
		//Rol trabajadorRol = rolRepository.findByName("EMPLEADO");
		Rol trabajadorRol = rolRepository.findByName(RolEnum.EMPLEADO.name()); //TODO no funciona, deberia de
		System.out.println(trabajadorRol);
		
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

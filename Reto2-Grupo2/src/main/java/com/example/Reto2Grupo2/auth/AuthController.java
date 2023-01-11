package com.example.Reto2Grupo2.auth;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Reto2Grupo2.auth.exception.UserCantCreateException;
import com.example.Reto2Grupo2.auth.model.AuthRequestCliente;
import com.example.Reto2Grupo2.auth.model.AuthRequestEmple;
import com.example.Reto2Grupo2.auth.model.AuthResponse;
import com.example.Reto2Grupo2.auth.security.JwtTokenUtil;
import com.example.Reto2Grupo2.user.modelo.User;
import com.example.Reto2Grupo2.user.service.UserServiceImpl;


@RestController
@RequestMapping("api")
public class AuthController {

	@Autowired
	AuthenticationManager authManager;

	@Autowired
	JwtTokenUtil jwtUtil;

	@Autowired
	UserServiceImpl userService;

	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@RequestBody AuthRequestEmple request) {
		
		try {
			Authentication authentication = authManager
					.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

			User trabajadorUser = (User) authentication.getPrincipal();
						
			String accessToken = jwtUtil.generateAccessToken(trabajadorUser);
						
			AuthResponse response = new AuthResponse(trabajadorUser.getId(),trabajadorUser.getUsername(), accessToken);

			return ResponseEntity.ok().body(response);

		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	@PostMapping("/auth/signup/empleados")
	public ResponseEntity<?> signupEmpleado(@RequestBody AuthRequestEmple request){
// 		 solo esta creado en el caso de que funcione. Si no es posible que de 500
//		Trabajador trabajadorUser = new Trabajador(request.getEmail(), request.getPassword());
//		return new ResponseEntity<Integer>(trabajadorService.create(trabajadorUser), HttpStatus.CREATED);
		User empleado = new User(request.getUsername(), request.getPassword(), request.getZooId());
		
		try {
			userService.signupEmpleado(empleado);
		}catch (UserCantCreateException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
			// TODO que devuelva los datos del usuario creado ???
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PostMapping("/auth/signup/clientes")
	public ResponseEntity<?> signupCliente(@RequestBody AuthRequestCliente request){
// 		 solo esta creado en el caso de que funcione. Si no es posible que de 500
//		Trabajador trabajadorUser = new Trabajador(request.getEmail(), request.getPassword());
//		return new ResponseEntity<Integer>(trabajadorService.create(trabajadorUser), HttpStatus.CREATED);
		User cliente = new User(request.getUsername(), request.getPassword(), request.getEmail());
		
		try {
			userService.signupCliente(cliente);
		}catch (UserCantCreateException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
			// TODO que devuelva los datos del usuario creado ???
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
	

//	// utilizamos el /me por que vamos a coger el nuestro
//	@GetMapping("/auth/me")
//	public ResponseEntity<?> getUserInfo(Authentication authentication) {
//		// aqui podemos castearlo a UserDetails o User. El UserDetails es una interfaz,
//		// si lo casteamos a la interfaz no podremos sacar campos como la ID
//		User trabajadorDetails = (User) authentication.getPrincipal();
//
//
//		return ResponseEntity.ok().body(trabajadorDetails);
//	}

}
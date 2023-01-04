package com.example.Reto2Grupo2.auth;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Reto2Grupo2.auth.model.AuthRequest;
import com.example.Reto2Grupo2.auth.model.AuthResponse;
import com.example.Reto2Grupo2.auth.persistence.Trabajador;
import com.example.Reto2Grupo2.auth.security.JwtTokenUtil;
import com.example.Reto2Grupo2.trabajador.service.TrabajadorService;


@RestController
@RequestMapping("api")
public class AuthController {

	@Autowired
	AuthenticationManager authManager;

	@Autowired
	JwtTokenUtil jwtUtil;

	@Autowired
	TrabajadorService trabajadorService;

	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest request) {
		try {
			Authentication authentication = authManager
					.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

			Trabajador trabajadorUser = (Trabajador) authentication.getPrincipal();
			String accessToken = jwtUtil.generateAccessToken(trabajadorUser);
			AuthResponse response = new AuthResponse(trabajadorUser.getId(),trabajadorUser.getUsername(), accessToken);

			return ResponseEntity.ok().body(response);

		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	@PostMapping("/auth/signup")
	public ResponseEntity<?> signIn(@RequestBody AuthRequest request) {
		// TODO solo esta creado en el caso de que funcione. Si no es posible que de 500
//		Trabajador trabajadorUser = new Trabajador(request.getEmail(), request.getPassword());
//		return new ResponseEntity<Integer>(trabajadorService.create(trabajadorUser), HttpStatus.CREATED);
		
		Trabajador trabajador = new Trabajador(request.getUsername(), request.getPassword());
		trabajadorService.signUp(trabajador);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
	

	// utilizamos el /me por que vamos a coger el nuestro
	@GetMapping("/auth/me")
	public ResponseEntity<?> getUserInfo(Authentication authentication) {
		// aqui podemos castearlo a UserDetails o User. El UserDetails es una interfaz,
		// si lo casteamos a la interfaz no podremos sacar campos como la ID
		Trabajador trabajadorDetails = (Trabajador) authentication.getPrincipal();


		return ResponseEntity.ok().body(trabajadorDetails);
	}

}
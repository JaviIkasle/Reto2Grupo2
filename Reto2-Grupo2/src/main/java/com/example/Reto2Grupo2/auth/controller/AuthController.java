package com.example.Reto2Grupo2.auth.controller;



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
import com.example.Reto2Grupo2.auth.model.AuthResponse;
import com.example.Reto2Grupo2.auth.security.JwtTokenUtil;
import com.example.Reto2Grupo2.user.modelo.AuthRequestEmple;
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

}
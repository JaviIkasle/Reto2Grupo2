package com.example.Reto2Grupo2.cifrado;

import org.springframework.security.crypto.password.PasswordEncoder;

public class OurPassEncoder implements PasswordEncoder{
	
	//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public String encode(CharSequence rawPassword) {		
		return cifradoCustom(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String bbddPass) {
		// rawPassword == pass sin cifarr
		// encodedPassword == pass cifrada de la BBDD
		return cifradoCustom(rawPassword.toString()).equals(bbddPass);
	}

	
	
	private String cifradoCustom(String string) {
		String textoCifrado = "";
		
		
		
		
		
		
		
		
		
		
		return textoCifrado;
	}
}

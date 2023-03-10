package com.example.Reto2Grupo2.cifrados.RSA;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

	
	
	private String cifradoCustom(String rawPass) {
		String passString = rawPass;
		String passSHA =null;
		MessageDigest algoritmo;
		try {
			algoritmo = MessageDigest.getInstance("SHA"); // Algoritmo a usar
			algoritmo.reset(); // Limpiamos la instancia por si acaso
			byte dataBytes[] = passString.getBytes(); 
			algoritmo.update(dataBytes); // El mensaje que queremos cifrar
			byte resumen[] = algoritmo.digest(); // Generamos el resumen

			 passSHA = Hexadecimal(resumen);
			return passSHA;
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return passSHA;
	}
	
	// Convierte Array de Bytes en hexadecimal
	static String Hexadecimal(byte[] resumen) {
		String HEX = "";
		for (int i = 0; i < resumen.length; i++) {
			String h = Integer.toHexString(resumen[i] & 0xFF);
			if (h.length() == 1)
				HEX += "0";
			HEX += h;
		}
		return HEX.toUpperCase();
	}
}

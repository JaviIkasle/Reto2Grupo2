package com.example.Reto2Grupo2.email.modelo;

import java.security.SecureRandom;

public class GeneradorPass {

	 private final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
	 private final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
	 private final String NUMBER = "0123456789";
	 private final String OTHER_CHAR = "!@#$%&*()_+-=[]?";

	 private final String PASSWORD_ALLOW_BASE = CHAR_LOWER + CHAR_UPPER + NUMBER + OTHER_CHAR;
	 private SecureRandom random = new SecureRandom();
	 private final int Tamaño= 20;
	
	
	  @SuppressWarnings("unused")
	public String generatePassword() {
	        if (Tamaño < 1) throw new IllegalArgumentException();

	        StringBuilder sb = new StringBuilder(Tamaño);
	        for (int i = 0; i < Tamaño; i++) {
	            int rndCharAt = random.nextInt(PASSWORD_ALLOW_BASE.length());
	            char rndChar = PASSWORD_ALLOW_BASE.charAt(rndCharAt);
	            sb.append(rndChar);
	        }
	        
	        
	        return sb.toString();
	    }
}

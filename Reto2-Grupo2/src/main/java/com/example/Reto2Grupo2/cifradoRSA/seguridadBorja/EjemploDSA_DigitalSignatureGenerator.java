package com.example.Reto2Grupo2.cifradoRSA.seguridadBorja;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

/**
 * Esta clase genera un par de claves pública y privada para el algoritmo DS
 * (específico para Firmas Digitales) y las almacena en dos ficheros 
 */
public class EjemploDSA_DigitalSignatureGenerator {

	private static final String FICHERO_CLAVE_PUBLICA = "clave_publica.key";
	private static final String FICHERO_CLAVE_PRIVADA = "clave_privada.key";
	
	private static KeyPair generarClaves() throws NoSuchAlgorithmException {
		KeyPair ret = null;
		KeyPairGenerator keyPairGenerator  = KeyPairGenerator.getInstance("DSA"); 
		keyPairGenerator.initialize(512); //Admite 512, 768 o 1024
		ret = keyPairGenerator.generateKeyPair();
		return ret;
	}
	
	private static void guardarClaves(KeyPair claves) throws IOException {
		FileOutputStream fileOutputStream = new FileOutputStream(FICHERO_CLAVE_PUBLICA);
		fileOutputStream.write(claves.getPublic().getEncoded());
		fileOutputStream.close();
		
		fileOutputStream = new FileOutputStream(FICHERO_CLAVE_PRIVADA);
		fileOutputStream.write(claves.getPrivate().getEncoded());
		fileOutputStream.close();
	}
	
	public static void main(String[] args) {
		try {
			KeyPair claves = generarClaves();
			guardarClaves (claves);
			
			System.out.print("Ficheros de Clave generados...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

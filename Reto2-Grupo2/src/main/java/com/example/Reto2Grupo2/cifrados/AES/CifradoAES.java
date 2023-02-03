package com.example.Reto2Grupo2.cifrados.AES;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class CifradoAES {
	
	private static final String FILE_PATH_USER = "src\\main\\resources\\UserCredentials.dat";
	private static final String FILE_PATH_PASS = "src\\main\\resources\\PassCredentials.dat";
	private static final String CLAVE = "clave";
	
	private String user;
	private String pass;

	public CifradoAES() {

		user = "javier.bazdepa@elorrieta-errekamari.com";
		pass = "ryxtvdpcbzwepztn";

		cifrarUser(user);
		cifrarPass(pass);

	}

	private static byte[] salt = "esta es la salt!".getBytes();

	
	public static String cifrarUser(String mensaje) {
		
		
		String ret = null;
		KeySpec keySpec = null;
		SecretKeyFactory secretKeyFactory = null;
		FileOutputStream fileOutputStream = null;
		try {

			keySpec = new PBEKeySpec(CLAVE.toCharArray(), salt, 65536, 128); // AES-128
			secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] key = secretKeyFactory.generateSecret(keySpec).getEncoded();
			SecretKey privateKey = new SecretKeySpec(key, 0, key.length, "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			byte[] encodedMessage = cipher.doFinal(mensaje.getBytes());
			byte[] iv = cipher.getIV();

			byte[] combined = concatArrays(iv, encodedMessage);

			fileOutputStream = new FileOutputStream(FILE_PATH_USER);
			fileOutputStream.write(combined);

			ret = new String(encodedMessage);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	public static String cifrarPass(String mensaje) {
		
		String ret = null;
		KeySpec keySpec = null;
		SecretKeyFactory secretKeyFactory = null;
		FileOutputStream fileOutputStream = null;
		try {

			keySpec = new PBEKeySpec(CLAVE.toCharArray(), salt, 65536, 128); // AES-128
			secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] key = secretKeyFactory.generateSecret(keySpec).getEncoded();
			SecretKey privateKey = new SecretKeySpec(key, 0, key.length, "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			byte[] encodedMessage = cipher.doFinal(mensaje.getBytes());
			byte[] iv = cipher.getIV();

			byte[] combined = concatArrays(iv, encodedMessage);

			fileOutputStream = new FileOutputStream(FILE_PATH_PASS);
			fileOutputStream.write(combined);

			ret = new String(encodedMessage);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	private static String descifrarUser() {
		
		String ret = null;
		try {
			File fichero = new File(FILE_PATH_USER);
			byte[] fileContent = Files.readAllBytes(fichero.toPath());
			KeySpec keySpec = null;
			SecretKeyFactory secretKeyFactory = null;

			keySpec = new PBEKeySpec(CLAVE.toCharArray(), salt, 65536, 128); // AES-128
			secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] key = secretKeyFactory.generateSecret(keySpec).getEncoded();
			SecretKey privateKey = new SecretKeySpec(key, 0, key.length, "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec ivParam = new IvParameterSpec(Arrays.copyOfRange(fileContent, 0, 16)); // La IV esta aqui
			cipher.init(Cipher.DECRYPT_MODE, privateKey, ivParam);
			byte[] decodedMessage = cipher.doFinal(Arrays.copyOfRange(fileContent, 16, fileContent.length));
			ret = new String(decodedMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	private static String descifrarPass() {
		
		String ret = null;
		try {
			File fichero = new File(FILE_PATH_PASS);
			byte[] fileContent = Files.readAllBytes(fichero.toPath());
			KeySpec keySpec = null;
			SecretKeyFactory secretKeyFactory = null;

			keySpec = new PBEKeySpec(CLAVE.toCharArray(), salt, 65536, 128); // AES-128
			secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] key = secretKeyFactory.generateSecret(keySpec).getEncoded();
			SecretKey privateKey = new SecretKeySpec(key, 0, key.length, "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec ivParam = new IvParameterSpec(Arrays.copyOfRange(fileContent, 0, 16)); // La IV esta aqui
			cipher.init(Cipher.DECRYPT_MODE, privateKey, ivParam);
			byte[] decodedMessage = cipher.doFinal(Arrays.copyOfRange(fileContent, 16, fileContent.length));
			ret = new String(decodedMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	private static byte[] concatArrays(byte[] array1, byte[] array2) {
		byte[] ret = new byte[array1.length + array2.length];
		System.arraycopy(array1, 0, ret, 0, array1.length);
		System.arraycopy(array2, 0, ret, array1.length, array2.length);
		return ret;
	}
	
	public String cojerCredencialUser() {
		return descifrarUser();
	}
	
	public String cojerCredencialPass() {
		return descifrarPass();
	}

}

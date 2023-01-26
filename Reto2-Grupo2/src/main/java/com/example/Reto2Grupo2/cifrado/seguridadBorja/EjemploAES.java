package com.example.Reto2Grupo2.cifrado.seguridadBorja;

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

/**
 * <b>Criptografía Simétrica (Clave Secreta)</b> <br/>
 * <br/>
 * 
 * Esta clase permite cifrar un texto mediante una <b>clave secreta</b> y lo
 * guarda en un fichero. La única forma de descifrar el texto es mediante dicha
 * clave, que tanto el <u>emisor</u> como el <u>receptor</u> la deben conocer.
 * 
 * En este caso vamos a utilizar:
 * <ul>
 * <li>El algoritmo AES</li>
 * <li>El modo CBC: Existen dos, el ECB que es sencillo, y el CBC que necesita
 * un vector de inicialización(IV)</li>
 * <li>El padding PKCS5Padding (128): Si el mensaje no es múltiplo de la
 * longitud del algoritmo se necesita un relleno.</li>
 * </ul>
 * AES solo admite <b>tamaños de clave</b> de 16, 24 o 32 bytes. Se debe
 * proporcionar exactamente ese tamaño de clave o usar una
 * <b>"salt"(Semilla)</b>. En criptografía, un salt es un dato aleatorio que se
 * usa como una entrada adicional de cifrado. En este caso, vamos a utilizar
 * salt para crear una clave de exactamente 16 bytes. <br/>
 * <br/>
 * Generalmente un salt se genera aleatoriamente cuando creas la clave, así que
 * <u>necesitas guardar</u> la clave y su salt para poder cifrar y descifrar.
 */
public class EjemploAES {

	// Fíjate que el String es de exactamente 16 bytes
	private static byte[] salt = "esta es la salt!".getBytes();

	private static final String FILE_PATH = "EjemploAES.dat";

	/**
	 * Cifra un texto con AES, modo CBC y padding PKCS5Padding (simétrica) y lo
	 * retorna
	 * 
	 * @param clave   La clave del usuario
	 * @param mensaje El mensaje a cifrar
	 * @return Mensaje cifrado
	 */
	public String cifrarTexto(String clave, String mensaje) {
		String ret = null;
		KeySpec keySpec = null;
		SecretKeyFactory secretKeyFactory = null;
		FileOutputStream fileOutputStream = null;
		try {

			// Creamos un SecretKey usando la clave + salt
			keySpec = new PBEKeySpec(clave.toCharArray(), salt, 65536, 128); // AES-128
			secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] key = secretKeyFactory.generateSecret(keySpec).getEncoded();
			SecretKey privateKey = new SecretKeySpec(key, 0, key.length, "AES");

			// Creamos un Cipher con el algoritmos que vamos a usar
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			byte[] encodedMessage = cipher.doFinal(mensaje.getBytes()); // Mensaje cifrado !!!
			byte[] iv = cipher.getIV(); // vector de inicializaci�n por modo CBC

			// Guardamos el mensaje codificado: IV (16 bytes) + Mensaje
			byte[] combined = concatArrays(iv, encodedMessage);

			fileOutputStream = new FileOutputStream(FILE_PATH);
			fileOutputStream.write(combined);

			ret = new String(encodedMessage);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * Descifra un texto con AES, modo CBC y padding PKCS5Padding (simetrica) y lo
	 * retorna
	 * 
	 * @param clave La clave del usuario
	 */
	
	private String descifrarTexto(String clave) {
		String ret = null;
		try {
			// Fichero leido
			File fichero = new File(FILE_PATH);
			byte[] fileContent = Files.readAllBytes(fichero.toPath());
			KeySpec keySpec = null;
			SecretKeyFactory secretKeyFactory = null;

			// Creamos un SecretKey usando la clave + salt
			keySpec = new PBEKeySpec(clave.toCharArray(), salt, 65536, 128); // AES-128
			secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] key = secretKeyFactory.generateSecret(keySpec).getEncoded();
			SecretKey privateKey = new SecretKeySpec(key, 0, key.length, "AES");

			// Creamos un Cipher con el algoritmos que vamos a usar
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

	/**
	 * Retorna una concatenacion de ambos arrays
	 * 
	 * @param array1
	 * @param array2
	 * @return Concatenaci�n de ambos arrays
	 */
	private byte[] concatArrays(byte[] array1, byte[] array2) {
		byte[] ret = new byte[array1.length + array2.length];
		System.arraycopy(array1, 0, ret, 0, array1.length);
		System.arraycopy(array2, 0, ret, array1.length, array2.length);
		return ret;
	}

	public static void main(String[] args) {
		EjemploAES ejemploAES = new EjemploAES();
		String mensaje = "Mensaje super secreto";
		System.out.println("Mensaje Original! -> " + mensaje);
		System.out.println("-----------");
		String mensajeCifrado = ejemploAES.cifrarTexto("Clave", mensaje);
		System.out.println("Mensaje Cifrado! -> " + mensajeCifrado);
		System.out.println("-----------");
		System.out.println("Mensaje Descifrado! -> " + ejemploAES.descifrarTexto("Clave"));
		System.out.println("-----------");
	}
}
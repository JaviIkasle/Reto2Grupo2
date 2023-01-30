package com.example.Reto2Grupo2.cifradoRSA.seguridadBorja;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Firmamos un texto con la clave privada y empleamos la clave publica para
 * comprobar si es el mismo o ha sufrido alteraciones.
 */
public class EjemploDSA {

	private static final String FICHERO_CLAVE_PUBLICA = "clave_publica.key";
	private static final String FICHERO_CLAVE_PRIVADA = "clave_privada.key";

	private static PrivateKey getClavePrivada() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		PrivateKey ret = null;
		File ficheroPrivada = new File(FICHERO_CLAVE_PRIVADA);
		byte[] clavePrivada = Files.readAllBytes(ficheroPrivada.toPath());
		KeyFactory keyFactory = KeyFactory.getInstance("DSA");
		EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(clavePrivada);
		ret = keyFactory.generatePrivate(privateKeySpec);
		return ret;
	}

	private static PublicKey getClavePublica() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		PublicKey ret = null;
		File ficheroPublica = new File(FICHERO_CLAVE_PUBLICA);
		byte[] clavePublica = Files.readAllBytes(ficheroPublica.toPath());
		KeyFactory keyFactory = KeyFactory.getInstance("DSA");
		EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(clavePublica);
		ret = keyFactory.generatePublic(publicKeySpec);
		return ret;
	}

	public static void main(String[] args) {

		String mensaje = "Hola, Mundo!";
		String mensajeModificado = "Hola, mundo!";

		try {

			// Firmamos el mensaje
			Signature signature = Signature.getInstance("DSA");
			signature.initSign(getClavePrivada());
			signature.update(mensaje.getBytes());
			byte[] firma = signature.sign();

			// Vamos a verificar
			signature.initVerify(getClavePublica());
			signature.update(mensajeModificado.getBytes());

			if (signature.verify(firma)) {
				System.out.print("Mensaje verificado!");
			} else {
				System.out.print("Error - Mensaje no fiable!");
			}

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

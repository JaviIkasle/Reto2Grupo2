package com.example.Reto2Grupo2.cifrado;

import java.io.File;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

public class CifradoRSA {
	
	 public static final String PUBLIC_KEY_FILE_PATH = "RSA_Public.key";
	 private static final String PRIVATE_KEY_FILE_PATH = "RSA_Private.key";
	 
   /**
    * Cifra un texto con RSA, modo ECB y padding PKCS1Padding (asim�trica) y lo
    * retorna
    * 
    * @param mensaje El mensaje a cifrar
    * @return El mensaje cifrado
    */
   public byte[] cifrarTexto(String mensaje) {
       byte[] encodedMessage = null;
       try {
           // Clave publica
   		File ficheroPublica = new File(PUBLIC_KEY_FILE_PATH);
   		byte[] clavePublica = Files.readAllBytes(ficheroPublica.toPath());
           System.out.println("Tamanio -> " + clavePublica.length + " bytes");

           KeyFactory keyFactory = KeyFactory.getInstance("RSA");
           X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(clavePublica);
           PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

           Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
           cipher.init(Cipher.ENCRYPT_MODE, publicKey);
           encodedMessage = cipher.doFinal(mensaje.getBytes());
       } catch (Exception e) {
           e.printStackTrace();
       }
            
       return encodedMessage;
   }

   /**
    * Descifra un texto con RSA, modo ECB y padding PKCS1Padding (asim�trica) y lo
    * retorna
    * 
    * @param mensaje El mensaje a descifrar
    * @return El mensaje descifrado
    */
   public byte[] descifrarTexto(byte[] mensaje) {
       byte[] decodedMessage = null;
       try {
           // Clave publica
   		File ficheroPrivada = new File(PRIVATE_KEY_FILE_PATH);
   		byte[] clavePrivada = Files.readAllBytes(ficheroPrivada.toPath());
           System.out.println("Tamanio -> " + clavePrivada.length + " bytes");

           KeyFactory keyFactory = KeyFactory.getInstance("RSA");
           PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(clavePrivada);
           PrivateKey privateKey = keyFactory.generatePrivate(pKCS8EncodedKeySpec);

           Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
           cipher.init(Cipher.DECRYPT_MODE, privateKey);
           decodedMessage = cipher.doFinal(mensaje);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return decodedMessage;
   }

   public static void main(String[] args) {
	   CifradoRSA cifradoRSA = new CifradoRSA();
       byte[] mensajeCifrado = cifradoRSA.cifrarTexto("Mensaje super secreto");
       System.out.println("Cifrado! -> " + new String(mensajeCifrado));
       System.out.println("Tamanio -> " + mensajeCifrado.length + " bytes");
       System.out.println("-----------");
       byte[] mensajeDescifrado = cifradoRSA.descifrarTexto(mensajeCifrado);
       System.out.println("Descifrado! -> " + new String(mensajeDescifrado));
       System.out.println("-----------");
   }

}

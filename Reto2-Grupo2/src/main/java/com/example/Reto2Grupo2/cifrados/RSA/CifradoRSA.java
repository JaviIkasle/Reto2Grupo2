package com.example.Reto2Grupo2.cifrados.RSA;

import java.io.File;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

import org.springframework.stereotype.Service;

@Service("cifradoRSA")
public class CifradoRSA {
	
	 public static final String PUBLIC_KEY_FILE_PATH = "Public_KEY.key";
	 private static final String PRIVATE_KEY_FILE_PATH = "Private_KEY.key";
	 
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

}

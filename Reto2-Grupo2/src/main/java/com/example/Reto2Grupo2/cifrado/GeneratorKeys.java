package com.example.Reto2Grupo2.cifrado;

import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class GeneratorKeys {
	
	 private static final String PUBLIC_KEY_FILE_PATH = "RSA_Public.key";
	 private static final String PRIVATE_KEY_FILE_PATH = "RSA_Private.key";
	 
   /**
    * Genera el fichero con la clave privada
    */
   public void generateKeys() {

       KeyPairGenerator generator;
       try {
           generator = KeyPairGenerator.getInstance("RSA");
           generator.initialize(1024); // Inicializamos el tamanio a 1024 bits
           KeyPair keypair = generator.generateKeyPair();
           PublicKey publicKey = keypair.getPublic(); // Clave Publica
           PrivateKey privateKey = keypair.getPrivate(); // Clave Privada

           // Publica
           X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
           FileOutputStream fileOutputStream = new FileOutputStream(PUBLIC_KEY_FILE_PATH);
           fileOutputStream.write(x509EncodedKeySpec.getEncoded());
           fileOutputStream.close();

           // Privada
           PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
           fileOutputStream = new FileOutputStream(PRIVATE_KEY_FILE_PATH);
           fileOutputStream.write(pKCS8EncodedKeySpec.getEncoded());
           fileOutputStream.close();
                                 
       } catch (Exception e) {
           e.printStackTrace();
       }
   }


}

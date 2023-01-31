package com.example.Reto2Grupo2.cifradoRSA.seguridadBorja;

import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * <b>Criptografia Asimetrica (Clave publica) - Generador Claves</b> <br/>
 * <br/>
 * 
 * En un <b>Cifrado asimetrico</b> hay dos participantes: el emisor y el
 * receptor. Los pasos a seguir son:
 * 
 * <ul>
 * <li>Generar una <b>clave publica</b> y otra <b>privada</b>. La clave publica
 * se env�a al emisor</li>
 * <li>El emisor <u>cifra</u> los datos con <b>clave publica</b> y se envian al
 * receptor</li>
 * <li>El receptor <u>descifra</u> los datos con <b>clave privada</b></li>
 * </ul>
 * 
 * En este caso, el algoritmo utilizado es el <b>RSA</b>. Para guardar una clave
 * en un archivo, se debe crear un <u>objeto de especificacion de clave</u>. La
 * clase para crear la especificaci�n de clave privada es
 * <u>PKCS8EncodedKeySpec</u>, y para la p�blica es <u>X509EncodedKeySpec</u>.
 */
public class EjemploRSA_KeyGenerator {

	 private static final String PUBLIC_KEY_FILE_PATH = "EjemploRSA_Public.key";
	 private static final String PRIVATE_KEY_FILE_PATH = "EjemploRSA_Private.key";
	 
    /**
     * Genera el fichero con la clave privada
     */
    public void generatePrivateKey() {

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
            //e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EjemploRSA_KeyGenerator ejemploRSA_KeyGenerator = new EjemploRSA_KeyGenerator();
        ejemploRSA_KeyGenerator.generatePrivateKey();
        System.out.println("Ficheros de Clave Generados!");
    }
}

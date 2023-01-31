package com.example.Reto2Grupo2.cifradoRSA.seguridadBorja;

import java.io.File;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * <b>Criptografia Asimetrica (Clave publica) - Generador Clave Publica</b>
 * <br/>
 * <br/>
 * 
 * En un <b>Cifrado asimetrico</b> hay dos participantes: el emisor y el
 * receptor. Los pasos a seguir son:
 * 
 * <ul>
 * <li>Generar una <b>clave publica</b> y otra <b>privada</b>. La clave publica
 * se envia al emisor</li>
 * <li>El emisor <u>cifra</u> los datos con <b>clave publica</b> y se envian al
 * receptor</li>
 * <li>El receptor <u>descifra</u> los datos con <b>clave privada</b></li>
 * </ul>
 * 
 * Esta clase genera primero cifra un mensaje con la <b>clave publica</b>. A
 * continuaci�n, lo descifra mediante la <b>clave privada</b>. En este caso
 * vamos a utilizar:
 * 
 * <ul>
 * <li>El algoritmo RSA</li>
 * <li>El modo ECB: Existen dos, el ECB que es sencillo, y el CBC que necesita
 * un vector de inicializacion(IV)</li>
 * <li>El padding PKCS1Padding: Si el mensaje no es multiplo de la longitud del
 * algoritmo se indica un relleno.</li>
 * </ul>
 */
public class EjemploRSA {

	 private static final String PUBLIC_KEY_FILE_PATH = "EjemploRSA_Public.key";
	 private static final String PRIVATE_KEY_FILE_PATH = "EjemploRSA_Private.key";
	 
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
    private byte[] descifrarTexto(byte[] mensaje) {
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
        EjemploRSA ejemploRSA = new EjemploRSA();
        byte[] mensajeCifrado = ejemploRSA.cifrarTexto("Mensaje super secreto");
        System.out.println("Cifrado! -> " + new String(mensajeCifrado));
        System.out.println("Tamanio -> " + mensajeCifrado.length + " bytes");
        System.out.println("-----------");
        byte[] mensajeDescifrado = ejemploRSA.descifrarTexto(mensajeCifrado);
        System.out.println("Descifrado! -> " + new String(mensajeDescifrado));
        System.out.println("-----------");
    }
}

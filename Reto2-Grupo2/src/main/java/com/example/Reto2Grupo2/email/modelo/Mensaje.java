package com.example.Reto2Grupo2.email.modelo;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.example.Reto2Grupo2.cifradoAES.CifradoAES;

public class Mensaje {
	
	private String user = null;
	private String pass = null;

	private String smtp_host = null;
	private int smtp_port = 0;

	
	@SuppressWarnings("unused")
	public Mensaje() {
	}
	
	public Mensaje(String user, String pass, String host, int port) {
		this.user = user;
		this.pass = pass;
		this.smtp_host = host;
		this.smtp_port = port;
	}
	
	private void cambioDePass(String to, String subject, String text) throws MessagingException {
		
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", smtp_host);
		properties.put("mail.smtp.port", smtp_port);
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.ssl.trust", smtp_host);
		properties.put("mail.imap.partialfetch", false);
		
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, pass);
			}
		});
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(user));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user));
		message.setSubject(subject);
		message.setContent(text, "text/html; charset=utf-8");

		Multipart multipart = new MimeMultipart();
		
		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(text, "text/html");
		multipart.addBodyPart(mimeBodyPart);

		message.setContent(multipart);

		Transport.send(message);

		
	}
	
	
	public void enviarMensaje() {
		
		CifradoAES cifrado = new CifradoAES();
		
		String to = "javier.bazdepa@elorrieta-errekamari.com";
		String subject = "Cambio de contraseña de cuenta en WildProject";
		String text = "Tu contraseña se ha cambiado satisfactoriamente, ingresa en la app con tu nueva contraseña.";

	
		Mensaje emailService = new Mensaje(cifrado.cojerCredencialUser(), cifrado.cojerCredencialPass(), "smtp.gmail.com", 465);
		try {
			emailService.cambioDePass(to, subject, text);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}

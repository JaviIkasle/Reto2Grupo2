package com.example.Reto2Grupo2.user.modelo;



public class EmailService {

	private String user = null;
	private String pass = null;

	// DNS Host + SMTP Port
	private String smtp_host = null;
	private int smtp_port = 0;

	@SuppressWarnings("unused")
	private EmailService() {}

	public EmailService(String user, String pass, String smtp_host, int smtp_port) {
		super();
		this.user = user;
		this.pass = pass;
		this.smtp_host = smtp_host;
		this.smtp_port = smtp_port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getSmtp_host() {
		return smtp_host;
	}

	public void setSmtp_host(String smtp_host) {
		this.smtp_host = smtp_host;
	}

	public int getSmtp_port() {
		return smtp_port;
	}

	public void setSmtp_port(int smtp_port) {
		this.smtp_port = smtp_port;
	}

	@Override
	public String toString() {
		return "EmailService [user=" + user + ", pass=" + pass + ", smtp_host=" + smtp_host + ", smtp_port=" + smtp_port
				+ "]";
	}
	
	public void sendMail(String receiver, String subject, String text, String cc) /*throws MessagingException*/ {

		// Mail properties
//		Properties properties = new Properties();
//		properties.put("mail.smtp.auth", true);
//		properties.put("mail.smtp.starttls.enable", "true");
//		properties.put("mail.smtp.host", smtp_host);
//		properties.put("mail.smtp.port", smtp_port);
//		properties.put("mail.smtp.ssl.enable", "true");
//		properties.put("mail.smtp.auth", "true");
//		properties.put("mail.smtp.ssl.trust", smtp_host);
//		properties.put("mail.imap.partialfetch", false);
//
//		// Authenticator knows how to obtain authentication for a network connection.
//		Session session = Session.getInstance(properties, new Authenticator() {
//			@Override
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(user, pass);
//			}
//		});
//
//		// MIME message to be sent
//		Message message = new MimeMessage(session);
//		message.setFrom(new InternetAddress(user));
//		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver)); // Ej: receptor@gmail.com
//		message.setSubject(subject); // Asunto del mensaje
//		//message.addRecipients(Message.RecipientType.CC, cc);
//
//		// A mail can have several parts
//		Multipart multipart = new MimeMultipart();
//
//		// A message part (the message, but can be also a File, etc...)
//		MimeBodyPart mimeBodyPart = new MimeBodyPart();
//		mimeBodyPart.setContent(text, "text/html");
//		multipart.addBodyPart(mimeBodyPart);
//
//		// Adding up the parts to the MIME message
//		message.setContent(multipart);
//
//		// And here it goes...
//		Transport.send(message);
	}
	
}

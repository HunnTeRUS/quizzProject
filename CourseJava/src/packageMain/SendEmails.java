package packageMain;

import org.apache.commons.mail.*;
import com.sun.mail.smtp.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.security.*;
import java.util.*;
import javax.net.ssl.*;
import javax.swing.JOptionPane;

public class SendEmails {
	SimpleEmail sendEmail = new SimpleEmail();

	static SendEmails objEmail = new SendEmails();

	public void enviandoEmail(String emailPerson, String code) {
		final String username = "otaviocelestinos@gmail.com";
		final String password = "otavio159";

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("otavio@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailPerson));
			message.setSubject("App Hunter");
			message.setText("Olá!! Este é seu código para iniciar em nosso app como administrador: " + code);

			Transport.send(message);

			System.out.println("Pronto!");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}


  
    
package com.WorkMerge.services;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.WorkMerge.entities.Job;
import com.WorkMerge.repositories.JobRepository;

@Service
public class NotificationService {

	@Autowired
	private JavaMailSender mailsender;
	
	@Autowired
	private JobRepository jobRepository;
	
	@Value("${spring.mail.username}")
	private String mailFrom;
	
	@Value("${spring.mail.password}")
	private String mailPassword;
	
	public boolean notificar(String id, String mailTo) {
		
		try {
			
			Job job = jobRepository.getById(id);
			
			if (job != null ) {
				enviarSimpleCliente(job, mailTo);
				enviarHTMLCliente(job, mailTo);
				
				return true;
			} else {
				return false;
			}
			
		} catch(Exception e) {
			System.out.println("ERROR con " + e.getMessage());
			return false;
		}
		
	}
	
	@Async
	private void enviarSimpleCliente(Job job, String mailTo) {
		
		SimpleMailMessage mensaje = new SimpleMailMessage();

		mensaje.setTo(mailTo);
		mensaje.setFrom(mailFrom);
		mensaje.setSubject("POSTULACIÓN PARA TRABAJO");
		mensaje.setText("Te has postulado para este trabajo " + job.getTitle() + ". Muchas gracias");

		mailsender.send(mensaje);
	}
	
	@Async
	private void enviarHTMLCliente(Job job, String mailTo) {

		Properties prop = new Properties();
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 587);
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailFrom, mailPassword);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mailFrom));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));
			message.setSubject("Te postulaste para este trabajo");

			String msg = "<h1> Te postulaste para este trabajo : " + job.getTitle() + "</h1>" + 
						 "<h2>" + job.getDescription() + ".</h2>" + 
						 "<p>Muchas gracias. </p>";
			
			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setContent(msg, "text/html");

			// DESCOMENTAR PARA ENVIAR ARCHIVOS DESDE EL SERVIDOR O BUCKET
			// MimeBodyPart attachmentBodyPart = new MimeBodyPart();
			// attachmentBodyPart.attachFile(new File("pom.xml"));

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(mimeBodyPart);
			// multipart.addBodyPart(attachmentBodyPart);

			message.setContent(multipart);

			Transport.send(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}

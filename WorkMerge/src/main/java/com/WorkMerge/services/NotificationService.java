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

import com.WorkMerge.entities.Client;
import com.WorkMerge.entities.Job;
import com.WorkMerge.repositories.ClientRepository;
import com.WorkMerge.repositories.JobRepository;

@Service
public class NotificationService {

	@Autowired
	private JavaMailSender mailsender;
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Value("${spring.mail.username}")
	private String mailFrom;
	
	@Value("${spring.mail.password}")
	private String mailPassword;
	
	public boolean notificar(String idJob, String idCli, String mailCli, String mailCon) {
		
		try {
			
			Job job = jobRepository.getById(idJob);
			Client client = clientRepository.getById(idCli);
			
			
			if (job != null ) {
				//enviarSimpleCliente(job, mailTo);
				enviarHTMLCliente(job, mailCli);
				enviarHTMLEmpresa(client, job, "maximongelos@gmail.com");
				
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
			message.setSubject("Postulación exitosa!!");

			String msg = "<h1>Te acabas de postular al empleo de " + job.getTitle() + ".</h1>" + 
						 "<h2>Insiste y sigue aplicando a más vacantes para encontrar tu próximo empleo.</h2>";
			
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
	
	@Async
	private void enviarHTMLEmpresa(Client client, Job job, String mailTo) {

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
			message.setSubject("Se han postulado a un anuncio!!");

			String msg = "<h2>" + client.getCurriculum().getName() + " " + client.getCurriculum().getSurname() + " se ha postulado al empleo de " + job.getTitle() + ".</h2>" + 
						 "<h4><ins>Datos de postulante:</ins></h4>" +
						 "<p> *Fecha de nacimiento: " + client.getCurriculum().getBirthday() + "</p>" +
						 "<p> *DNI: " + client.getCurriculum().getDni() + "</p>" +
						 "<p> *Email: " + client.getEmail() + "</p>" +
						 "<p> *Telefono: " + client.getCurriculum().getPhone() + "</p>" +
						 "<p> *Dirección: " + client.getCurriculum().getAddress() + "</p>" +
						 "<p> *Ciudad: " + client.getCurriculum().getCity()+ "</p>" +
						 "<p> *Nacionalidad: " + client.getCurriculum().getNationality() + "</p>" +
						 "<p> *Educación: " + client.getCurriculum().getEducation() + "</p>" +
						 "<p> *Ultima experiencia laboral: " + client.getCurriculum().getWorkexperience()+ "</p>" +
						 "<p> *Habilidades: " + client.getCurriculum().getSkills() + "</p>" +
						 "<p> *Idioma: " + client.getCurriculum().getLanguage() + "</p>" +
						 "<p> *Genero: " + client.getCurriculum().getGender() + "</p>" ;
			
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

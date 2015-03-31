package net.etfbl.muzickagroznica.gateway;

import java.util.List;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import net.etfbl.muzickagroznica.model.entities.User;

public class EmailGateway {

	private String hostname;
	private String defaultFrom;
	
	
	
	public String getHostname() {
		return hostname;
	}



	public void setHostname(String hostname) {
		this.hostname = hostname;
	}



	public String getDefaultFrom() {
		return defaultFrom;
	}



	public void setDefaultFrom(String defaultFrom) {
		this.defaultFrom = defaultFrom;
	}


	private Properties properties;

	public EmailGateway() {
		// TODO Auto-generated constructor stub
		properties = System.getProperties();
		properties.setProperty("mail.smtp.host", hostname);
	}
	
	public void sendEmail(String to, String subject, String text){
		Session session = Session.getDefaultInstance(properties);
		
		try{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(defaultFrom));
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			
			message.setSubject(subject);
			
			message.setText(text);
			
			Transport.send(message);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	public void sendEmailsToUsers(List<User> users, String subject, String text){
		for(User u : users){
			sendEmail(u.getEmail(), subject, text);
		}
	}

}

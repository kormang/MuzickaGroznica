package net.etfbl.muzickagroznica.gateway;

import java.util.List;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import net.etfbl.muzickagroznica.model.entities.User;

public class EmailGateway {


	private Properties properties;
	private String password;
	private String userEmail;
	private String defualtFrom;


	public String getDefualtFrom() {
		return defualtFrom;
	}


	public void setDefualtFrom(String defualtFrom) {
		this.defualtFrom = defualtFrom;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Properties getProperties() {
		return properties;
	}


	public void setProperties(Properties properties) {
		this.properties = properties;
	}


	public EmailGateway() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void sendEmail(String to, String subject, String text){
		
        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                  protected PasswordAuthentication getPasswordAuthentication() {
                      return new PasswordAuthentication(userEmail, password);
                  }
                });
		
		try{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(defualtFrom));
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			
			message.setSubject(subject);
			
			message.setText(text, "UTF-8");
			
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

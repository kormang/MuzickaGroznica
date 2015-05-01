package net.etfbl.muzickagroznica.gateway;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;

import net.etfbl.muzickagroznica.model.entities.User;
import net.etfbl.muzickagroznica.util.StandardUtil;

public class EmailGateway {

	private Properties properties;
	private String password;
	private String userEmail;
	private String defaultFrom;
	
	protected ExecutorService asyncExecutor;
	
	

	public ExecutorService getAsyncExecutor() {
		return asyncExecutor;
	}


	public void setAsyncExecutor(ExecutorService asyncExecutor) {
		this.asyncExecutor = asyncExecutor;
	}


	public String getDefaultFrom() {
		return defaultFrom;
	}


	public void setDefaultFrom(String defaultFrom) {
		this.defaultFrom = defaultFrom;
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
	}
	
	protected void doSendEmail(String to, String subject, String text){
		Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                  protected PasswordAuthentication getPasswordAuthentication() {
                      return new PasswordAuthentication(userEmail, password);
                  }
                });
		
		try{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(defaultFrom));
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			
			message.setSubject(subject);
			
			message.setText(text, "UTF-8");
			
			Transport.send(message);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void sendEmail(final String to, final String subject, final String text){
	
        asyncExecutor.execute(new Runnable() {
			
			@Override
			public void run() {
				EmailGateway.this.doSendEmail(to, subject, text);
				
			}
		});
		
	}
	
	public void sendEmailsToUsers(List<User> users, String subject, String text){
		
        asyncExecutor.execute(new Runnable() {
			
			@Override
			public void run() {
				for(User u : users){
					doSendEmail(u.getEmail(), subject, text);
				}
			}
		});
		

	}

}

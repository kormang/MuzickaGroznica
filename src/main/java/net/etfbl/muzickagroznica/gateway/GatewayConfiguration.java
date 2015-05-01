package net.etfbl.muzickagroznica.gateway;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.etfbl.muzickagroznica.util.StandardUtil;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

	public GatewayConfiguration() {
		// TODO Auto-generated constructor stub
	}
	
	@Bean
	public EmailGateway emailGateway(){
		
		EmailGateway eg = new EmailGateway();
		
		eg.setDefaultFrom(StandardUtil.getProperties().getProperty("gateway.email.mail"));
		eg.setUserEmail(StandardUtil.getProperties().getProperty("gateway.email.mail"));
		eg.setPassword(StandardUtil.getProperties().getProperty("gateway.email.password"));

		Properties properties = new Properties();
		properties.setProperty("mail.smtp.host", StandardUtil.getProperties().getProperty("gateway.email.props.mail.smtp.host"));
		properties.setProperty("mail.smtp.port", StandardUtil.getProperties().getProperty("gateway.email.props.mail.smtp.port"));
		properties.setProperty("mail.smtp.auth", StandardUtil.getProperties().getProperty("gateway.email.props.mail.smtp.auth"));
		properties.setProperty("mail.smtps.host", StandardUtil.getProperties().getProperty("gateway.email.props.mail.smtps.host"));
		properties.setProperty("mail.smtp.starttls.enable", StandardUtil.getProperties().getProperty("gateway.email.props.mail.smtp.starttls.enable"));
		
		eg.setProperties(properties);
		
		eg.setAsyncExecutor(Executors.newCachedThreadPool());
		
		
		return eg;
	}

}

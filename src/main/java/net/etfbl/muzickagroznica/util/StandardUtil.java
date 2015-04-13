package net.etfbl.muzickagroznica.util;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Properties;

//import java.time.LocalDateTime;
//import java.time.ZoneId;

public class StandardUtil {

	public StandardUtil() {
		// TODO Auto-generated constructor stub
	}

	public static java.util.Date now(){
		return java.sql.Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
		//return java.util.Date.from(new java.util.Date().toInstant());
	}
	
	public static java.util.Date fromLocalDateTime(LocalDateTime ldt){
		return java.util.Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	static {
		properties = new Properties();
		loadProperties();
	}

	static private Properties properties;
	
	
	
	static public Properties getProperties() {
		return properties;
	}


	static private void loadProperties(){
		try {
		
			properties.load(
				Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("muzickagroznica.properties")
			);
			
		} catch (Exception ex){
			throw new RuntimeException("Standard properties file can not be read.", ex);
		}
	}



	static public File getAvatarUploadDir(){
		return new File(properties.getProperty("avatarUploadDir"));
	}
	
	static public File getContentUploadDir(){
		return new File(properties.getProperty("contentUploadDir"));
	}
	
}

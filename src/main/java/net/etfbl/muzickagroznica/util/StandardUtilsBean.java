package net.etfbl.muzickagroznica.util;

import java.io.File;
import java.util.Properties;


public class StandardUtilsBean {

	public StandardUtilsBean() {
		properties = new Properties();
		loadProperties();
	}

	private Properties properties;
	
	
	
	public Properties getProperties() {
		return properties;
	}


	private void loadProperties(){
		try {
		
			properties.load(
				Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("muzickagroznica.properties")
			);
			
		} catch (Exception ex){
			throw new RuntimeException("Standard properties file can not be read.", ex);
		}
	}



	public File getAvatarUploadDir(){
		return new File(properties.getProperty("avatarUploadDir"));
	}
	
	public File getContentUploadDir(){
		return new File(properties.getProperty("contentUploadDir"));
	}
	
	
}

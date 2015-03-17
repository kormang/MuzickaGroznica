package net.etfbl.muzickagroznica.util;

//import java.time.LocalDateTime;
//import java.time.ZoneId;

public class StandardUtil {

	public StandardUtil() {
		// TODO Auto-generated constructor stub
	}

	public static java.util.Date now(){
		//return java.sql.Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
		return java.util.Date.from(new java.util.Date().toInstant());
	}
	
}

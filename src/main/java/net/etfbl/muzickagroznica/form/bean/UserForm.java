package net.etfbl.muzickagroznica.form.bean;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class UserForm {

	public UserForm() {
		// TODO Auto-generated constructor stub
	}
	
	@NotEmpty
	@Pattern(regexp = "[^@#/\\\\]{6,}")
	private String username;
	
	@NotEmpty
	@Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}")
	private String rawPassword;
	
	@NotEmpty
	private String firstName;
	
	@NotEmpty
	private String lastName;
	
	@Pattern(regexp = "[0-9]{13}")
	private String jmb;
	
	@NotEmpty
	@Email
	private String email;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRawPassword() {
		return rawPassword;
	}
	public void setRawPassword(String rawPassword) {
		this.rawPassword = rawPassword;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getJmb() {
		return jmb;
	}
	public void setJmb(String jmb) {
		this.jmb = jmb;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}

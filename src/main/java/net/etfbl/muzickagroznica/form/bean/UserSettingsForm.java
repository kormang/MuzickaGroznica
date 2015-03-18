package net.etfbl.muzickagroznica.form.bean;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class UserSettingsForm {

	public UserSettingsForm() {
		// TODO Auto-generated constructor stub
	}
	
	private String username;
	
	@NotEmpty
	private String firstName;
	
	@NotEmpty
	private String lastName;
	
	@Pattern(regexp = "[0-9]{13}")
	private String jmb;
	
	@NotEmpty
	@Email
	private String email;
	
	
	private String oldPassword;
	
	
	public String getOldPassword() {
		return oldPassword;
	}
	
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
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

package net.etfbl.muzickagroznica.form.bean;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


public class NewEventForm {

	public NewEventForm() {
		// TODO Auto-generated constructor stub
	}

	@NotNull
	private java.util.Date eventTime;
	
	@NotEmpty
	private String description;
	
	@NotEmpty
	private String location;
	
	@NotEmpty
	private String name;

	public java.util.Date getEventTime() {
		return eventTime;
	}

	public void setEventTime(java.util.Date eventTime) {
		this.eventTime = eventTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}

package net.etfbl.muzickagroznica.model.entities;

// Generated Mar 18, 2015 12:34:37 PM by Hibernate Tools 4.3.1

import java.util.Date;

/**
 * Event generated by hbm2java
 */
public class Event implements java.io.Serializable {

	private Integer id;
	private User user;
	private String name;
	private String location;
	private String description;
	private Date publishTime;
	private Date eventTime;
	private Integer approvalStatus;

	public Event() {
	}

	public Event(User user, String name, String location, String description,
			Date publishTime, Date eventTime, Integer approvalStatus) {
		this.user = user;
		this.name = name;
		this.location = location;
		this.description = description;
		this.publishTime = publishTime;
		this.eventTime = eventTime;
		this.approvalStatus = approvalStatus;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPublishTime() {
		return this.publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Date getEventTime() {
		return this.eventTime;
	}

	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}

	public Integer getApprovalStatus(){
		return this.approvalStatus;
	}

	public void setApprovalStatus(Integer approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

}

package net.etfbl.muzickagroznica.model.entities;

// Generated Mar 13, 2015 10:37:52 PM by Hibernate Tools 4.3.1

import java.util.Date;

/**
 * Rate generated by hbm2java
 */
public class Rate implements java.io.Serializable {

	private Integer id;
	private MusicContent musicContent;
	private User user;
	private int rate;
	private Date ratingTime;

	public Rate() {
	}

	public Rate(MusicContent musicContent, User user, int rate, Date ratingTime) {
		this.musicContent = musicContent;
		this.user = user;
		this.rate = rate;
		this.ratingTime = ratingTime;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MusicContent getMusicContent() {
		return this.musicContent;
	}

	public void setMusicContent(MusicContent musicContent) {
		this.musicContent = musicContent;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getRate() {
		return this.rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public Date getRatingTime() {
		return this.ratingTime;
	}

	public void setRatingTime(Date ratingTime) {
		this.ratingTime = ratingTime;
	}

}
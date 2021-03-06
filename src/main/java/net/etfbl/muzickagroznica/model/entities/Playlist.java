package net.etfbl.muzickagroznica.model.entities;

// Generated Mar 18, 2015 12:34:37 PM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Playlist generated by hbm2java
 */
public class Playlist implements java.io.Serializable {

	private Integer id;
	private User user;
	private String title;
	private Date creationTime;
	private Set<MusicContent> musicContents = new HashSet<MusicContent>(0);
	private Integer creatorId;

	public Playlist() {
	}

	public Playlist(User user, Date creationTime) {
		this.user = user;
		this.creationTime = creationTime;
	}

	public Playlist(User user, String title, Date creationTime,
			Set<MusicContent> musicContents) {
		this.user = user;
		this.title = title;
		this.creationTime = creationTime;
		this.musicContents = musicContents;
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

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreationTime() {
		return this.creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Set<MusicContent> getMusicContents() {
		return this.musicContents;
	}

	public void setMusicContents(Set<MusicContent> musicContents) {
		this.musicContents = musicContents;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	
}

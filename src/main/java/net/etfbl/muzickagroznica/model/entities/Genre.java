package net.etfbl.muzickagroznica.model.entities;

// Generated Mar 13, 2015 10:37:52 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

/**
 * Genre generated by hbm2java
 */
public class Genre implements java.io.Serializable {

	private String name;
	private Set musicContents = new HashSet(0);

	public Genre() {
	}

	public Genre(String name) {
		this.name = name;
	}

	public Genre(String name, Set musicContents) {
		this.name = name;
		this.musicContents = musicContents;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getMusicContents() {
		return this.musicContents;
	}

	public void setMusicContents(Set musicContents) {
		this.musicContents = musicContents;
	}

}

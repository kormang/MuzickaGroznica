package net.etfbl.muzickagroznica.form.bean;

import net.etfbl.muzickagroznica.controller.validation.constraint.MgContentLink;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

public class ContentNewForm {

	public ContentNewForm() {
		// TODO Auto-generated constructor stub
	}

	@NotEmpty
	private String artist;
	
	@MgContentLink
	@URL
	private String contentPath;
	
	@NotEmpty
	private String genre;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String lyrics;
		

	public String getLyrics() {
		return lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getContentPath() {
		return contentPath;
	}

	public void setContentPath(String contentPath) {
		this.contentPath = contentPath;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}

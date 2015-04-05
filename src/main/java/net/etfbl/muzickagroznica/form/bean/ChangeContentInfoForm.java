package net.etfbl.muzickagroznica.form.bean;

import org.hibernate.validator.constraints.NotEmpty;

public class ChangeContentInfoForm {

	public ChangeContentInfoForm() {
		// TODO Auto-generated constructor stub
	}

	private Integer id;
	
	@NotEmpty
	private String genre;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String lyrics;
	
	@NotEmpty
	private String artist;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
	
	
}

package net.etfbl.muzickagroznica.controller.utils.entities;

import java.util.Date;

import net.etfbl.muzickagroznica.model.entities.Artist;
import net.etfbl.muzickagroznica.model.entities.Genre;
import net.etfbl.muzickagroznica.model.entities.User;

public class MusicContentExtraCount {

	public MusicContentExtraCount() {
		// TODO Auto-generated constructor stub
	}

	private Integer id;
	private String name;
	private Date publishTime;
	private String contentType;
	private String artistName;
	private String genreName;
	private Integer extraCount;
	

	public Integer getExtraCount() {
		return extraCount;
	}
	public void setExtraCount(Integer extraCount) {
		this.extraCount = extraCount;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	
	
	
}

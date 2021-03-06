package net.etfbl.muzickagroznica.model.entities;

// Generated Mar 18, 2015 12:34:37 PM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * MusicContent generated by hbm2java
 */
public class MusicContent implements java.io.Serializable {

	private Integer id;
	private Artist artist;
	private Genre genre;
	private String name;
	private Date publishTime;
	private String lyrics;
	private Date duration;
	private String contentPath;
	private Integer contentType;
	private boolean active;
	private String extraInfo;
	private User user;
	private String artistName;
	private String genreName;

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	private Set<Favorite> favorites = new HashSet<Favorite>(0);
	private Set<Listening> listenings = new HashSet<Listening>(0);
	private Set<Playlist> playlists = new HashSet<Playlist>(0);
	private Set<Rate> rates = new HashSet<Rate>(0);
	private Set<Comment> comments = new HashSet<Comment>(0);

	public MusicContent() {
	}

	public MusicContent(Artist artist, Genre genre, String name,
			Date publishTime, String lyrics, Date duration, String contentPath,
			Integer contentType, boolean active, User user) {
		this.artist = artist;
		this.genre = genre;
		this.name = name;
		this.publishTime = publishTime;
		this.lyrics = lyrics;
		this.duration = duration;
		this.contentPath = contentPath;
		this.contentType = contentType;
		this.active = active;
		this.user = user;
	}

	public MusicContent(Artist artist, Genre genre, String name,
			Date publishTime, String lyrics, Date duration, String contentPath,
			Integer contentType, boolean active, Set<Favorite> favorites, Set<Listening> listenings,
			Set<Playlist> playlists, Set<Rate> rates, Set<Comment> comments) {
		this.artist = artist;
		this.genre = genre;
		this.name = name;
		this.publishTime = publishTime;
		this.lyrics = lyrics;
		this.duration = duration;
		this.contentPath = contentPath;
		this.contentType = contentType;
		this.active = active;
		this.favorites = favorites;
		this.listenings = listenings;
		this.playlists = playlists;
		this.rates = rates;
		this.comments = comments;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object other){
		if(other == this){
			return true;
		}
		if(other instanceof MusicContent){
			return ((MusicContent)other).id == this.id;
		}
		return false;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Artist getArtist() {
		return this.artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public Genre getGenre() {
		return this.genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getPublishTime() {
		return this.publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getLyrics() {
		return this.lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	public Date getDuration() {
		return this.duration;
	}

	public void setDuration(Date duration) {
		this.duration = duration;
	}

	public String getContentPath() {
		return this.contentPath;
	}

	public void setContentPath(String contentPath) {
		this.contentPath = contentPath;
	}

	public Integer getContentType() {
		return this.contentType;
	}

	public void setContentType(Integer contentType) {
		this.contentType = contentType;
	}

	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getExtraInfo() {
		return extraInfo;
	}

	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public Set<Favorite> getFavorites() {
		return this.favorites;
	}

	public void setFavorites(Set<Favorite> favorites) {
		this.favorites = favorites;
	}

	public Set<Listening> getListenings() {
		return this.listenings;
	}

	public void setListenings(Set<Listening> listenings) {
		this.listenings = listenings;
	}

	public Set<Playlist> getPlaylists() {
		return this.playlists;
	}

	public void setPlaylists(Set<Playlist> playlists) {
		this.playlists = playlists;
	}

	public Set<Rate> getRates() {
		return this.rates;
	}

	public void setRates(Set<Rate> rates) {
		this.rates = rates;
	}

	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	

}

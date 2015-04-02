package net.etfbl.muzickagroznica.service.helper.entities;

public class PlaylistSummaryData {

	public PlaylistSummaryData() {
		// TODO Auto-generated constructor stub
	}

	private int id;
	private String title;
	private java.util.Date creationTime;
	private int numberOfContents;
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public java.util.Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(java.util.Date creationDate) {
		this.creationTime = creationDate;
	}
	public int getNumberOfContents() {
		return numberOfContents;
	}
	public void setNumberOfContents(int numberOfContents) {
		this.numberOfContents = numberOfContents;
	}
	
	

}

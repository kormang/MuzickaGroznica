package net.etfbl.muzickagroznica.model.dao;

import net.etfbl.muzickagroznica.model.entities.MusicContent;

import java.util.List;

public interface MusicContentDao {
	
	void persist(MusicContent musiccontent);
	MusicContent merge(MusicContent musiccontent);
	void delete(MusicContent musiccontent);
	MusicContent findById(int id);
	List<MusicContent> findByExample(MusicContent musiccontent);
	List<MusicContent> search(String name, String artist, String genre);
	List<MusicContent> findTopNMusicContent(int n);
	
	/**
	 * 
	 * @param limit number of results to return
	 * @param genreRestriction if not null resulting content will have this genre
	 * @param artistRestriction if not null resulting content will have this artist
	 * @return random picked music content
	 */
	List<MusicContent> random(int limit, String genreRestriction, String artistRestriction);
	
}

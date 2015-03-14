package net.etfbl.muzickagroznica.model.dao;

import net.etfbl.muzickagroznica.model.entities.MusicContent;

import java.util.List;

public interface MusicContentDao {
	
	void persist(MusicContent musiccontent);
	MusicContent merge(MusicContent musiccontent);
	void delete(MusicContent musiccontent);
	MusicContent findById(int id);
	List<MusicContent> findByExample(MusicContent musiccontent);
	
}

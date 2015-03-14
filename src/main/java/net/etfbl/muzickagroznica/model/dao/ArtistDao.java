package net.etfbl.muzickagroznica.model.dao;

import net.etfbl.muzickagroznica.model.entities.Artist;

import java.util.List;

public interface ArtistDao {
	
	void persist(Artist artist);
	Artist merge(Artist artist);
	void delete(Artist artist);
	Artist findById(int id);
	List<Artist> findByExample(Artist artist);
	
}

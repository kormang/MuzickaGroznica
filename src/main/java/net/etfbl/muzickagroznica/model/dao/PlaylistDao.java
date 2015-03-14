package net.etfbl.muzickagroznica.model.dao;

import net.etfbl.muzickagroznica.model.entities.Playlist;

import java.util.List;

public interface PlaylistDao {
	
	void persist(Playlist playlist);
	Playlist merge(Playlist playlist);
	void delete(Playlist playlist);
	Playlist findById(int id);
	List<Playlist> findByExample(Playlist playlist);
	
}

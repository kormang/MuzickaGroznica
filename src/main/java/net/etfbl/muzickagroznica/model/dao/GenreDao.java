package net.etfbl.muzickagroznica.model.dao;

import net.etfbl.muzickagroznica.model.entities.Genre;

import java.util.List;

public interface GenreDao {
	
	void persist(Genre genre);
	Genre merge(Genre genre);
	void delete(Genre genre);
	Genre findById(int id);
	List<Genre> findByExample(Genre genre);
	List<Genre> findAll();
	
}

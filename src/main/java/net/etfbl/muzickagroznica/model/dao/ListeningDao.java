package net.etfbl.muzickagroznica.model.dao;

import net.etfbl.muzickagroznica.model.entities.Listening;

import java.util.List;

public interface ListeningDao {
	
	void persist(Listening listening);
	Listening merge(Listening listening);
	void delete(Listening listening);
	Listening findById(int id);
	List<Listening> findByExample(Listening listening);
	List<Listening> findLastListening(int limit);
	List<Listening> findLastListening(int limit, int userId);
	
}

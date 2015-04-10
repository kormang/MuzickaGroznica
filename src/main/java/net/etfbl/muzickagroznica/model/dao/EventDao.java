package net.etfbl.muzickagroznica.model.dao;

import net.etfbl.muzickagroznica.model.entities.Event;

import java.util.List;

public interface EventDao {
	
	void persist(Event event);
	Event merge(Event event);
	void delete(Event event);
	Event findById(int id);
	List<Event> findByExample(Event event);
	
}

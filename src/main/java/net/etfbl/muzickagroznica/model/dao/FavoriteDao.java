package net.etfbl.muzickagroznica.model.dao;

import net.etfbl.muzickagroznica.model.entities.Favorite;

import java.util.List;

public interface FavoriteDao {
	
	void persist(Favorite favorite);
	Favorite merge(Favorite favorite);
	void delete(Favorite favorite);
	Favorite findById(int id);
	List<Favorite> findByExample(Favorite favorite);
	
}

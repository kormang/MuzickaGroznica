package net.etfbl.muzickagroznica.model.dao;

import net.etfbl.muzickagroznica.model.entities.Rate;

import java.util.List;

public interface RateDao {
	
	void persist(Rate rate);
	Rate merge(Rate rate);
	void delete(Rate rate);
	Rate findById(int id);
	List<Rate> findByExample(Rate rate);
	
}

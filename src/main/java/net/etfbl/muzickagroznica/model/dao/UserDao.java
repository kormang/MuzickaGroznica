package net.etfbl.muzickagroznica.model.dao;

import net.etfbl.muzickagroznica.model.entities.User;

import java.util.List;

public interface UserDao {
	
	void persist(User user);
	User merge(User user);
	void delete(User user);
	User findById(int id);
	List<User> findByExample(User user);
	User findByUsername(String username);
	List<User> findByActive(boolean active);
	List<User> findRegisteredAfter(java.util.Date date);
}

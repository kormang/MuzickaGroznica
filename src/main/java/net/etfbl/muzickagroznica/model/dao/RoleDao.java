package net.etfbl.muzickagroznica.model.dao;

import net.etfbl.muzickagroznica.model.entities.Role;

import java.util.List;

public interface RoleDao {
	
	void persist(Role role);
	Role merge(Role role);
	void delete(Role role);
	Role findById(int id);
	List<Role> findByExample(Role role);
	List<Role> findByUserId(int id);
	
}

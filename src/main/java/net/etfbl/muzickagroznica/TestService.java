package net.etfbl.muzickagroznica;

import net.etfbl.muzickagroznica.model.dao.RoleDao;
import net.etfbl.muzickagroznica.model.entities.Role;
import net.etfbl.muzickagroznica.model.entities.RoleId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class TestService {

	@Autowired
	RoleDao roleDao;
	
	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public TestService() {
		// TODO Auto-generated constructor stub
	}
	
	@Transactional
	public void dosome(){
		
		System.err.println("doing some inside");
		
		Role role = new Role();
		role.setId(new RoleId(6, "ROLE_SLATKOCA"));
		System.err.println("persisting...");
		roleDao.persist(role);
		
		System.err.println("done some inside");
		
	}

}

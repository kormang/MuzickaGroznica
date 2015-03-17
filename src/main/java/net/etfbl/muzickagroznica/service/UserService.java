package net.etfbl.muzickagroznica.service;

import net.etfbl.muzickagroznica.model.dao.RoleDao;
import net.etfbl.muzickagroznica.model.dao.UserDao;
import net.etfbl.muzickagroznica.model.entities.Role;
import net.etfbl.muzickagroznica.model.entities.RoleId;
import net.etfbl.muzickagroznica.model.entities.User;
import net.etfbl.muzickagroznica.util.StandardUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	RoleDao roleDao;
	
	public UserService() {
		// TODO Auto-generated constructor stub
		System.err.println("------>>> KONSTRUISAN SERVIS!!!");
	}

	/**
	 * 
	 * @param user user with information from registraction form or something like that
	 * @param rawPassword a raw, unencrypted password 
	 */
	@Transactional
	public void registerNewUser(User user, String rawPassword){
		
		user.setActivationTime(null);
		user.setAvatarPath(null);
		user.setRegisteringTime(StandardUtil.now());
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(rawPassword));
		
		userDao.persist(user);
		
		Role registered = new Role();
		registered.setId(new RoleId(user.getId(), "ROLE_REGISTERED"));
		roleDao.persist(registered);
		
		
	}
	
}

package net.etfbl.muzickagroznica.security;


import java.util.ArrayList;
import java.util.List;

import net.etfbl.muzickagroznica.model.dao.UserDao;
import net.etfbl.muzickagroznica.model.entities.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.transaction.annotation.Transactional;

public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService{


	@Autowired
	private UserDao userDao;
	
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserDetailsServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		net.etfbl.muzickagroznica.model.entities.User user = userDao.findByUsername(username);
		
		List<GrantedAuthority> authorites = new ArrayList<GrantedAuthority>();
		
		if(user == null){
			return new AuthUser("__wrong__", "__wrong__", authorites, user);
		}
		
		
		
		
		for(Role r : user.getRoles()){
			authorites.add(new SimpleGrantedAuthority(r.getId().getRoleName()));
		}
		
		System.err.println(user.getUsername() + " is " + (user.isActive() ? "" : "not") + " active.");
		
		for(GrantedAuthority ga : authorites){
			System.err.println(ga.getAuthority());
		}
		
		return new AuthUser(user.getUsername(), user.getPassword(), user.isActive(),
				true, true, true,
				authorites, user);
		
		
	}
	
	

}


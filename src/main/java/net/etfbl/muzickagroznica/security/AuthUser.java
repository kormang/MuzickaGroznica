package net.etfbl.muzickagroznica.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class AuthUser extends org.springframework.security.core.userdetails.User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private net.etfbl.muzickagroznica.model.entities.User user;
	
	public net.etfbl.muzickagroznica.model.entities.User getUser() {
		return user;
	}



	public void setUser(net.etfbl.muzickagroznica.model.entities.User user) {
		this.user = user;
	}



	public AuthUser(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities, net.etfbl.muzickagroznica.model.entities.User user) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		
		this.user = user;
		
	}



	public AuthUser(String username, String password,
			Collection<? extends GrantedAuthority> authorities,
			net.etfbl.muzickagroznica.model.entities.User user) {
		super(username, password, authorities);
		
		this.user = user;
		
	}
	
}

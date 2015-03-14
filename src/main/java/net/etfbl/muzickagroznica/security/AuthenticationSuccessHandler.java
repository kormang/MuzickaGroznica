package net.etfbl.muzickagroznica.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class AuthenticationSuccessHandler 	extends SavedRequestAwareAuthenticationSuccessHandler{

	public AuthenticationSuccessHandler() {
		// TODO Auto-generated constructor stub
	}
	

    public void onAuthenticationSuccess(final HttpServletRequest request,
            final HttpServletResponse response, final Authentication authentication)
            throws IOException, ServletException {
        super.onAuthenticationSuccess(request, response, authentication);

        HttpSession session = request.getSession(true);
        
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        session.setAttribute("user", authUser.getUser());
        session.setAttribute("username", authUser.getUsername());
        
        for(GrantedAuthority ga : authentication.getAuthorities()){
        	session.setAttribute(ga.getAuthority(), ga.getAuthority());
        }
        
        System.err.println("authed: " + authUser.getUsername());
        
    }

}

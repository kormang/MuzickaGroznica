package net.etfbl.muzickagroznica.security;


import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;

public class AuthenticationFailureHandler extends ExceptionMappingAuthenticationFailureHandler{

	public AuthenticationFailureHandler() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public	void onAuthenticationFailure (
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		super.onAuthenticationFailure(request, response, exception);
		
		
	}

	
	
}

package br.com.framework.frameworkpost.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class FrameWorkSecurity {

	public Authentication getAuthentication() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return authentication;
	}
	
	public String getUserIdJwt() {
		Jwt jwt = (Jwt) getAuthentication().getPrincipal();
		return jwt.getClaim("user_name");
	}
	
}

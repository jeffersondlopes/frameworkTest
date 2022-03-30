package br.com.framework.frameworkpost.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public String getUserIdJwt() {
		Jwt jwt = (Jwt) getAuthentication().getPrincipal();
		return jwt.getClaim("user_name");
	}
	
}

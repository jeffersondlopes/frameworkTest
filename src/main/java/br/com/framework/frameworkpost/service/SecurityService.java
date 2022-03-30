package br.com.framework.frameworkpost.service;

import br.com.framework.frameworkpost.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

	private final UserService userService;

	@Autowired
	public SecurityService(UserService userService) {
		this.userService = userService;
	}

	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public String getUserIdJwt() {
		Jwt jwt = (Jwt) getAuthentication().getPrincipal();
		return jwt.getClaim("user_name");
	}

	public User getUser(){
		String userIdJwt = getUserIdJwt();
		return userService.findByEmail(userIdJwt);
	}
	
}

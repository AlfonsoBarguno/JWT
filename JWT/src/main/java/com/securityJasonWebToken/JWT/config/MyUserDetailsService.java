package com.securityJasonWebToken.JWT.config;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//este es el usuario que le damos por defecto
		return new User("alfonso", "alfonso", new ArrayList<>());
		
		//pero aquí es donde la conectaríamos al repositorio
		//para buscar el username
	}

	
	
}

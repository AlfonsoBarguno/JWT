package com.securityJasonWebToken.JWT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.securityJasonWebToken.JWT.config.MyUserDetailsService;
import com.securityJasonWebToken.JWT.entity.AuthenticationRequest;
import com.securityJasonWebToken.JWT.entity.AuthenticationResponse;
import com.securityJasonWebToken.JWT.util.JwtUtil;

@RestController
@EntityScan
public class SecurityController {
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwTokenUtil;
	
	@RequestMapping("/hello")
	public String hello() {
		return "hello world";
	}
	
	
	// endpoint con un método para crear un authentication Token
	// con el payload del requestBody (nombre y password del usuario)
	@RequestMapping(value="/authenticate", method=RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		
		//autentificamos nombre y password
		//lanzamos excepción si la info es incorrecta
		try {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		
		
	} catch (BadCredentialsException e) {
		
	 throw new Exception("Incorrect information.",e);
	}
		//autentificación exitosa en este punto
		//debemos crear y devolver un jwt y necesitamos UserDetails
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
	    
	    final String jwt = jwTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
}
}
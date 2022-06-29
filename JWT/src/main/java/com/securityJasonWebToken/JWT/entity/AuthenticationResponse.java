package com.securityJasonWebToken.JWT.entity;

public class AuthenticationResponse {

	//el ouput de la respuesta con un jwt
	
	private final String jwt;
	
	
	
	public AuthenticationResponse(String jwt) {
		super();
		this.jwt = jwt;
	}



	public String getJwt() {
		return jwt;
	}
}

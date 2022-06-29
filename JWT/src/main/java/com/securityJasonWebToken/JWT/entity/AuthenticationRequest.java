package com.securityJasonWebToken.JWT.entity;

public class AuthenticationRequest {
	
	
	
	//define el argumento(input) del método authenticate
	//es lo que manda el usuario en la POST request
	
	private String username;
	private String password;
	
	
	
	public AuthenticationRequest() {
		super();
	}
	public AuthenticationRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}

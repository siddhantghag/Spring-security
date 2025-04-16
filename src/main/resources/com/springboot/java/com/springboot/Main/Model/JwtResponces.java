package com.springboot.Main.Model;


public class JwtResponces 
{
	String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public JwtResponces(String token) {
		super();
		this.token = token;
	}

	public JwtResponces() 
	{	
		
	}
	
}

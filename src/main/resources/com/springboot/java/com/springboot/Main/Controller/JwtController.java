package com.springboot.Main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.Main.Model.JwtRequest;
import com.springboot.Main.Model.JwtResponces;
import com.springboot.Main.Service.CustomUserDetailsService;
import com.springboot.Main.helper.Jwtutil;

@RestController
public class JwtController 
{
	/*
	 * @Autowired private AuthenticationProvider authenticationProvider;
	 */
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private Jwtutil jwtutil;
	
	@PostMapping("/token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
	{
		 System.out.println(jwtRequest);
		 try
		 {
			 this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
					 (jwtRequest.getUsername(), jwtRequest.getPassword()));
		 }
		 catch (UsernameNotFoundException e) 
		 {
			e.printStackTrace();
			throw new Exception("Bad credentials");	
		 }
		 catch(BadCredentialsException b)
		 {
			 b.printStackTrace();
			 throw new Exception("Bad credentials");
		 }
		 
		String token=this.jwtutil.doGenerateToken(jwtRequest.getUsername());
	    return ResponseEntity.ok(new JwtResponces(token));
	}
}

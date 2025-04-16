package com.springboot.Main.Configure;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.springboot.Main.Service.CustomUserDetailsService;
import com.springboot.Main.helper.Jwtutil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter 
{
	@Autowired
	private Jwtutil jwtutil;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException 
	{
		// first get JWT
		// second check to Bearer start or not
		// third then validate

		String requestTokenHeader = request.getHeader("Authorization");
		String username=null;	
		String jwtToken=null;

		// check the token are null or not and check the format
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")) 
		{
			jwtToken = requestTokenHeader.substring(7);

			try 
			{
				username=this.jwtutil.getUsernameFromToken(jwtToken);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}

			UserDetails details = this.customUserDetailsService.loadUserByUsername(username);

			// Security
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) 
			{
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());

				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			} 
			else 
			{
				System.out.println("token is not validate");
			}
		}
		filterChain.doFilter(request, response);
	}
}

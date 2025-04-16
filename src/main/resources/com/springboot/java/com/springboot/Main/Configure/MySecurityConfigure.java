package com.springboot.Main.Configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springboot.Main.Service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class MySecurityConfigure 
{
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired 
	private JwtAuthenticationFilter authenticationFilter;
	
	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	
	@Bean
	public SecurityFilterChain chain(HttpSecurity httpSecurity) throws Exception
	{
		httpSecurity
			.csrf()
			.disable()
			.authorizeHttpRequests()
			.requestMatchers("/token").permitAll() 			
			.anyRequest()
			.authenticated()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
		
		httpSecurity.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return httpSecurity.build();
	}
	
	/*
	 * @Bean 
	 * public UserDetailsService detailsService(PasswordEncoder encoder) 
	 * {
	 *   UserDetails details1= 
	 *             User 
	 *                 .builder() 
	 *                 .username("yash")
	 *                 .password(encoder()
	 *                 .encode("ya1")) 
	 *                 .roles("USER") 
	 *                 .build();
	 * 
	 * UserDetails details2= User .builder() .username("sagar")
	 * .password(encoder().encode("sar1")) .roles("ADMIN") .build(); return new
	 * InMemoryUserDetailsManager(details1, details2); }
	 */
	
	
	  @Bean 
	  public AuthenticationProvider authProvider() 
	  {
		  DaoAuthenticationProvider daoProvider=new DaoAuthenticationProvider();
		  daoProvider.setUserDetailsService(customUserDetailsService);
	 	  daoProvider.setPasswordEncoder(encoder()); 
	      return daoProvider; 
	  }
	  
	 
	  @Bean
	  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception 
	  {
		 return authConfig.getAuthenticationManager();
	  } 

		@Bean
		public PasswordEncoder encoder()
		{
			return new BCryptPasswordEncoder();
		}
}

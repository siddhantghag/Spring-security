package com.springboot.Main.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.Main.Connection.CustomUserDetails;
import com.springboot.Main.Repo.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService
{
	
	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException 
	{
		
		com.springboot.Main.Model.User user= this.repository.findByUsername(userName);
		
		System.out.println(user);
		if(user == null)
		{
			throw new UsernameNotFoundException("user not found this Username "+user);
		}
		else
		{
		    return new CustomUserDetails(user);
		}
		/*
		 * Fack Service :-
		 * 
		 * if(userName.equals("siddhant")) { return new User("siddhant" , new
		 * BCryptPasswordEncoder().encode("sid") , new ArrayList<>()); } else { throw
		 * new UsernameNotFoundException("User not found"); }
		 */
	}

}

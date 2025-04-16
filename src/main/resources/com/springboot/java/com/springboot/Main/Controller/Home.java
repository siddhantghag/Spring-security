package com.springboot.Main.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home 
{
	@GetMapping("/welcome")
	public String welcome()
	{
		String text = "this is pravite page ,";
		text+= " This page is not alloword to unauthenticated users";
		return text;
	}
	
	@GetMapping("/getusers")
	public String getUser()
	{
		
		return "This is a User String";
	}
}

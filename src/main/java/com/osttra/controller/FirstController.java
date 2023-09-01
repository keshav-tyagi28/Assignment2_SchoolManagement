package com.osttra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.osttra.to.User;


@RestController
public class FirstController {

	
		
		@GetMapping("/getMssg") 
		
		public String getMessage()
		{
			return "Hello world";
		}
		
		@GetMapping("/getUser")
		public User getUser()
		{
			System.out.println("Hello inside user");
			User user = new User("a");
			return user;
		}
		
//		@GetMapping("/getUsers")
//		public List<User> getAll()
//		{
//			
//		}
		
		
	
}

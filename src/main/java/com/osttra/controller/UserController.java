package com.osttra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.cj.Session;
import com.osttra.service.UserService;
import com.osttra.to.User;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	UserService userservice;

	@GetMapping("/getregister")
	public String getRegistration()
	{
		return "register_page";
		
	}
	
	@PostMapping("/register")
	public String  register(User user)
	{
		
		
		userservice.registerUser(user);
		
		return "login_page";
		
	}
	
	
	@GetMapping("/getlogin")
	public String getlogin()
	{
		
		return "login_page";
		
		
	}
	
	
	@PostMapping("/login")
	public ModelAndView login(String username, String password, HttpServletRequest request)
	{
		User user= userservice.login(username,password);
		System.out.println(user);
		
		ModelAndView modelandview=null;
		
		if(user!=null && user.getStatus().equals("active"))
		{
			modelandview= new ModelAndView("welcome_page");
			HttpSession session= request.getSession();
			session.setAttribute("user", user);
			
		}
		else
		{
			modelandview= new ModelAndView("login_page");
			modelandview.addObject("errormessage", "Cannot Login");
		}
		
		return modelandview;
		
	}
	
	@GetMapping("/welcome/{username}")
	public String backtoWelcome(@PathVariable String username,HttpServletRequest request)
	{
		
		User u= userservice.UserByUsername(username);
		HttpSession session= request.getSession();
		session.setAttribute("user", u);
		return "welcome_page";
		
		
	}
	
	@GetMapping("/update/{username}")
	public ModelAndView updatePage(@PathVariable String username, HttpServletRequest request) {
		
		
		System.out.println("inside update ");
		ModelAndView modelAndView = null;
		
	  HttpSession session = request.getSession(false); // if no session then it returns null 
														 // direct url access will not be their then
		
		
		
		
		if(  session!=null )
		{
			
			User needupdate= userservice.UserByUsername(username);
			modelAndView = new ModelAndView("update_user");
			modelAndView.addObject("needupdate",needupdate);
		}
	
		else  {
			modelAndView = new ModelAndView("login_page");
		}
		
		
		return modelAndView;
			
	}
	
	// mapping when admin is updating a user as mergin with existing was problematic
	@GetMapping("/admin/update/{username}")
	public ModelAndView updatePageAdmin(@PathVariable String username, HttpServletRequest request) {
		
		
		System.out.println("inside update ");
		ModelAndView modelAndView = null;
		
	  HttpSession session = request.getSession(false); // if no session then it returns null 
														 // direct url access will not be their then
		
		
		
		
		if(  session!=null )
		{
			
			User needupdate= userservice.UserByUsername(username);
			/* System.out.println(needupdate); */
			modelAndView = new ModelAndView("update_user_admin");
			modelAndView.addObject("needupdate",needupdate);
		}
	
		
		
		
		return modelAndView;
			
	}
	
	
	
	// when admin is updating a user
	@PostMapping("/admin/update")
	public String updateAdmin(User user, HttpServletRequest request) {
		
		System.out.println(user);
		
		userservice.update(user);
		
		return "redirect:/displayallusers";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@PostMapping("/update")
	public ModelAndView update(User user, HttpServletRequest request) {
		
		
		
		userservice.update(user);
		
		HttpSession session = request.getSession(false);
		
		User oldUser = ( User )session.getAttribute("user");
		
		String password = oldUser.getPassword();
		String role = oldUser.getRole();
		String status=oldUser.getStatus();
		Integer counter= oldUser.getCounter();
		
		user.setPassword(password);   user.setRole(role);
		user.setStatus(status);
		user.setCounter(counter);
		
		session.setAttribute("user", user);
		
		ModelAndView modelAndView = new ModelAndView("welcome_page");
		modelAndView.addObject("updateSuccessMsg", "Your Profile is updated successfully");
		
		return modelAndView;
		
	}
	
	
	
	
	@GetMapping("/delete/{username}")
	public String delete(@PathVariable String username, HttpServletRequest request) {
		
		

		HttpSession session = request.getSession(false);
		User sessionuser= (User) session.getAttribute("user");
		
		User seconduser=userservice.UserByUsername(username);
		
		// signifies if a user is deleting his/her account be it (admin,student,teacher)
		
		if(session!=null && sessionuser.getRole().equals(seconduser.getRole()))
		{
			userservice.delete(username);
			return "login_page";
		}
		
		// if admin send requests for deleting other user
		
//		 if( session!=null &&  sessionuser.getRole().equals("Admin"))
		
		
			userservice.delete(username);
			return "redirect:/displayallusers";
			
		
	}
	@GetMapping("/displayallstudents")
	public ModelAndView displayallstudents(HttpServletRequest request)
	{
		
		
		List<User> list =userservice.displayallstudents();
		
		ModelAndView modelandview= new ModelAndView("display_users");
	
		
		modelandview.addObject("users",list);
		
		
		return modelandview;
		
		
		
	}
	
	
	@GetMapping("/displayallusers")
	public ModelAndView displayallusers(HttpServletRequest request)
	{
		

		HttpSession session = request.getSession(false);
		User sessionuser= (User) session.getAttribute("user");
		
		System.out.println(sessionuser);
		List<User> list =userservice.displayallusers();
		
		ModelAndView modelandview= new ModelAndView("display_users");
	
		
		modelandview.addObject("users",list);
		modelandview.addObject("user",sessionuser);
		
		return modelandview;
		
		
		
	}
	
	@GetMapping("{username}/updatestatus/{status}")
	public String updateStatus(@PathVariable String username, @PathVariable String status)
	
	{
		
		System.out.println("inside update admin");
		User user=userservice.UserByUsername(username);
		
		System.out.println(status);
		
		user.setStatus(status);
		
		userservice.updateStatus(user);
		
		
		System.out.println(user);
		
		return "redirect:/displayallusers";
		
		
		
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request)
	{
		 HttpSession session = request.getSession(false);
	        if (session != null) {
	            session.invalidate();
	        }
	        
	        return "login_page";
	}
	
	
	
	
	
	
	
	
}

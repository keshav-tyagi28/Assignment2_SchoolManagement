package com.osttra.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.osttra.repository.UserRepo;
import com.osttra.to.User;

@Service
public class UserService {

	@Autowired
	UserRepo userrepo;
	
	public void registerUser(User u)
	{
		userrepo.addUser(u);
		
	}
	
	public User login(String name,String password)
	{
		

		User user= userrepo.getUser(name,password);
		
		return user;
	}
	
	public User UserByUsername(String username)
	{
		return userrepo.getByUsername(username);
	}
	
public void update(User user) {
		
		userrepo.update(user);
	}
public void updateStatus(User user) {
	
	userrepo.updateStatus(user);
}
	
public void delete(String username) {
	
	userrepo.delete(username);
}

public List<User> displayallstudents()
{
	return  userrepo.allStudents();
	

}


public List<User> displayallusers()
{
	return  userrepo.allUsers();
	

}
	
	
}

package com.osttra.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.osttra.to.User;
import com.osttra.utils.DbConnect;

@Repository
public class UserRepo {

	public void addUser(User user) {
		Connection conn = DbConnect.connect();
		try {
			PreparedStatement statement = conn.prepareStatement("insert into user values(?,?,?,?,?,?,?)");

			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getCompleteName());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getRole());
			if(user.getRole().equals("Admin"))
			{
				statement.setString(6, "active");
			}
			else {
				statement.setString(6, "inactive");
			}			
			statement.setInt(7, 0);
			

			statement.executeUpdate();

		} catch (Exception e) {
			System.out.println("inside user repo add");
			e.printStackTrace();

		}

	}

	
	public User getByUsername(String username) {
		User user = null;

		Connection conn = DbConnect.connect();
		try {

			PreparedStatement statement = conn.prepareStatement("select * from user where username=?");

			statement.setString(1, username);
			ResultSet resultset = statement.executeQuery();

			if (resultset.next()) {

				String password= resultset.getString(2);
				String completeName = resultset.getString(3);
				String email = resultset.getString(4);
				String role = resultset.getString(5);
				String status= resultset.getString(6);
				Integer counter= resultset.getInt(7);

				user = new User(username, password, completeName, email, role,status,counter);
			}
		} catch (Exception e) {
			System.out.println("inside catch of getUser(username and password) of UserRepository...");
			e.printStackTrace();
		}

		return user;

	}
	
	
	
	public User getUser(String username, String password)
	{
		
	

		User user=null;
		
		Connection conn= DbConnect.connect();
		try {
		
		PreparedStatement statement= conn.prepareStatement("select * from user where username=?");
		
		statement.setString(1, username);
		/* statement.setString(2, password); */
		
		ResultSet resultset =statement.executeQuery();
		
		if (resultset.next()) {
			
			if(password.equals(resultset.getString(2)))
			{
				String completeName = resultset.getString(3);
				String email = resultset.getString(4);
				String role = resultset.getString(5);
				String status= resultset.getString(6);
				Integer counter= 0;
				PreparedStatement statement2= conn.prepareStatement("update user set counter=? where username=?");
				
				statement2.setInt(1, counter);
				statement2.setString(2, username);
				statement2.executeUpdate();
				
				user = new User(username, password, completeName, email, role,status,counter);
			}
			else
			{
				System.out.println("inside else of login repo");
				Integer counter= resultset.getInt(7);
				counter+=1;
				if(counter>=3)
				{
					PreparedStatement statement2= conn.prepareStatement("update user  set status=?, counter=?  where username=?");
					statement2.setString(1, "blocked");
					statement2.setInt(2, counter);
					statement2.setString(3, username);
					statement2.executeUpdate();
				}
				else {
				PreparedStatement statement2= conn.prepareStatement("update user set counter=? where username=?");
				
				statement2.setInt(1, counter);
				statement2.setString(2, username);
				statement2.executeUpdate();
				}
			}

			
		}
	} catch (Exception e) {
		System.out.println("inside catch of getUser(username and password) of UserRepository...");
		e.printStackTrace();
	}
		
	return user;

}
	

	public void update(User user) {
		try {

			Connection connection = DbConnect.connect();

			PreparedStatement statement = connection.prepareStatement("update user set completeName = ?, email = ? where username = ?");

			statement.setString(1, user.getCompleteName());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getUsername());
			

			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("inside catch of update of UserRepository...");
			e.printStackTrace();
		}
		
	}
	public void updateStatus(User user) {
		try {

			Connection connection = DbConnect.connect();

			PreparedStatement statement = connection.prepareStatement("update user set status=? where username = ?");

			statement.setString(1, user.getStatus());
			statement.setString(2, user.getUsername());
			

			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("inside catch of update of UserRepository...");
			e.printStackTrace();
		}
		
	}
	
	
	
	public void delete(String username) {

		try {

			Connection connection = DbConnect.connect();

			PreparedStatement statement = connection.prepareStatement("delete from user where username = ?");

			statement.setString(1, username);

			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("inside catch of delete of UserRepository...");
			e.printStackTrace();
		}
	}

public List<User> allStudents()
{
	
	List<User> list= new ArrayList<>();
	try {

		Connection connection = DbConnect.connect();

		PreparedStatement statement = connection.prepareStatement("select * from user where role = ?");

		statement.setString(1, "Student");
		
		ResultSet resultset =statement.executeQuery();
		
		if (!resultset.next()) {
		    throw new Exception("No Students available.");
		}

		do {
		    
			String username= resultset.getString(1);
			String password= resultset.getString(2);
			String completeName = resultset.getString(3);
			String email = resultset.getString(4);
			String role = resultset.getString(5);
			String status=resultset.getString(6);
			Integer counter= resultset.getInt(7);
			
			User u= new User(username,password,completeName,email,role,status,counter);
		
			list.add(u);
		    
		} while (resultset.next());

	}catch(Exception e)
	{
		System.out.println("inside displayall of userrepo");
	}
		
return list;

}



public List<User> allUsers()
{
	
	List<User> list= new ArrayList<>();
	try {

		Connection connection = DbConnect.connect();

		PreparedStatement statement = connection.prepareStatement("select * from user ");

		
		
		ResultSet resultset =statement.executeQuery();
		
		if (!resultset.next()) {
		    throw new Exception("No Users available.");
		}

		do {
		    
			String username= resultset.getString(1);
			String password= resultset.getString(2);
			String completeName = resultset.getString(3);
			String email = resultset.getString(4);
			String role = resultset.getString(5);
			String status=resultset.getString(6);
			Integer counter= resultset.getInt(7);
			
			User u= new User(username,password,completeName,email,role,status,counter);
			
			list.add(u);
		    
		} while (resultset.next());

	}catch(Exception e)
	{
		System.out.println("inside displayall of userrepo");
	}
		
return list;

}



}

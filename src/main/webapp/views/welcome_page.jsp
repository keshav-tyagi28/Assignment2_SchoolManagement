<%@page import="com.osttra.to.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<style>
    body, html {
      height: 100%;
      margin: 0;
      display: flex;
      justify-content: center;
      align-items: center;
    }
  </style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div class="container">
  <div class="row">
    <div class="col-md-12">
      ${updateSuccessMsg}
    </div>
  </div>


 <table class="table">
    <thead>
      <tr>
        <th scope="col" class="col-lg-3">Username</th>
        <th scope="col" class="col-lg-3">Complete Name</th>
        <th scope="col" class="col-lg-3">Email</th>
        <th scope="col" class="col-lg-3">Role</th>
        <th scope="col" class="col-lg-3">Update</th>
        <th scope="col" class="col-lg-3">Delete</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>${user.getUsername()}</td>
        <td>${user.getCompleteName()}</td>
        <td>${user.getEmail()}</td>
        <td>${user.getRole()}</td>
         <td><a href="/update/${user.getUsername() }"> Update </a></td>
          <td><a href="/delete/${user.getUsername() }"> Delete </a></td>
      </tr>
    </tbody>
  </table>

		<%
  // Retrieve the 'user' object from the session
  User user = (User) session.getAttribute("user");

  if (user != null && "Teacher".equals(user.getRole())) {
%>
  <a href="/displayallstudents" class="btn btn-primary">View All Students</a>
<%
  }
%>
<br>

	<%
	if (user != null && "Admin".equals(user.getRole())) {
%>
  <a href="/displayallusers" class="btn btn-primary">View All Users</a>
<%
  }
%>
	
<hr>

<a href="/logout"><button class="btn btn-info">LOGOUT</button></a>	
	
		
		
		</body>
</html>
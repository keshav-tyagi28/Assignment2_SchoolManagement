<%@page import="java.util.List"%>
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

<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h5> Available Entries </h5>

 <table class="table">
    <thead>
      <tr>
        <th scope="col" >Username</th>
        <th scope="col" >Complete Name</th>
        <th scope="col" >Email</th>
        <th scope="col">Role</th>
        <th scope="col">Status</th>
        
        <th scope="col">Delete </th>
    
         <th scope="col">Update </th>
        <th scope="col">Update Status </th>
      </tr>
    </thead>

<tbody>
<% 
List<User> list = (List<User>) request.getAttribute("users");
 User user= (User) session.getAttribute("user"); 

for (User u : list) {
%>

<tr>
  <td> <%= u.getUsername() %> </td>
  <td> <%= u.getCompleteName() %> </td>
  <td> <%= u.getEmail() %> </td>
  <td> <%= u.getRole() %> </td>
  <td>  <%= u.getStatus() %></td>
  
  <% if(  !(user.getRole().equals("Teacher"))) {%>
  
  
  <% if(!(u.getRole().equals("Admin"))){ %>
  <td><a href="/delete/<%= u.getUsername() %>">  <button> delete </button> </a> </td>
  <%}else{ %>
  <td></td>
  <%} %>
  <td><a href="/admin/update/<%= u.getUsername() %>">  <button> Update </button> </a> </td>
  <td>

  <% if (u.getStatus().equals("inactive")) { %>
    <a href="<%= u.getUsername() %>/updatestatus/active"> <button class="btn btn-primary">Activate</button>  </a>
<% } else { %>
    <a href="<%= u.getUsername() %>/updatestatus/inactive"> <button class="btn btn-primary">Disable</button>  </a>
<% } %>
    <a href="<%= u.getUsername() %>/updatestatus/block"> <button class="btn btn-danger">Block </button>  </a>
  </td>
  <%} %>
</tr>

 
<% 
}
%>

</tbody>
</table>

<a href="/welcome/${user.getUsername()}"> <button class="btn btn-info"> Back</button> </a>
</body>
</html>
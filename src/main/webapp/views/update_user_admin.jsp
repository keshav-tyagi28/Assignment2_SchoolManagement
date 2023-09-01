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

 <% User user= (User)request.getAttribute("needupdate"); 
 	System.out.println(user);
 %> -

	<h1 style="margin-left: 450px">Osttra Book Management</h1>
	<h2 style="margin-left: 450px; color: red;">Provide your Details
		here!!</h2>
		
	<div style="margin-left: 450px; margin-right: 500px">
		<form action="/admin/update" method="post">
			<div class="mb-3">
				<label for="exampleFormControlInput1" class="form-label">Username</label>
				<input type="text" name="username" readonly value="<%= user.getUsername() %>" class="form-control">
			</div>
			
			<div class="mb-3">
				<label for="exampleFormControlInput1" class="form-label">Complete
					Name</label> <input type="text" name="completeName" value="<%= user.getCompleteName() %>" class="form-control">
			</div>
			<div class="mb-3">
				<label for="exampleFormControlInput1" class="form-label">Email</label>
				<input type="text" name="email" value="<%=  user.getEmail() %>" class="form-control">
			</div>



			<button style="margin-left: 130px" type="submit"
				class="btn btn-primary">Update</button>
			<br>
		</form>
		
	</div>


</body>
</html>
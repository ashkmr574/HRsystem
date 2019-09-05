<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Admin</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   <link href="<c:url value="/resources/style.css" />"
    rel="stylesheet">
<script type="text/javascript">
function validate()
{
		var password=document.getElementById("password").value;
		var cnf_password=document.getElementById("confirm_password").value;
		if(password.length==0 && cnf_password.length==0)
			document.getElementById("message").innerHTML="";
		else if(password==cnf_password)
		{
			document.getElementById("message").innerHTML="password match";
			document.getElementById("submit").disabled = false;
			document.getElementById("message").style.color="green";
		}
		else
		{
			document.getElementById("message").innerHTML="password does not match";
			document.getElementById("submit").disabled = true;
			document.getElementById("message").style.color="red";
		}
}
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip({
        placement : 'top'
    });
});

</script>
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="home">JobPortalAdmin</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="home">Home</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
    	<li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-user"></span> Welcome ${name}
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
			<li><a href='add_admin'>Add Another Admin</a></li>
			<li><a href='change_adminpassword'>Change Password</a></li>
			<li> <a href='logout'>Logout</a> </li>
        </ul>
      </li>
     </ul>
  </div>
</nav>
<div class="container-fluid">
<div class="jumbotron">
  <h1>Job Search Portal</h1> 
  <p>Welcome to the Largest Job Search Portal</p> 
</div>
</div>
<div class="container">
<form action ='register_admin' method='post' class='form-horizontal' >
<div class="panel panel-info job-info">
<div class="panel-body job-post">
<h1>Add a Admin</h1>
<div class="form-group">
		<label class="control-label col-sm-4" for="username">UserName</label>
		<div class="col-sm-6">
		<input type='text' name='admin.username'  value='${model.admin.username }' class="form-control" required>
		</div>
		<span class="red">${error.get("username") }</span>
		<span>${msg }</span>
	</div>
<div class="form-group">
		<label class="control-label col-sm-4" for="password">Password</label>
		<div class="col-sm-6">
		<input type='password' name='admin.password' id='password'  pattern="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$" onchange='validate()' class="form-control" required>
		</div>
		<span class="red">${error.get("password") }</span>
		<div class="col-sm-1">
		<span class="glyphicon glyphicon-info-sign" data-toggle="tooltip" data-original-title="Password should contain at least one UpperCase,one lowerCase,one special character, one number and length should be at least 8 "></span>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="password_cnf">Confirm Password</label>
		<div class="col-sm-6">
		<input type='password' name='confirm_password' id='confirm_password'  onkeyup='validate()' class="form-control" required>
		</div>
		<div class="col-sm-2">
		<span id='message'></span>
		</div>
	</div>
	</div>
	</div>
	<div class="panel panel-info job-info">
<div class="panel-body job-post">
<h1>Personal Details</h1>
<div class="form-group">
		<label class="control-label col-sm-4" for="name">Name:</label>
		<div class="col-sm-6">
		<input type='text' name='admindetails.name'  value='${model.admindetails.name }' class="form-control" required>
		</div>
		<span>${error.get("name") }</span>
</div>
<div class="form-group">
		<label class="control-label col-sm-4" for="email">Email:</label>
		<div class="col-sm-6">
		<input type='email' name='admindetails.email'  value='${model.admindetails.email }' class="form-control" required>
		</div>
		<span>${error.get("email") }</span>
</div>
<div class="form-group">
		<label class="control-label col-sm-4" for="contact">Contact Number:</label>
		<div class="col-sm-6">
		<input type='text' name='admindetails.contact' onkeypress='return event.charCode >= 48 && event.charCode <= 57'  maxlength="10"  pattern=".{10,}"  value='${model.admindetails.contact }' class="form-control" required>
		</div>
		<span>${error.get("contact") }</span>
</div>
</div>
</div>
<div align="center">
<input type='submit' name='submit' value='Add' id="submit" class="btn btn-success">
</div>
</form>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Password Change</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link href="<c:url value="/resources/style.css" />"
    rel="stylesheet">
<script type="text/javascript">
function change()
{
	document.getElementById('msg').style.color="red";
}
function validate()
{
		var password=document.getElementById("password_new").value;
		var cnf_password=document.getElementById("password_cnf").value;
		if(password==cnf_password)
		{
			document.getElementById("message").innerHTML="password match";
			document.getElementById("submit").disabled = false;
		}
		else
		{
			document.getElementById("message").innerHTML="password does not match";
			document.getElementById("submit").disabled = true;
		}
}
</script>
</head>
<c:if test="${firstlogin=='false'}">
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
</c:if>
<body onload="change()">
<div class="container-fluid">
<div class="jumbotron">
  <h1>Job Search Portal</h1> 
  <p>Welcome to the Largest Job Search Portal</p> 
</div>
</div>
<div class="container">
	<form method='post' action='changepassword' class="form-horizontal">
	<p><span id='msg'>${msg}</span></p>
	<div class="form-group">
		<label class="control-label col-sm-4" for="password">Current Password:</label>
		<div class="col-sm-6">
			<input type='password' name='password' class="form-control" required>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="new_password">New Password:</label>
		<div class="col-sm-6">
			<input type='password' name='password_new' id='password_new' pattern="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$" class="form-control"  onchange="validate()" required>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="password_cnf">Confirm New Password:</label>
		<div class="col-sm-6">
			<input type='password' name='password_cnf' id='password_cnf' onkeyup='validate()' class="form-control" required>
			<span id='message'></span>
		</div>
	</div>
	<div align="center">
		<input type='submit' name='submit' id ='submit' value='Change Password' class="btn btn-success">
	</div>
	</form>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Recover Password</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   <link href="<c:url value="/resources/style.css" />"
    rel="stylesheet">
<script type="text/javascript">
function validate()
{
		var password=document.getElementById("password_new").value;
		var cnf_password=document.getElementById("password_cnf").value;
		if(password.length==0 && cnf_password.length==0)
		{
			document.getElementById("message").innerHTML="";
			document.getElementById("submit").disabled = false;
		}
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
<div class="container-fluid">
<div class="jumbotron">
  <h1>Job Search Portal</h1> 
  <p>Welcome to the Largest Job Search Portal</p> 
</div>
</div>
<div class="container">
	<form method='post' action='confirm_recover' class="form-horizontal">
  	<div class="form-group">
    <label class="control-label col-sm-4" for="new_pass">New Password:</label>
    <div class="col-sm-6">
    <input type="password" class="form-control" name="password_new" id="password_new" pattern="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$" onchange='validate()'>
    </div>
  	<div class="col-sm-1">
					<span class="glyphicon glyphicon-info-sign" data-toggle="tooltip" data-original-title="Password should contain at least one UpperCase,one lowerCase,one special character, one number and length should be at least 8 "></span>
	</div>
	</div>
  	<div class="form-group">
    <label class="control-label col-sm-4" for="pass">Confirm New Password:</label>
    <div class="col-sm-6">
    <input type='password' name='password_cnf' id='password_cnf'  class="form-control" onkeyup='validate()'>
    <span id='message'></span>
    </div>
  	</div>
  	<div align="center">
  	<button type="submit" class="btn btn-success" id="submit">Submit</button>
  	</div>
	</form>
	
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>
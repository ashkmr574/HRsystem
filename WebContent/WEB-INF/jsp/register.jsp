<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link href="<c:url value="/resources/style.css" />"
    rel="stylesheet">
  <script type="text/javascript">
  $(document).ready(function(){
	    $('[data-toggle="tooltip"]').tooltip({
	        placement : 'top'
	    });
	});
function myUpper() {
    var x = document.getElementById("name");
    x.value = x.value.toUpperCase();
}
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
			document.getElementById("message").style.color="red";
			document.getElementById("submit").disabled = true;
		}
}
function validate1()
{
		var password=document.getElementById("password1").value;
		var cnf_password=document.getElementById("confirm_password1").value;
		if(password.length==0 && cnf_password.length==0)
			document.getElementById("message1").innerHTML="";
		else if(password==cnf_password)
		{
			document.getElementById("message1").innerHTML="password match";
			document.getElementById("submit1").disabled = false;
			document.getElementById("message1").style.color="green";
		}
		else
		{
			document.getElementById("message1").innerHTML="password does not match";
			document.getElementById("message1").style.color="red";
			document.getElementById("submit1").disabled = true;
		}
}
function checkemail()
{
		var email=$('#email').val();
		$.ajax({
			type:"POST",
			url:"checkemail",
			data:"email="+email,
			success:function(response)
			{
				if(response=='email exists')
				{
					$('#msg_email').html("email already exists");
					$('#email').val('');
				}
				else
				{
					$('#msg_email').html("");
				}
				
			},
			error: function(e)
			{
				alert('Error: ' + e);
			}

		}
		);
}
function checkuser()
{
		var user=$('#user').val();
		$.ajax({
			type:"POST",
			url:"checkuser",
			data:"user="+user,
			success:function(response)
			{
				if(response=='user exists')
				{
					$('#msg').html("user already exists");
					$('#user').val('');
				}
				else
				{
					$('#msg').html("");
				}
				
			},
			error: function(e)
			{
				alert('Error: ' + e);
			}

		}
		);
}

function checkemployer()
{
		var user=$('#users').val();
		$.ajax({
			type:"POST",
			url:"checkuser",
			data:"user="+user,
			success:function(response)
			{
				if(response=='user exists')
				{
					$('#msg1').html("user already exists");
					$('#users').val('');
				}
				else
				{
					$('#msg1').html("");
				}
				
			},
			error: function(e)
			{
				alert('Error: ' + e);
			}

		}
		);
}

function removespace()
{
	var website= document.getElementById('website').value;
	website=website.replace(/\s/g,"");
	document.getElementById('website').value=website;
}
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
<div class="row">
<ul class="nav nav-pills">
	<c:choose>
		<c:when test="${type=='employer' }">
			<li ><a data-toggle="pill" href="#jobseeker">Register as JobSeeker</a></li>
   			 <li class="active"><a data-toggle="pill" href="#employer">Register as Employer</a></li>
		</c:when>
		<c:otherwise>
			<li class="active"><a data-toggle="pill" href="#jobseeker">Register as JobSeeker</a></li>
    			<li><a data-toggle="pill" href="#employer">Register as Employer</a></li>
		</c:otherwise>
	</c:choose>
  </ul>
<div class="tab-content">
	<c:choose>
		<c:when test="${type=='employer' }">
			 <div id="jobseeker" class="tab-pane fade in">
			 		<%@ include file="register_jobseeker.jsp" %>
			 </div>
			  <div id="employer" class="tab-pane fade in active">
			 		<%@ include file="register_employer.jsp" %>
			 	</div>
		</c:when>
		<c:otherwise>
			 <div id="jobseeker" class="tab-pane fade in active">
  					 <%@ include file="register_jobseeker.jsp" %>
   			 </div>
   			  <div id="employer" class="tab-pane fade in">
   					 <%@ include file="register_employer.jsp" %>
   			 </div>
    </c:otherwise>
	</c:choose>
 </div>
 </div>
 </div>
 <%@ include file="footer.jsp" %>
</body>
</html>
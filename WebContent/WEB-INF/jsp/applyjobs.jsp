<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Apply</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/style.css"
    rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="home">JobPortal</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="home">Home</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
    	<li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-user"></span> Welcome ${name}
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
        		<li><a href="profile">Profile</a></li>
			<li><a href='search_jobs' >Search Jobs</a></li>
			<li><a href='change_password'>Change Password</a></li>
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
	<div class="row" align="center">
	<div class="col-sm-4"></div>
	<div class="col-sm-4">
		<form method='post' action='finalsubmit' class="form-horizontal">
		<h1>Review your Details</h1>
		<div class="form-group">
			<label class="control-label col-sm-4" for="CompanyName">Company Name:</label>
			<div class='col-sm-8'>
				<input name='company_name' value='${jb_detail.company_name}' class="form-control" readonly>
				<input type="hidden" name='company_username' value='${jb_detail.username}'>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-4" for="Post">Post:</label>
			<div class='col-sm-8'>
				<input name='post' value='${jb_detail.post}' class="form-control" readonly>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-4" for="Post">Job Location:</label>
			<div class="col-sm-8">
				<input name='job_location' value='${jb_detail.job_location}' class="form-control" readonly>
				<input type="hidden" name='jobid.job_id' value='${jb_detail.job_id}' class="form-control" >
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-4" for="name">Name:</label>
			<div class="col-sm-8">
				<input name='name' value='${applicant_name}' class="form-control" readonly>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-4" for="contact">Contact:</label>
			<div class="col-sm-8">
				<input name='contact' value='${applicant_contact}' class="form-control" readonly>
			</div>
		</div>
		<button type="submit" class="btn btn-success" id="submit">Submit</button>
		<a href="search_jobs" class="btn btn-success">Back</a>
	</form>
	</div>
	</div>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>
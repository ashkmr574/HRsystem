<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>POST JOB</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   <link href="<c:url value="/resources/style.css" />"
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
          <% if(session.getAttribute("roleId").toString().equals("3")){ %>
			<li><a href='#' >Post Jobs</a></li>
			<li><a href='candidate_management'>Manage Candidates</a></li>
		<%} else{%>
			<li><a href='search_jobs' >Search Jobs</a></li>
		<%} %>
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
	<form method='post' action='submit_job' class="form-horizontal manage">
	<h1>Post A job</h1>
	<div class="form-group">
		<label class="control-label col-sm-2" for="designation">Designation: </label>
		<div class="col-sm-6">
		<input type='text' name='post' value='${jbdetails.post}' class="form-control" required >
		</div>
		<span class="red">${error.get("post") }</span>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="openings">No. of Openings: </label>
		<div class="col-sm-6">
		<input type='number' min='1' name='openings' value='${jbdetails.openings}' class="form-control" required>
		</div>
		<span class="red">${error.get("openings") }</span>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="min_experience">Minimum Experience(in years)</label>
		<div class="col-sm-6">
		<input type='number' min="0" name='min_experience' value='${jbdetails.min_experience}' class="form-control" required>
		</div>
		<span class="red">${error.get("min_experience") }</span>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="ctc">CTC(in Lakhs)</label>
		<div class="col-sm-6">
		<input type='number' name='ctc' min="0" step="0.01" value='${jbdetails.ctc}' class="form-control" required>
		</div>
		<span class="red">${error.get("ctc") }</span>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="location">Job Location</label>
		<div class="col-sm-6">
		<select name='job_location' class="form-control" required>
					<c:choose>
						<c:when test='${jbdetails.job_location.length()>0}'>
						<option value='${jbdetails.job_location}' selected="selected">${jbdetails.job_location}</option>
						</c:when>
						<c:otherwise>
						<option value='' selected="selected">Choose a option</option>
						</c:otherwise>
					</c:choose>
					<option value='PAN India'>PAN India</option>
					<option value='Bangalore'>Bangalore</option>
					<option value='Mumbai'>Mumbai</option>
					<option value='Kolkata'>Kolkata</option>
				</select>
		</div>
		<span class="red">${error.get("job_location") }</span>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="req_skills">Required Skills</label>
		<div class="col-sm-6">
		<textarea name='req_skills' rows='6' cols='30' class="form-control" required>${jbdetails.req_skills}</textarea>
		</div>
		<span class="red">${error.get("req_skills") }</span>
		Put Comma Separated Values
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="description">Job Description</label>
		<div class="col-sm-6">
		<textarea name='job_description' rows='6' cols='30' class="form-control" required>${jbdetails.job_description}</textarea>
		</div>
		<span class="red">${error.get("job_description") }</span>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="last_apply_date">Last Date of Apply</label>
		<div class="col-sm-6">
		<input type="date" name='last_apply_date' value='${jbdetails.last_apply_date}' class="form-control" required>
		</div>
		<span class="red">${error.get("last_apply_date") }</span>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="recruitment_process">Recruitment Process</label>
		<div class="col-sm-6">
		<textarea name='recruitment_process' rows='25' cols='100' class="form-control" required>${jbdetails.recruitment_process}</textarea>
		</div>
		<span class="red">${error.get("recruitment_process") }</span>
	</div>
	<div align="center">
		  <input type='submit' name='submit' value='post' class="btn btn-success">
	 </div>
	</form>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>
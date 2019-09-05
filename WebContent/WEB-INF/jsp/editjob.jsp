<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EDIT JOB</title>
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
			<li><a href='post_jobs' >Post Jobs</a></li>
			<li><a href='candidate_management'>Manage Candidates</a></li>
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
<div class="container job-post">
	<form method='post' action='editjobs' class="form-horizontal">
	<h1>Edit the job</h1>
		<div class="form-group">
			<label class="control-label col-sm-4" for="post">Designation:</label>
			<div class="col-sm-6">
			<input type='text' name='post'  value='${ jb_detail.post}' class="form-control" required><input type="hidden" name='job_id' value=${jb_detail.job_id }>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-4" for="openings">No. of Openings:</label>
			<div class="col-sm-6">
			<input type='number' min='1' name='openings'  value='${ jb_detail.openings}' class="form-control" required>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-4" for="experience">Minimum Experience(in years)</label>
			<div class="col-sm-6">
			<input type='number' min='0' name='min_experience'  value='${ jb_detail.min_experience}' class="form-control" required>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-4" for="ctc">CTC(in Lakhs)</label>
			<div class="col-sm-6">
			<input type='number' name='ctc' step="0.01" min="0"  value='${ jb_detail.ctc}' class="form-control" required>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-4" for="location">Job Location</label>
			<div class="col-sm-6">
			<select name='job_location' required class="form-control">
					<c:choose>
						<c:when test='${jb_detail.job_location.length()>0}'>
							<option value='${jb_detail.job_location}' selected="selected">${jb_detail.job_location}</option>
						</c:when>
						<c:otherwise>
							<option value='' selected="selected">Choose a option</option>
						</c:otherwise>
					</c:choose>
					<option value='PAN India'>PAN India</option>
					<option value='Bangalore'>Bangalore</option>
					<option value='Mumbai'>Mumbai</option>
					<option value='Kolkata'>Kolkata</option>
					<option value="Hydrabaad">Hydrabaad</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-4" for="skills">Required Skills:</label>
			<div class="col-sm-6">
			<textarea name='req_skills' rows='6' cols='30' class="form-control" required>${ jb_detail.req_skills}</textarea>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-4" for="description">Job Description:</label>
			<div class="col-sm-6">
			<textarea name='job_description' rows='6' cols='30'  class="form-control" required>${ jb_detail.job_description}</textarea>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-4" for="apply_date">Last Date of Apply:</label>
			<div class="col-sm-6">
			<input type="date" name='last_apply_date' value='${ jb_detail.last_apply_date}' class="form-control" required>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-4" for="recruit">Recruitment Process:</label>
			<div class="col-sm-6">
			<textarea name='recruitment_process' rows='25' cols='100' class="form-control" required >${ jb_detail.recruitment_process}</textarea>
			</div>
		</div>
		<div align="center">
		<input type='submit' name='submit' value='Update' class="btn btn-success">
	</div>
	</form>
	
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>
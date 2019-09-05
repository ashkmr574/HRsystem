<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   <link href="<c:url value="/resources/style.css" />"
    rel="stylesheet">
<title>List Jobs</title>
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
<div class="row" align="center">
<form method='post' action='criteria_search' class='form-inline'>
<div class="form-group">
  	<label for="criteria">Search Job:</label>
  	<select class="form-control" name='criteria' required>
    		<c:choose>
			<c:when test='${criteria.length()>0}'>
				<option value='${criteria}' selected="selected">${criteria}</option>
			</c:when>
			<c:otherwise>
				<option value='' selected="selected">Choose a option</option>
			</c:otherwise>
			</c:choose>
			<option value='skills'>skills</option>
			<option value='location'>location</option>
			<option value='company'>Company</option>
  	</select>
</div>
<div class="form-group">
	<input type='text' name='search' value='${search}' id='search'  class="form-control" required>	
</div>
	<button type="submit" class="btn btn-success" name='submit'>Search</button>	
</form>	
</div>
<c:if test="${jb_detail.size()==0 }">
<div class="container">
			<div class="row">
			<div class="panel-group">
			<div class="panel panel-info job-info">
			<div class="panel-body job-post">
				<h1 align="center">No Jobs Found.</h1>
			</div>
			</div>
			</div>
			</div>
</div>
</c:if>
<c:forEach items="${jb_detail}" var="job_details">
			<div class="container">
			<div class="row">
			<div class="panel-group">
			<div class="panel panel-info job-info">
			<div class="panel-body job-post">
			<b>Company Name:</b>${job_details.company_name}<br/>
			<b>Post:</b>${job_details.post}<br/>
			<b>Openings:</b>${job_details.openings}<br/>
			<b>Minimum Experience:</b>${job_details.min_experience}+<br/>
			<b>Salary:</b>${job_details.ctc}<br/>
			<b>Job Location:</b>${job_details.job_location}<br/>
			<b>Last Date of Apply:</b>${job_details.last_apply_date}<br/>
			<b>Job Description:</b><br/>
			<p>${job_details.job_description}</p>
			<b>Required Skills:</b><br/>
			<p>${job_details.req_skills}</p>
		    <c:choose>
			<c:when test="${job_details.recruitment_process==null}">
				<b>No Recruitment Process Posted.</b>
			</c:when>
			<c:otherwise>
				<b>Recruitment Process:</b>
				<pre>${job_details.recruitment_process}</pre>
			</c:otherwise>
			</c:choose>
			<a href='apply_${job_details.job_id}' class="btn btn-success">Apply</a>
			</div>
			</div>
			</div>
			</div>
			</div>
</c:forEach>
<%@ include file="footer.jsp" %>
</body>
</html>
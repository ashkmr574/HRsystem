<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Panel</title>
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
      <a class="navbar-brand" href="#">JobPortalAdmin</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">Home</a></li>
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
<fieldset>
<legend><b>Statistics</b></legend>
<div class="table-responsive">
<table class="table table-bordered table-hover">
	<tr>
	<th class="info">Count of Registered JobSeekers</th>
	<td class="success">${jobseeker_count }</td>
	</tr>
	<tr>
		<th class="info">Count of Registered Employers</th>
		<td class="success">${employer_count }</td>
	</tr>
	<tr>
		<th class="info">Count of Job Posted</th>
		<td class="success">${job_count }</td>
	</tr>
	<tr>
		<th class="info">Count of Jobs Applied</th>
		<td class="success">${applied_job_count }</td>
	</tr>
</table>
</div>
</fieldset>
<fieldset>
	<legend><b>List of Registered JobSeekers</b></legend>
	<div class="table-responsive">
	<table class="table table-bordered table-hover">
		<tr class="info">
			<th>username</th>
			<th>name</th>
			<th>email</th>
			<th>contact</th>
			<th>gender</th>
			<th>Date Of Birth</th>
		</tr>
		<c:forEach items="${jobseeker}" var="candidate">
			<tr class="success">
				<td>${candidate.username }</td>
				<td>${candidate.name }</td>
				<td>${candidate.email }</td>
				<td>${candidate.contact }</td>
				<td>${candidate.gender }</td>
				<td>${candidate.dob}</td>
			</tr>
		</c:forEach>
	</table>
	</div>
</fieldset>
<fieldset>
	<legend><b>List of Registered Employers</b></legend>
	<div class="table-responsive">
	<table class="table table-bordered table-hover">
		<tr class="info">
			<th>username</th>
			<th>company name</th>
			<th>email</th>
			<th>website</th>
			<th>contact Person</th>
			<th>contact</th>
			<th>Designation</th>
			<th>Office Address</th>
		</tr>
		<c:forEach items="${employer}" var="candidate">
			<tr class="success">
				<td>${candidate.username }</td>
				<td>${candidate.company_name }</td>
				<td>${candidate.email }</td>
				<td>${candidate.website }</td>
				<td>${candidate.contact_person_name }</td>
				<td>${candidate.contact}</td>
				<td>${candidate.designation}</td>
				<td>${candidate.office_address}</td>
			</tr>
		</c:forEach>
	</table>
	</div>
</fieldset>
<fieldset>
	<legend><b>List of Posted Jobs</b></legend>
	<div class="table-responsive">
	<table class="table table-bordered table-hover">
		<tr class="info">
			<th>Job Id</th>
			<th>username</th>
			<th>company name</th>
			<th>openings</th>
			<th>post</th>
			<th>min experience</th>
			<th>Salary(In Lacs)</th>
			<th>Job Location</th>
		</tr>
		<c:forEach items="${jobs}" var="job">
			<tr class="success">
				<td>${job.job_id }</td>
				<td>${job.username }</td>
				<td>${job.company_name }</td>
				<td>${job.openings }</td>
				<td>${job.post }</td>
				<td>${job.min_experience }</td>
				<td>${job.ctc }</td>
				<td>${job.job_location }</td>
			</tr>
		</c:forEach>
	</table>
	</div>
</fieldset>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
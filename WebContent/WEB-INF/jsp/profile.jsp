<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profile</title>
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
        <li><a href="#">Profile</a></li>
          <% if(session.getAttribute("roleId").toString().equals("3")){ %>
			<li><a href='post_jobs' >Post Jobs</a></li>
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
<c:if test="${msg=='success' }">
<script>
	alert("profile updated successfully!");
</script>
</c:if>
<div class="container">
<a href='editprofile' class="btn btn-success">Edit Profile</a>
<c:choose>
<c:when test="${roleId==2 }">
<div class="panel panel-info job-info">
<div class="panel-body job-post">
<div class="table-responsive">
<fieldset>
	<legend><b>Personal Details</b></legend>
	<table class="table table-hover table-bordered">
	<tr>
	<th class="info">Full Name</th>
	<td class="success">${jobsekkerdetails.personaldetails.name}</td>
	</tr>
	<tr>
	<th class="info">Email</th>
	<td class="success">${jobsekkerdetails.personaldetails.email}</td>
	</tr>
	<tr>
	<th class="info">Gender</th>
	<td class="success">${jobsekkerdetails.personaldetails.gender}</td>
	</tr>
	<tr>
	<th class="info">Date of Birth</th>
	<td class="success">${jobsekkerdetails.personaldetails.dob}</td>
	</tr>
	<tr>
	<th class="info">Contact Number</th>
	<td class="success">${jobsekkerdetails.personaldetails.contact}</td>
	</tr>
	<tr>
		<th class="info">Address</th>
		<td class="success">
		${jobsekkerdetails.personaldetails.address1}
		${jobsekkerdetails.personaldetails.address2}
		${jobsekkerdetails.personaldetails.city}
		${jobsekkerdetails.personaldetails.state}
		${jobsekkerdetails.personaldetails.pincode}
		</td>
	</tr>
	</table>
</fieldset>
</div>
</div>
</div>
<div class="panel panel-info job-info">
<div class="panel-body job-post">
<div class="table-responsive">
<fieldset>
	<legend><b>Professional Details</b></legend>
	<table class="table table-hover table-bordered">
	<tr >
	<th class="info">Total Experience(In Years)</th>
	<td class="success">${jobsekkerdetails.professionaldetails.experience}</td>
	</tr>
	<tr>
	<th class="info">Current CTC(in Lakhs)</th>
	<td class="success">${jobsekkerdetails.professionaldetails.ctc}</td>
	</tr>
	<tr>
	<th class="info">Skills</th>
	<td class="success">${jobsekkerdetails.professionaldetails.skills}</td>
	</tr>
	</table>
</fieldset>
</div>
</div>
</div>
<div class="panel panel-info job-info">
<div class="panel-body job-post">
<div class="table-responsive">
<fieldset>
	<legend><b>Educational Details</b></legend>
<table class="table table-hover table-bordered">
	<tr class="info">
		<th>Degree</th>
		<th>Institute Name</th>
		<th>Year Of passing</th>
		<th>Percentage/cgpa</th>
	</tr>
	<c:forEach items="${jobsekkerdetails.educationaldetails}" var="educational_details">
	<tr class="success">
		<td>${educational_details.degree }</td>
		<td>${educational_details.institute}</td>
		<td>${educational_details.yop }</td>
		<td>${educational_details.marks}</td>
	</tr>
	</c:forEach>
</table>
</fieldset>
</div>
</div>
</div>
</c:when>
<c:otherwise>
<div class="panel panel-info job-info">
<div class="panel-body job-post">
<div class="table-responsive">
<fieldset>
<legend><b>Company Details</b></legend>
<table class="table table-hover table-bordered">
<tr>
<th class="info">Company Name:</th>
<td class="success">${empdetails.company_name }</td>
</tr>
<tr>
<tr>
<th class="info">Company Email:</th>
<td class="success">${empdetails.email}</td>
</tr>
<tr> 
<th class="info">Website</th>
<td class="success">${empdetails.website }</td>
</tr>
<tr>
<th class="info">Contact Person Name</th>
<td class="success">${empdetails.contact_person_name }</td>
</tr>
<tr>
<th class="info">Designation</th>
<td class="success">${empdetails.designation }</td>
</tr>
<tr>
<th class="info">Contact Number</th>
<td class="success">${empdetails.contact}</td>
</tr>
<tr>
<th class="info">Industry Type</th>
<td class="success">${empdetails.industry}</td>
</tr>
<tr >
<th class="info">Office Address</th>
<td class="success">${empdetails.office_address }</td>
</tr>
</table>
</fieldset>
</div>
</div>
</div>
</c:otherwise>
</c:choose>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Candidate Management</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link href="${pageContext.request.contextPath}/resources/css/style.css"
    rel="stylesheet">
    <script>
    function getinfo(user)
	{
    		document.getElementById('user-content').style.display='none';
		var user=document.getElementById(user).innerHTML;
		$.ajax({
			type:"POST",
			url:"getinfo",
			data:"user="+user,
			success:function(response)
			{
				var fields=response.split(',');
				$('#name').html(fields[0]);
				$('#email').html(fields[1]);
				$('#contact').html(fields[2]);
				$('#gender').html(fields[3]);
				$('#dob').html(fields[4]);
				$('#address').html(fields[5]+','+fields[6]+','+fields[7]+','+fields[8]+','+fields[9]);
				document.getElementById('user-content').style.display='block';
				
			},
			error: function(e)
			{
				alert('Error: ' + e);
			}

		}
		);
	}
</script>
<style>
	.hide-first{
		display:none
	}
	.modal {
  text-align: center;
}

@media screen and (min-width: 768px) { 
  .modal:before {
    display: inline-block;
    vertical-align: middle;
    content: " ";
    height: 100%;
  }
}

.modal-dialog {
  display: inline-block;
  text-align: left;
  vertical-align: middle;
}
</style>
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
			<li><a href='#'>Manage Candidates</a></li>
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
<div class="row">
<h3>List Of Candidates Applied:</h3>
<div class="table-responsive manage">
<table class="table table-bordered table-hover">
<tr class="success">
<th>Job Id</th>
<th>Applicant UserName</th>
<th>Post</th>
<th>Job Location</th>
<th>Submission Date</th>
<th>Lock Candidate</th>
<th>Block Candidate</th>
</tr>
<c:set var="i" scope="application" value="0">
</c:set>
<c:forEach items="${jobs}" var="job_details">
		<c:if test="${job_details.status=='submitted'}">
		<c:set var="i" value="${i+1}">
		</c:set>
			<tr class="info">
			<td>${job_details.jobid.job_id}</td>
			<td><a href="#" data-toggle="modal" data-target="#profile"><span id='user${i}' onclick="getinfo('user${i}')">${job_details.jobid.applicant_username}</span></a></td>
			<td>${job_details.post}</td>
			<td>${job_details.job_location}</td>
			<td>${job_details.submission_date}</td>
			<td><a href='lock_${job_details.jobid.job_id}_${job_details.jobid.applicant_username}' class="btn btn-success">Lock</a></td>
			<td><a href='block_${job_details.jobid.applicant_username}' class="btn btn-danger">Block</a></td>
		</tr>
		</c:if>
		<br/>
</c:forEach>
</table>
<c:if test="${i==0 }">
	<h1 align="center">No Candidates Applied.</h1>
</c:if>
</div>
<div class="modal fade" id='profile' role='dialog'>
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
        				<button type="button" class="close" data-dismiss="modal">&times;</button>
        				<h4 class="modal-title">Profile</h4>
      		</div>
     		 <div class="modal-body hide-first" id="user-content">
        			<div class="table-responsive">
        				<table class="table table-hover table-bordered">
        					<tr>
        						<th class='info'>Name</th>
        						<td class='success' id='name'></td>
        					</tr>
        					<tr>
        						<th class='info'>Email</th>
        						<td class='success' id='email'></td>
        					</tr>
        					<tr>
        						<th class='info'>Contact</th>
        						<td class='success' id='contact'></td>
        					</tr>
        					<tr>
        						<th class='info'>Gender</th>
        						<td class='success' id='gender'></td>
        					</tr>
        					<tr>
        						<th class='info'>Date of Birth</th>
        						<td class='success' id='dob'></td>
        					</tr>
        					<tr>
        						<th class='info'>Address</th>
        						<td class='success' id='address'></td>
        					</tr>
        				</table>
        			</div>
      		</div>
     		 <div class="modal-footer">
        				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      		</div>
		</div>
  	</div>
</div>
</div>
<c:set var="i" value="0">
		</c:set>
<div class="row">
<h3>List of Locked Candidates</h3>
<div class="table-responsive manage">
<table class="table table-bordered table-hover">
<tr class="success">
<th>Job Id</th>
<th>Applicant UserName</th>
<th>Post</th>
<th>Job Location</th>
<th>Submission Date</th>
<th>Unlock Candidate</th>
</tr>
<c:forEach items="${jobs}" var="job_details">
		<c:if test="${job_details.status=='locked'}">
		<c:set var="i" value="${i+1}">
		</c:set>
			<tr class="info">
			<td>${job_details.jobid.job_id}</td>
			<td>${job_details.jobid.applicant_username}</td>
			<td>${job_details.post}</td>
			<td>${job_details.job_location}</td>
			<td>${job_details.submission_date}</td>
			<td><a href='unlock_${job_details.jobid.job_id}_${job_details.jobid.applicant_username}' class="btn btn-success">Unlock</a></td>
		</tr>
		</c:if>
		<br/>
</c:forEach>
</table>
<c:if test="${i==0 }">
	<h1 align="center">No Locked Candidates</h1>
</c:if>
</div>
</div>
<c:set var="i" value="0">
		</c:set>
<div class="row">
<h3>List of Blocked Candidates</h3>
<div class="table-responsive manage">
<table class="table table-bordered table-hover">
<tr class="success">

<th>Applicant UserName</th>
<th>Unblock Candidate</th>
</tr>
<c:forEach items="${candidate}" var="candidate">
		<c:set var="i" value="${i+1}">
		</c:set>
			<tr class="info">
			<td>${candidate.blockid.applicant_username}</td>
			<td><a href='unblock_${candidate.blockid.applicant_username}' class="btn btn-success">Unblock</a></td>
			</tr>
		<br/>
</c:forEach>
</table>
<c:if test="${i==0 }">
	<h1 align="center">No Blocked Candidates</h1>
</c:if>
</div>
</div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HOME</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link href="${pageContext.request.contextPath}/resources/css/style.css"
    rel="stylesheet">
<script>
	function confirm(link)
	{
		document.getElementById('link').innerHTML=link;
	}
	function deletejob()
	{
		link=document.getElementById('link').innerHTML;
		window.location.href = link;
	}
	function withdrawjob()
	{
		link=document.getElementById('link').innerHTML;
		window.location.href = link;
	}
</script>
<style>
	.hide-first{
		display:block
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
      <a class="navbar-brand" href="#">JobPortal</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">Home</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
    	<li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-user"></span> Welcome ${name}
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
        <li><a href="profile">Profile</a></li>
          <% if(session.getAttribute("type").toString().equals("employer")){ %>
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
<c:if test="${editjob=='true' }">
<script>
	alert("Job updated successfully!");
</script>
</c:if>
<c:if test="${withdraw=='withdrawn' }">
<script>
	alert("Job Withdrawn successfully!");
</script>
</c:if>
<c:if test="${delete=='success' }">
<script>
	alert("Job Deleted successfully!");
</script>
</c:if>
<div class="container">
<div class="panel panel-info job-info">
<div class="panel-body job-post">
<c:choose>
	<c:when test="${type=='employer'}">
		<b>List of Posted Job</b>
		<div class="table-responsive">
		<table class="table table-bordered table-hover ">
		<tr class="success">
			<th>Job Id</th>
			<th>Post</th>
			<th>Openings</th>
			<th>Minimum Experience</th>
			<th>Salary(in Lakhs)</th>
			<th>Job Location</th>
			<th>Last date of Apply</th>
			<th>Delete</th>
			<th>Edit</th>
		</tr>
		<c:forEach items="${jb_detail}" var="job_details">
		<tr class="info">
		<td>${job_details.job_id}</td>
		<td>${job_details.post}</td>
		<td>${job_details.openings}</td>
		<td>${job_details.min_experience}+</td>
		<td>${job_details.ctc}</td>
		<td>${job_details.job_location}</td>
		<td>${job_details.last_apply_date}</td>
		<td><a href='#' class="btn btn-danger" data-toggle="modal" data-target="#confirmdelete" onclick="confirm('delete_${job_details.job_id}')">Delete</a></td>
		<td><a href='edit_${job_details.job_id}' class="btn btn-success">Edit</a></td>
		</tr>
</c:forEach>
</table>
<c:if test="${jb_detail.size()==0 }">
				<h1 align="center">No Jobs Posted.</h1>
		</c:if>
</div>
	</c:when>
	<c:otherwise>
	<b>List of Applied Jobs</b>
	<div class="table-responsove ">
	<table class="table table-bordered table-hover">
<tr class="success">
<th>Job Id</th>

<th>Post</th>
<th>Job Location</th>
<th>Submission Date</th>
<th>Status</th>
<th>Withdraw Application</th>
</tr>
		<c:forEach items="${jobs}" var="job_details">
		
			<tr class="info">
			<td>${job_details.jobid.job_id}</td>
			<td>${job_details.post}</td>
			<td>${job_details.job_location}</td>
			<td>${job_details.submission_date}</td>
			<td>${job_details.status}</td>
			<c:choose>
			<c:when test="${job_details.status=='submitted' or job_details.status=='locked'}">
				<td><a href='#' data-toggle="modal" data-target="#confirmwithdraw" class="btn btn-danger" onclick="confirm('withdraw_${job_details.jobid.job_id}')">Withdraw</a></td>
			</c:when>
			<c:otherwise>
			<td>can't withdraw</td>
			</c:otherwise>
			</c:choose>
		</tr>
</c:forEach>
</table>
<c:if test="${jobs.size()==0 }">
	<h1 align="center">No Jobs Applied.</h1>
</c:if>
</div>
	</c:otherwise>
</c:choose>
</div>
</div>
</div>
<%@ include file="footer.jsp" %>
<div class="modal fade" id='confirmdelete' role='dialog'>
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
        				<button type="button" class="close" data-dismiss="modal">&times;</button>
      		</div>
     		 <div class="modal-body hide-first" id="user-content">
        			Are you Sure want to delete?
      		</div>
     		 <div class="modal-footer">
        				<button type="button" class="btn btn-info" data-dismiss="modal">Cancel</button>
        				<button type="button" class="btn btn-danger" onclick="deletejob()">Ok</button>
      		</div>
		</div>
  	</div>
</div>
<div class="modal fade" id='confirmwithdraw' role='dialog'>
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
        				<button type="button" class="close" data-dismiss="modal">&times;</button>
      		</div>
     		 <div class="modal-body hide-first" id="user-content">
        			Are you Sure want to Withdraw?
      		</div>
     		 <div class="modal-footer">
        				<button type="button" class="btn btn-info" data-dismiss="modal">Cancel</button>
        				<button type="button" class="btn btn-danger" onclick="withdrawjob()">Ok</button>
      		</div>
		</div>
  	</div>
</div>
<span id='link' style="display:none;"></span>
</body>
</html>
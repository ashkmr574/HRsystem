<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Password Recovery</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   <link href="<c:url value="/resources/style.css" />"
    rel="stylesheet">
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
<div class="col-sm-4"></div>
<div class="col-sm-4">
<div class="panel panel-info job-info">
			<div class="panel-body job-post">
	<c:choose>
	<c:when test="${type=='jobseeker' }">
	
	<form method='post' action='jobseeker_recover' class="form-horizontal">
		<span class='red'>${msg}</span>
		<div class="form-group">
				<label class="control-label col-sm-4" for="email">Email:</label>
				<div class="col-sm-8">
				<input type='email' name='email'  class="form-control" required>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="dob">Date Of Birth:</label>
				<div class="col-sm-8">
				<input type='date' name='dob'  class="form-control" required>
				</div>
			</div>
		<div align="center">
			<input type="submit" value="submit" name="submit" class="btn btn-success">
		</div>
	</form>
	</c:when>
	<c:otherwise>
		<form method='post' action='employer_recover' class="form-horizontal">
		<span class='red'>${msg}</span>
		<div class="form-group">
	<label class="control-label col-sm-4" for="company_email">Company Email:</label>
	<div class="col-sm-8">
		<input type='email' name='email'  class="form-control" required>
	</div>
</div>
<div class="form-group">
				<label class="control-label col-sm-4" for="reg_date">Registration Date:</label>
				<div class="col-sm-8">
				<input type='date' name='reg_date'  class="form-control" required>
				</div>
			</div>
			<div align="center">
			<input type="submit" value="submit" name="submit" class="btn btn-success">
		</div>
		</form>
	</c:otherwise>
	</c:choose>
</div>
</div>
</div>
</div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
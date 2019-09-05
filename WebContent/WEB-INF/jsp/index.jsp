<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Login</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link href="<c:url value="/resources/style.css" />" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
<div class="jumbotron">
  <h1>Job Search Portal</h1> 
  <p>Welcome to the Largest Job Search Portal</p> 
</div>
</div>
<div align='right'>
 <a href='administrator' class='btn btn-info'>Admin login</a>
</div>
<div class="container">
<div class='row'>
<div class='col-sm-4'>
</div>
<div class='col-sm-4'>
<form action='login' method='post'>
	<b><span style="color:red;">${message}</span></b><br/>
  <div class="form-group">
  	<label for="type">Select Type:</label>
  		<select class="form-control" id="type" name="type">
    			<option value='employer'>Employer</option>
			<option value='jobseeker'>JobSeeker</option>
  		</select>
	</div>
  <div class="form-group">
    <label for="user">UserName:</label>
    <input type="text" class="form-control" name="user" id="user" autocomplete="off" required>
  </div>
  <div class="form-group">
    <label for="pass">Password:</label>
    <input type="password" class="form-control" name="pass" id="pass" required>
  </div>
  <button type="submit" class="btn btn-success">Login</button>
</form>
<p>Not having account ? <a href='register'>Sign up</a>  <a href="forgot_password">Forgot password?</a></p>
</div>
</div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>

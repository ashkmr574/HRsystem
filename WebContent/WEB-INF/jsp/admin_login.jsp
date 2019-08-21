<html>
<head>
	<title>Login</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link href="${pageContext.request.contextPath}/resources/css/style.css"
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
<form action ='adminlogin' method='post'>
<b><span style="color:red;">${message}</span></b><br/>
  <div class="form-group">
    <label for="user">UserName:</label>
    <input type="text" class="form-control" name="username" id="user" required>
  </div>
  <div class="form-group">
    <label for="pass">Password:</label>
    <input type="password" class="form-control" name="password" id="pass" required>
  </div>
  <button type="submit" class="btn btn-success">Login</button>
</form>
</div>
</div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
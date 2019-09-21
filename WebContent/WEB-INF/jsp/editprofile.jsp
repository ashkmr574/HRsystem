<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EDIT PROFILE</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   <link href="<c:url value="/resources/style.css" />"
    rel="stylesheet">
<script type="text/javascript">
function myUpper() {
    var x = document.getElementById("name");
    x.value = x.value.toUpperCase();
}
function removespace()
{
	var website= document.getElementById('website').value;
	website=website.replace(/\s/g,"");
	document.getElementById('website').value=website;
}
</script>
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
<div class="container">
	<c:choose>
		<c:when test="${roleId==2}">
		<form method='post' action='submiteditedprofile' class="form-horizontal">
		<div class="panel panel-info job-info">
		<div class="panel-body job-post">
		<fieldset>
			<legend><b>Personal Details</b></legend>
			<div class="form-group">
				<label class="control-label col-sm-4" for="name">Full Name:</label>
				<div class="col-sm-6">
				<input type='text' id='name' name="personaldetails.name" onchange="myUpper()"  value='${jobsekkerdetails.personaldetails.name}' class="form-control" required>
				<span class="red">${error.get("personaldetails.name") }</span>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="email">Email:</label>
				<div class="col-sm-6">
				<input type='email' name='personaldetails.email'   value='${jobsekkerdetails.personaldetails.email}'  class="form-control" required>
				<span class="red">${error.get("personaldetails.email") }</span>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="gender">Gender*:</label>
				<div class="col-sm-6">
				<input type='text' name='personaldetails.gender' value='${jobsekkerdetails.personaldetails.gender}'  class="form-control" readonly>
				<span class="red">${error.get("personaldetails.gender") }</span>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="dob">Date Of Birth:</label>
				<div class="col-sm-6">
				<input type='date' name='personaldetails.dob'  value='${jobsekkerdetails.personaldetails.dob}' class="form-control" required>
				<span class="red">${error.get("personaldetails.dob") }</span>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="contact">Contact:</label>
				<div class="col-sm-6">
				<input type='text' name='personaldetails.contact' onkeypress='return event.charCode >= 48 && event.charCode <= 57'  maxlength="10"  pattern=".{10,}"   value='${jobsekkerdetails.personaldetails.contact}' class="form-control" required>
				<span class="red">${error.get("personaldetails.contact") }</span>
				</div>
			</div>			
			<h3>Address</h3>
			<div class="form-group">
				<label class="control-label col-sm-4" for="address1">Line 1:</label>
				<div class="col-sm-6">
				<input type='text' name='personaldetails.address1' placeholder='Address line1'   value='${jobsekkerdetails.personaldetails.address1}' class="form-control" required>
				<span class="red">${error.get("personaldetails.address1") }</span>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="address2">Line 2:</label>
				<div class="col-sm-6">
				<input type='text' name='personaldetails.address2' placeholder='Address line2'  value='${jobsekkerdetails.personaldetails.address2}' class="form-control" required>
				<span class="red">${error.get("personaldetails.address2") }</span>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="city">City:</label>
				<div class="col-sm-6">
				<input type='text' name='personaldetails.city' placeholder='City'   value='${jobsekkerdetails.personaldetails.city}' class="form-control" required>
				<span class="red">${error.get("personaldetails.city") }</span>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="pin">Pin Code:</label>
				<div class="col-sm-6">
				<input type='text' name='personaldetails.pincode' placeholder='Pincode'  onkeypress='return event.charCode >= 48 && event.charCode <= 57'  maxlength="6"   pattern=".{6,}"  value='${jobsekkerdetails.personaldetails.pincode}' class="form-control" required>
				<span class="red">${error.get("personaldetails.pincode") }</span>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="state">State:</label>
				<div class="col-sm-6">
				<input type='text' name='personaldetails.state' placeholder='State'   value='${jobsekkerdetails.personaldetails.state}' class="form-control" required>
				<span class="red">${error.get("personaldetails.state") }</span>
			</div>
			</div>
			</fieldset>
			</div>
			</div>
			<div class="panel panel-info job-info">
		<div class="panel-body job-post">
		<fieldset>
			<legend><b>Professional Details</b></legend>
			<div class="form-group">
				<label class="control-label col-sm-4" for="experience">Total Experience(In Years):</label>
				<div class="col-sm-6">
					<input type='number'  step="0.01" name='professionaldetails.experience' min="0"  value='${jobsekkerdetails.professionaldetails.experience}' class="form-control" required>
					<span class="red">${error.get("professionaldetails.experience") }</span>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="ctc">Current CTC(in Lakhs):</label>
				<div class="col-sm-6">
					<input type='number' step="0.01" min="0" name='professionaldetails.ctc' value='${jobsekkerdetails.professionaldetails.ctc}' class="form-control" required>
					<span class="red">${error.get("professionaldetails.ctc") }</span>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="skills">Skills:</label>
				<div class="col-sm-6">
					<input type="text" name="professionaldetails.skills" value='${jobsekkerdetails.professionaldetails.skills}' class="form-control" required>
					<span class="red">${error.get("professionaldetails.skills") }</span>
				</div>
			</div>
		</fieldset>
		</div>
		</div>
		<div class="panel panel-info job-info">
		<div class="panel-body job-post">
		<fieldset>
	<legend><b>Educational Details</b></legend>
<div class="table-responsive">
<table class="table table-boredered table-hover">
	<tr class="info">
		<th>Degree</th>
		<th>Institute Name</th>
		<th>Year Of passing</th>
		<th>Percentage/cgpa</th>
	</tr>
	<tr id="row1">
		<td><input type='text' name='educationaldetails[0].degree'  value='${jobsekkerdetails.educationaldetails[0].degree}' required></td>
		<td><input type='text' name='educationaldetails[0].institute'   value='${jobsekkerdetails.educationaldetails[0].institute}' required></td>
		<td><input type='text' name='educationaldetails[0].yop' onkeypress='return event.charCode >= 48 && event.charCode <= 57'  maxlength="4"  pattern=".{4,}"   value='${jobsekkerdetails.educationaldetails[0].yop}' required></td>
		<td><input type='number'  step="0.01" min="0" name='educationaldetails[0].marks'  value='${jobsekkerdetails.educationaldetails[0].marks}' required></td>
		<td><input type="hidden" name='educationaldetails[0].row_no' value='${jobsekkerdetails.educationaldetails[0].row_no}'></td>
	</tr>
	<tr id="row2">
		<td><input type='text' name='educationaldetails[1].degree' value='${jobsekkerdetails.educationaldetails[1].degree}'></td>
		<td><input type='text' name='educationaldetails[1].institute' value='${jobsekkerdetails.educationaldetails[1].institute}'></td>
		<td><input type='text' name='educationaldetails[1].yop' onkeypress='return event.charCode >= 48 && event.charCode <= 57'  pattern=".{4,}" maxlength="4" value='${jobsekkerdetails.educationaldetails[1].yop}'></td>
		<td ><input type='number'  step="0.01" min="0" name='educationaldetails[1].marks' value='${jobsekkerdetails.educationaldetails[1].marks}' id='marks1'></td>
		<td><input type="hidden" name='educationaldetails[1].row_no' value='${jobsekkerdetails.educationaldetails[1].row_no}'></td>
	</tr>
	<tr id="row3">
		<td><input type='text' name='educationaldetails[2].degree' value='${jobsekkerdetails.educationaldetails[2].degree}'></td>
		<td><input type='text' name='educationaldetails[2].institute' value='${jobsekkerdetails.educationaldetails[2].institute}'></td>
		<td><input type='text' name='educationaldetails[2].yop' onkeypress='return event.charCode >= 48 && event.charCode <= 57'  pattern=".{4,}" maxlength="4" value='${jobsekkerdetails.educationaldetails[2].yop}'></td>
		<td ><input type='number'  step="0.01" min="0" name='educationaldetails[2].marks' value='${jobsekkerdetails.educationaldetails[2].marks}' id='marks2'></td>
		<td><input type="hidden" name='educationaldetails[2].row_no' value='${jobsekkerdetails.educationaldetails[2].row_no}'></td>
	</tr>
</table>
</div>
</fieldset>
</div>
</div>
<div align="center">
	<input type='submit' name='submit' value='Update' id="submit" class="btn btn-success">
	</div>
	</form>
</c:when>
<c:otherwise>
	<form method='post' action='submiteditprofile' class="form-horizontal">
	<div class="panel panel-info job-info">
		<div class="panel-body job-post">
	<fieldset>
<legend><b>Company Details</b></legend>
			<div class="form-group">
				<label class="control-label col-sm-4" for="company_name">Company Name:</label>
				<div class="col-sm-6">
				<input type='text' id='name' name="company_name"  value='${empdetails.company_name }'  class="form-control" required>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="company_email">Company Email:</label>
				<div class="col-sm-6">
				<input type='email' name='email' value='${empdetails.email}' class="form-control" required>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="website">Website:</label>
				<div class="col-sm-6">
				<input type='text' name='website'value='${empdetails.website }' class="form-control" id='website' onchange ='removespace()' required>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="contatc_person">Contact Person Name:</label>
				<div class="col-sm-6">
				<input type='text' name='contact_person_name' value='${empdetails.contact_person_name }' class="form-control" required>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="designation">Designation:</label>
				<div class="col-sm-6">
				<input type='text' name='designation' value='${empdetails.designation }' class="form-control" required>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="contact">Contact Number:</label>
				<div class="col-sm-6">
				<input type='text' name='contact' onkeypress='return event.charCode >= 48 && event.charCode <= 57'  class="form-control" maxlength="10"  pattern=".{10,}" value='${empdetails.contact}' required>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="industry">Industry Type:</label>
				<div class="col-sm-6">
				<select name='industry' class="form-control" required>
							<c:choose>
						<c:when test='${empdetails.industry.length()>0}'>
							<option value='${empdetails.industry}' selected="selected">${empdetails.industry}</option>
						</c:when>
						<c:otherwise>
							<option value='' selected="selected">Choose a option</option>
						</c:otherwise>
					</c:choose>
							<option value="IT">IT</option>
							<option value="BPO">BPO</option>
				</select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="office_address">Office Address:</label>
				<div class="col-sm-6">
				<textarea name='office_address' rows='6' cols='30' class="form-control" required>${empdetails.office_address }</textarea>
				</div>
			</div>
			</fieldset>
			</div>
			</div>
			<div align="center">
				<input type='submit' name='submit' value='Update' id="submit" class="btn btn-success">
			</div>
	</form>
</c:otherwise>
</c:choose>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
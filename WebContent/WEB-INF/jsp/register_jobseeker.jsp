<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<form method='post'  action='register_jobseeker' class="form-horizontal">
   <div class="panel panel-info job-info">
   <div class="panel-body job-post">
			<div class="form-group">
				<label class="control-label col-sm-4" for="username">Username:</label>
				<div class="col-sm-6">
				<input type='text' name='users.username'  value='${jobsekkerdetails.users.username }' id='user' onchange="checkuser()"  class="form-control"required>
				<span class="red">${error.get("users.username") }</span>
				<span id='msg' class="red"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="password">Password:</label>
				<div class="col-sm-6">
				<input type='password' name='users.password' id='password'  pattern="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$" onchange="validate()" class="form-control" required>
				<span class="red">${error.get("users.password") }</span>
				</div>
				<div class="col-sm-1">
					<span class="glyphicon glyphicon-info-sign" data-toggle="tooltip" data-original-title="Password should contain at least one UpperCase,one lowerCase,one special character, one number and length should be at least 8 "></span>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="password_cnf">Confirm Password:</label>
				<div class="col-sm-6">
				<input type='password' name='confirm_password' id='confirm_password'  onkeyup='validate()' class="form-control" required>
				<span id='message'></span>
				</div>
			</div>
</div>
</div>
 <div class="panel panel-info job-info">
   <div class="panel-body job-post">
<h1>Personal Details</h1>
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
				<input type='radio' name='personaldetails.gender' value='male' class="radio-inline" required>Male <input type='radio' name='personaldetails.gender' value='female'  class="radio-inline" required>Female
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
</div>
</div>
 <div class="panel panel-info job-info">
   <div class="panel-body job-post">
<h1>Professional Details</h1>
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
					<div class="col-sm-6">
							(Put 0 if fresher)
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-4" for="skills">Skills:</label>
				<div class="col-sm-6">
					<input type="text" name="professionaldetails.skills" value='${jobsekkerdetails.professionaldetails.skills}' class="form-control" required>
					<span class="red">${error.get("professionaldetails.skills") }</span>
					<div class="col-sm-6">
							(Put Comma separated values)
					</div>
				</div>
				
			</div>
</div>
</div>
<div class="panel panel-info job-info">
<div class="panel-body job-post">
<fieldset>
	<legend><b>Educational Details</b></legend>
	<div class="table-responsive">
<table class="table table-bordered table-hover">
	<tr >
		<th class="info">Degree</th>
		<th class="info">Institute Name</th>
		<th class="info">Year Of passing</th>
		<th class="info">Percentage/cgpa</th>
	</tr>
	<tr id="row1">
		<td><input type='text' name='educationaldetails[0].degree'  value='${jobsekkerdetails.educationaldetails[0].degree}' required></td>
		<td><input type='text' name='educationaldetails[0].institute'   value='${jobsekkerdetails.educationaldetails[0].institute}' required></td>
		<td><input type='text' name='educationaldetails[0].yop' onkeypress='return event.charCode >= 48 && event.charCode <= 57'  maxlength="4"  pattern=".{4,}"   value='${jobsekkerdetails.educationaldetails[0].yop}' required></td>
		<td><input type='number'  step="0.01" min="0" name='educationaldetails[0].marks'   value='${jobsekkerdetails.educationaldetails[0].marks}' min="0" max="100" required></td>
	</tr>
	<tr id="row2">
		<td><input type='text' name='educationaldetails[1].degree'  value='${jobsekkerdetails.educationaldetails[1].degree}' required></td>
		<td><input type='text' name='educationaldetails[1].institute' value='${jobsekkerdetails.educationaldetails[1].institute}' required></td>
		<td><input type='text' name='educationaldetails[1].yop' onkeypress='return event.charCode >= 48 && event.charCode <= 57'  pattern=".{4,}" maxlength="4"  value='${jobsekkerdetails.educationaldetails[1].yop}' required></td>
		<td><input type='number'  step="0.01" min="0" name='educationaldetails[1].marks'  value='${jobsekkerdetails.educationaldetails[1].marks}' min="0" max="100" required></td>
	</tr>
	<tr id="row3">
		<td><input type='text' name='educationaldetails[2].degree'   value='${jobsekkerdetails.educationaldetails[2].degree}' required></td>
		<td><input type='text' name='educationaldetails[2].institute'   value='${jobsekkerdetails.educationaldetails[2].institute}' required></td>
		<td><input type='text' name='educationaldetails[2].yop' onkeypress='return event.charCode >= 48 && event.charCode <= 57'  pattern=".{4,}" maxlength="4"   value='${jobsekkerdetails.educationaldetails[2].yop}' required></td>
		<td><input type='number'  step="0.01" min="0" name='educationaldetails[2].marks'  value='${jobsekkerdetails.educationaldetails[2].marks}'  min="0" max="100" required></td>
	</tr>
</table>
</div>
</fieldset>
	</div>
	</div>
	<div align="center">
			<input type='submit' name='submit' value='Register' id="submit" class="btn btn-success">
		</div>
</form>
</body>
</html>
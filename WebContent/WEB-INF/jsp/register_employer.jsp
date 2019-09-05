<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<form action ='register_employer' method='post' class="form-horizontal">
    <div class="panel panel-info job-info">
   <div class="panel-body job-post">
<fieldset>
	<div class="form-group">
		<label class="control-label col-sm-4" for="username">Username:</label>
		<div class="col-sm-6">
			<input type='text' name='users.username'  id='users' value='${empdetails.users.username }' onchange="checkemployer()" class="form-control" required>
			<span class="red" id='msg1'>${msg}</span>
			<span class="red">${error.get("users.username") }</span>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="password">Password:</label>
		<div class="col-sm-6">
			<input type='password' name='users.password' id='password1'  pattern="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$" onchange="validate1()" class="form-control" required>
			<span class="red">${error.get("users.password") }</span>
		</div>
		<div class="col-sm-1">
			<span class="glyphicon glyphicon-info-sign" data-toggle="tooltip" data-original-title="Password should contain at least one UpperCase,one lowerCase,one special character, one number and length should be at least 8 "></span>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-4" for="password_cnf">Confirm Password:</label>
			<div class="col-sm-6">
				<input type='password' name='confirm_password' id='confirm_password1'  onkeyup='validate1()' class="form-control" required>
				<span id='message1'></span>
		</div>
	</div>
</fieldset>
</div>
</div>
<div class="panel panel-info job-info">
 <div class="panel-body job-post">
<fieldset>
<legend><b>Company Details</b></legend>
<div class="form-group">
	<label class="control-label col-sm-4" for="company_name">Company Name:</label>
	<div class="col-sm-6">
		<input type='text' id='name' name="employer.company_name"  value='${empdetails.employer.company_name }' class="form-control" required>
		<span class="red">${error.get("employer.company_name") }</span>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-sm-4" for="company_email">Company Email:</label>
	<div class="col-sm-6">
		<input type='email' name='employer.email' value='${empdetails.employer.email}' class="form-control" required>
		<span class="red">${error.get("employer.email") }</span>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-sm-4" for="website">Website:</label>
	<div class="col-sm-6">
		<input type='text' name='employer.website'value='${empdetails.employer.website }' class="form-control" id='website' onchange="removespace()" required>
		<span class="red">${error.get("employer.website") }</span>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-sm-4" for="person_name">Contact Person Name:</label>
	<div class="col-sm-6">
		<input type='text' name='employer.contact_person_name' value='${empdetails.employer.contact_person_name }' class="form-control" required>
		<span class="red">${error.get("employer.contact_person_name") }</span>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-sm-4" for="designation">Designation:</label>
	<div class="col-sm-6">
		<input type='text' name='employer.designation' value='${empdetails.employer.designation }' class="form-control" required>
		<span class="red">${error.get("employer.designation") }</span>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-sm-4" for="contact">Contact Number:</label>
	<div class="col-sm-6">
		<input type='text' name='employer.contact' onkeypress='return event.charCode >= 48 && event.charCode <= 57'  maxlength="10"   pattern=".{10,}" value='${empdetails.employer.contact}' class="form-control" required>
		<span class="red">${error.get("employer.contact") }</span>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-sm-4" for="industry">Industry Type:</label>
	<div class="col-sm-6">
		<select name='employer.industry' class="form-control" required>
			<option value='' selected="selected">Choose...</option>
			<option value="IT">IT</option>
			<option value="BPO">BPO</option>
		</select>
		<span class="red">${error.get("employer.industry") }</span>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-sm-4" for="office_address">Office Address:</label>
	<div class="col-sm-6">
		<textarea name='employer.office_address' rows='6' cols='30' class="form-control" required>${empdetails.employer.office_address }</textarea>
		<span class="red">${error.get("employer.office_address") }</span>
	</div>
</div>
</fieldset>
</div>
</div>
<div align="center">
<input type='submit' name='submit' value='Register' id="submit1" class="btn btn-success">
</div>
</form>
</body>
</html>
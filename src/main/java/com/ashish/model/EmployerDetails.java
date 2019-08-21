package com.ashish.model;

import javax.validation.Valid;

import com.ashish.dto.Employer;
import com.ashish.dto.Users;

public class EmployerDetails 
{
	@Valid
	private Users users;
	@Valid
	private Employer employer;
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public Employer getEmployer() {
		return employer;
	}
	public void setEmployer(Employer employer) {
		this.employer = employer;
	}
	
}

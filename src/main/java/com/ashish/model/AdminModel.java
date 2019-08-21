package com.ashish.model;

import javax.validation.Valid;

import com.ashish.dto.Admin;
import com.ashish.dto.AdminDetails;

public class AdminModel 
{
	@Valid
	private Admin admin;
	
	@Valid
	private AdminDetails admindetails;
	
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public AdminDetails getAdmindetails() {
		return admindetails;
	}
	public void setAdmindetails(AdminDetails admindetails) {
		this.admindetails = admindetails;
	}
	
}

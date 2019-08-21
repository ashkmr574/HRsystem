package com.ashish.model;

import java.util.List;

import javax.validation.Valid;

import com.ashish.dto.EducationalDetails;
import com.ashish.dto.PersonalDetails;
import com.ashish.dto.ProfessionalDetails;
import com.ashish.dto.Users;

public class JobSeekerDetails 
{
	@Valid
	private Users users;
	@Valid
	private PersonalDetails personaldetails;
	@Valid
	private ProfessionalDetails professionaldetails;
	
	@Valid
	private List<EducationalDetails> educationaldetails;
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public PersonalDetails getPersonaldetails() {
		return personaldetails;
	}
	public void setPersonaldetails(PersonalDetails personaldetails) {
		this.personaldetails = personaldetails;
	}
	public ProfessionalDetails getProfessionaldetails() {
		return professionaldetails;
	}
	public void setProfessionaldetails(ProfessionalDetails professionaldetails) {
		this.professionaldetails = professionaldetails;
	}
	public List<EducationalDetails> getEducationaldetails() {
		return educationaldetails;
	}
	public void setEducationaldetails(List<EducationalDetails> educationaldetails) {
		this.educationaldetails = educationaldetails;
	}
	
}

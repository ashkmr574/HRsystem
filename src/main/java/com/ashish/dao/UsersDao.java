package com.ashish.dao;

import java.util.List;

import com.ashish.dto.*;
public interface UsersDao
{
	public Users getPassword(String username);
	public Boolean changePassword(String username,String password);
	public List<AppliedJobs> getApliedJobs(String username);
	public List<JobDetails> getPostedJobs(String username) ;
	public Users isUserExists(String username);
	public PersonalDetails getPersonalDetails(String username);
	public List<EducationalDetails> getEducationalDetailsDetails(String username);
	public ProfessionalDetails getProfessionalDetails(String username);
	public Employer getEmployerProfile(String username);
	
}

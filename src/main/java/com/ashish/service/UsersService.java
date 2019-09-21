package com.ashish.service;

import java.util.List;

import com.ashish.dto.AppliedJobs;
import com.ashish.dto.Employer;
import com.ashish.dto.JobDetails;
import com.ashish.model.JobSeekerDetails;

public interface UsersService 
{
	public Integer login(String username,String password);
	public Boolean changePassword(String username,String current_password,String password);
	public Boolean changePassword(String username,String password);
	public List<AppliedJobs> getAppliedJobs(String username);
	public List<JobDetails> getPostedJobs(String username) ;
	public Integer isUserExists(String username);
	public JobSeekerDetails getProfile(String usrname);
	public Employer getProfile(String username,boolean employer);
}

package com.ashish.service;

import java.util.List;

import com.ashish.dto.AppliedJobs;
import com.ashish.dto.Employer;
import com.ashish.dto.JobDetails;
import com.ashish.model.JobSeekerDetails;

public interface UsersService 
{
	public Boolean login(String username,String password,String usertype);
	public Boolean changePassword(String username,String current_password,String password,String usertype);
	public Boolean changePassword(String username,String password);
	public List<AppliedJobs> getAppliedJobs(String username);
	public List<JobDetails> getPostedJobs(String username) ;
	public String isUserExists(String username);
	public JobSeekerDetails getProfile(String usrname);
	public Employer getProfile(String username,boolean employer);
}

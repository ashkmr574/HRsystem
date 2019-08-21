package com.ashish.service;

import java.util.List;

import com.ashish.dto.Employer;
import com.ashish.dto.JobDetails;
import com.ashish.dto.PersonalDetails;
import com.ashish.model.AdminModel;

public interface AdminService 
{
	public String login(String username,String password);
	public boolean change_password(String username,String password,String password_new,String firstlogin);
	public boolean saveAdmin(AdminModel model);
	public long getEmployerCount();
	public long getJobSeekerCount();
	public long getJobCount();
	public long getAppliedJobCount();
	public List<Employer> getEmployers() ;
	public List<PersonalDetails> getJobSeekers();
	public List<JobDetails> getJobs(boolean status) ;
}

package com.ashish.dao;

import java.util.List;

import com.ashish.dto.Admin;
import com.ashish.dto.Employer;
import com.ashish.dto.JobDetails;
import com.ashish.dto.PersonalDetails;
import com.ashish.model.AdminModel;

public interface AdminDao
{
	public Admin login(String username);
	public boolean change_password(Admin admin);
	public String get_Password(String username);
	public boolean saveAdmin(AdminModel model);
	public boolean isAdminExists(String username);
	public long getEmployerCount();
	public long getJobSeekerCount();
	public long getJobCount();
	public long getAppliedJobsCount();
	public List<Employer> getEmployers() ;
	public List<PersonalDetails> getJobSeekers() ;
	public List<JobDetails> getJobs(boolean status);
}

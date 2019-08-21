package com.ashish.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.ashish.dto.AppliedJobs;
import com.ashish.dto.JobDetails;
import com.ashish.model.JobSeekerDetails;

public interface JobSeekerService 
{
	public boolean saveJobSeeker(JobSeekerDetails js);
	public String getApplicantName(String username);
	public String getContact(String username);
	public JobSeekerDetails getProfile(String username);
	public boolean editProfile(JobSeekerDetails js,String username);
	public boolean isemailexists(String email);
	public boolean isuserexists(String username);
	public boolean validate(String email,Date date,String username);
	public List<JobDetails> getJobsList(String username) throws ParseException;
	public List<JobDetails> getJobsList(String text,String criteria) throws ParseException;
	public JobDetails getJob(int id);
	public boolean isBlocked(String applicant_username, String company_username);
	public String getCompanyUserName(int id);
	public void saveAppliedJobs(AppliedJobs jobs) throws ParseException;
	public void withdraw(int id, String user) ;
}

package com.ashish.dao;


import java.util.Date;
import java.util.List;

import com.ashish.dto.AppliedJobs;
import com.ashish.dto.BlockCandidate;
import com.ashish.dto.EducationalDetails;
import com.ashish.dto.JobDetails;
import com.ashish.dto.JobId;
import com.ashish.dto.PersonalDetails;
import com.ashish.dto.ProfessionalDetails;
import com.ashish.dto.Users;
import com.ashish.model.JobSeekerDetails;

public interface JobSeekerDao 
{
	public String getPassword(String Username);
	public void saveJobSeeker(JobSeekerDetails js);
	public String getApplicantName(String username);
	public String getContact(String username);
	public boolean isuserexists(String username);
	public PersonalDetails getPersonalDetails(String username);
	public List<EducationalDetails> getEducationalDetailsDetails(String username);
	public ProfessionalDetails getProfessionalDetails(String username);
	public boolean updatePersonalDetails(PersonalDetails ps);
	public boolean updateProfessionalDetails(ProfessionalDetails ps);
	public boolean updateEducationalDetails(Users user);
	public String isemailexists(String email);
	public boolean validate(String email,Date date,String username);
	public List<JobId> getJobIds(String username);
	public List<JobDetails> getJobsList(Date date);
	public List<JobDetails> getJobsList(List<Integer> jobids,Date date);
	public List<JobDetails> getJobsListByLocation(String text,Date date);
	public List<JobDetails> getJobsListBySkills(String skills,Date date);
	public List<JobDetails> getJobsListByCompany(String company,Date date);
	public JobDetails getJob(int id);
	public BlockCandidate isBlocked(String applicant_username, String company_username);
	public String getCompanyUserName(int id);
	public void saveAppliedJobs(AppliedJobs jobs);
	public void withdraw(int id,String user) ;
}

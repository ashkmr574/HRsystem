package com.ashish.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.ashish.dto.AppliedJobs;
import com.ashish.dto.BlockCandidate;
import com.ashish.dto.Employer;
import com.ashish.dto.JobDetails;
import com.ashish.dto.LockCandidate;
import com.ashish.model.EmployerDetails;


public interface EmployerService 
{
	public boolean saveEmployer(EmployerDetails emp) throws ParseException;
	public String getCompanyName(String username);
	public EmployerDetails getProfile(String username);
	public EmployerDetails editProfile(Employer emp);
	public boolean validate(String email,Date date,String username);
	public void saveJob(JobDetails js);
	public List<BlockCandidate> getBlockedCandidate(String company_username);
	public List<AppliedJobs> getAppliedJobs(String company_username) ;
	public boolean lock(int id,String applicant_username, String company_username) ;
	public boolean block(String applicant_username, String company_username);
	public JobDetails getJob(int id) ;
	public void updateJob(JobDetails js);
	public boolean unblock(BlockCandidate candidate);
	public boolean unlock(LockCandidate candidate,String company_username) ;
	public boolean delete(int id,String user) ;
}

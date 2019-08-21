package com.ashish.dao;

import java.util.Date;
import java.util.List;

import com.ashish.dto.AppliedJobs;
import com.ashish.dto.BlockCandidate;
import com.ashish.dto.Employer;
import com.ashish.dto.JobDetails;
import com.ashish.dto.JobId;
import com.ashish.dto.LockCandidate;
import com.ashish.model.EmployerDetails;


public interface EmployerDao 
{
	public void saveEmployer(EmployerDetails emp);
	public String getCompanyName(String email);
	public boolean isuserexists(String username);
	public Employer getEmployer(String username);
	public boolean updateEmployer(Employer emp);
	public boolean validate(String email,Date date,String username);
	public void saveJob(JobDetails js);
	public List<BlockCandidate> getBlockedCandidate(String company_username);
	public List<AppliedJobs> getAppliedJobs(String username);
	public AppliedJobs isJobDetailsValid(JobId jbid, String company_username);
	public List<AppliedJobs> isJobDetailsValid(String applicant_username, String company_username);
	public void lock(LockCandidate candidate);
	public boolean changeStatus(LockCandidate candidate,String status) ;
	public boolean deleteAppliedJob(BlockCandidate bid) ;
	public void block(BlockCandidate candidate) ;
	public JobDetails getJob(int id);
	public void updateJob(JobDetails js);
	public int unblock(BlockCandidate candidate);
	public void unlock(LockCandidate candidate) ;
	public boolean delete(int id,String user) ;
}

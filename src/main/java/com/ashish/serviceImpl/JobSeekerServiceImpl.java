package com.ashish.serviceImpl;


import com.ashish.model.JobSeekerDetails;
import com.ashish.service.JobSeekerService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashish.dao.*;
import com.ashish.dto.AppliedJobs;
import com.ashish.dto.BlockCandidate;
import com.ashish.dto.EducationalDetails;
import com.ashish.dto.JobDetails;
import com.ashish.dto.JobId;
import com.ashish.dto.PersonalDetails;
import com.ashish.dto.ProfessionalDetails;
import com.ashish.dto.Users;

@Service
public class JobSeekerServiceImpl implements JobSeekerService
{
	@Autowired
	private JobSeekerDao jobSeekerDao;
	
	public void setJobSeekerDao(JobSeekerDao jobSeekerDao) 
	{
		this.jobSeekerDao = jobSeekerDao;
	}

	@Override
	public String getApplicantName(String username) 
	{
		String name=this.jobSeekerDao.getApplicantName(username);
		return name;
	}

	@Override
	public boolean saveJobSeeker(JobSeekerDetails js)
	{
		boolean status=this.jobSeekerDao.isuserexists(js.getUsers().getUsername());
		if(!status)
		{
			String hashed = BCrypt.hashpw(js.getUsers().getPassword(), BCrypt.gensalt());
			js.getUsers().setPassword(hashed);
			js.getUsers().setUsertype("jobseeker");
			String username=js.getUsers().getUsername();
			js.getPersonaldetails().setUsername(username);
			js.getProfessionaldetails().setUsername(username);
			js.getPersonaldetails().setUser(js.getUsers());
			js.getProfessionaldetails().setUser(js.getUsers());
			for(EducationalDetails education:js.getEducationaldetails())
			{
					education.setUsername(username);
					js.getUsers().getEducation().add(education);
			}
			this.jobSeekerDao.saveJobSeeker(js);
			return true;
		}
		return false;
	}

	@Override
	public String getContact(String username) 
	{
		return this.jobSeekerDao.getContact(username);
	}

	@Override
	public JobSeekerDetails getProfile(String username) 
	{
		PersonalDetails ps=this.jobSeekerDao.getPersonalDetails(username);
		ProfessionalDetails pfs=this.jobSeekerDao.getProfessionalDetails(username);
		List<EducationalDetails> eds=this.jobSeekerDao.getEducationalDetailsDetails(username);
		JobSeekerDetails jbs=new JobSeekerDetails();
		jbs.setEducationaldetails(eds);
		jbs.setPersonaldetails(ps);
		jbs.setProfessionaldetails(pfs);
		return jbs;
	}

	@Override
	public boolean editProfile(JobSeekerDetails js,String username) 
	{
		js.getPersonaldetails().setUsername(username);
		js.getProfessionaldetails().setUsername(username);
		Users users=new Users();
		users.setUsername(username);
		users.setUsertype("jobseeker");
		String password=this.jobSeekerDao.getPassword(username);
		users.setPassword(password);
		for(EducationalDetails eds:js.getEducationaldetails())
		{
			eds.setUsername(username);
			users.getEducation().add(eds);
		}
		this.jobSeekerDao.updateEducationalDetails(users);
		this.jobSeekerDao.updatePersonalDetails(js.getPersonaldetails());
		this.jobSeekerDao.updateProfessionalDetails(js.getProfessionaldetails());
		return true;
	}

	@Override
	public boolean isemailexists(String email)
	{
		String email1=this.jobSeekerDao.isemailexists(email);
		if(email1!=null)
			return true;
		return false;
	}

	@Override
	public boolean isuserexists(String username) 
	{
		return this.jobSeekerDao.isuserexists(username);
	}

	@Override
	public boolean validate(String email, Date date,String username) {
		return this.jobSeekerDao.validate(email, date,username);
	}

	@Override
	public List<JobDetails> getJobsList(String username) throws ParseException
	{
		List<Integer> jobids=new ArrayList<>();
		List<JobId> jobid=this.jobSeekerDao.getJobIds(username);
		Date date=new Date();
		DateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd");
		String s=dateformat.format(date);
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(s);
		if(jobid.size()==0)
			return this.jobSeekerDao.getJobsList(date);
		for(JobId jb:jobid)
		{
			jobids.add(jb.getJob_id());
		}
		return this.jobSeekerDao.getJobsList(jobids,date1);
	}

	@Override
	public List<JobDetails> getJobsList(String text, String criteria) throws ParseException 
	{
		Date date=new Date();
		DateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd");
		String s=dateformat.format(date);
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(s);
		List<JobDetails> jb_detail;
		if(criteria.equals("location"))
			jb_detail=this.jobSeekerDao.getJobsListByLocation(text,date1);
		else if(criteria.equals("skills"))
			jb_detail=this.jobSeekerDao.getJobsListBySkills(text,date1);
		else
			jb_detail=this.jobSeekerDao.getJobsListByCompany(text,date1);
	return jb_detail;
	}

	@Override
	public JobDetails getJob(int id) 
	{
		return this.jobSeekerDao.getJob(id);
	}

	@Override
	public boolean isBlocked(String applicant_username, String company_username) 
	{
		BlockCandidate candidate=this.jobSeekerDao.isBlocked(applicant_username, company_username);
		if(candidate==null)
				return false;
		return true;
	}

	@Override
	public String getCompanyUserName(int id) 
	{
		return this.jobSeekerDao.getCompanyUserName(id);
	}

	@Override
	public void saveAppliedJobs(AppliedJobs jobs) throws ParseException
	{
		Date date=new Date();
		DateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd");
		String s=dateformat.format(date);
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(s);
		jobs.setSubmission_date(date1);
		jobs.setStatus("submitted");
		 
		this.jobSeekerDao.saveAppliedJobs(jobs);
		
	}
	
	@Override
	public void withdraw(int id, String user) 
	{
		this.jobSeekerDao.withdraw(id, user);
		
	}

}

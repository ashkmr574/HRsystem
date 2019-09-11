package com.ashish.serviceImpl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashish.dao.EmployerDao;
import com.ashish.dto.AppliedJobs;
import com.ashish.dto.BlockCandidate;
import com.ashish.dto.BlockId;
import com.ashish.dto.Employer;
import com.ashish.dto.JobDetails;
import com.ashish.dto.JobId;
import com.ashish.dto.LockCandidate;
import com.ashish.model.EmployerDetails;
import com.ashish.service.EmployerService;

@Service
public class EmployerServiceImpl implements EmployerService
{
	@Autowired
	private EmployerDao employerDao;
	
	@Override
	public String getCompanyName(String username) 
	{
		String name=this.employerDao.getCompanyName(username);
		return name;
	}
	
	@Override
	public boolean saveEmployer(EmployerDetails emp) throws ParseException
	{
		boolean status=this.employerDao.isuserexists(emp.getUsers().getUsername());
		if(!status)
		{
			emp.getUsers().setUsertype("employer");
			emp.getEmployer().setUsername(emp.getUsers().getUsername());
			Date date=new Date();
			DateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd");
			String s=dateformat.format(date);
			Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(s);
			emp.getEmployer().setReg_date(date1);
			String hashed = BCrypt.hashpw(emp.getUsers().getPassword(), BCrypt.gensalt());
			emp.getUsers().setPassword(hashed);
			emp.getEmployer().setUser(emp.getUsers());
			this.employerDao.saveEmployer(emp);
			return true;
		}
		else
			return false;
		
	}

	@Override
	public EmployerDetails getProfile(String username) 
	{
		Employer emp=this.employerDao.getEmployer(username);
		EmployerDetails emp_detail=new EmployerDetails();
		emp_detail.setEmployer(emp);
		
		return emp_detail;
	}

	@Override
	public EmployerDetails editProfile(Employer emp) 
	{
		this.employerDao.updateEmployer(emp);
		return null;
	}

	@Override
	public boolean validate(String email, Date date, String username)
	{
		return this.employerDao.validate(email,date,username);
	}
	
	@Override
	public void saveJob(JobDetails jb) 
	{
		String company_name=this.employerDao.getCompanyName(jb.getUsername());
		jb.setCompany_name(company_name);
		this.employerDao.saveJob(jb);
		
	}
	
	@Override
	public List<BlockCandidate> getBlockedCandidate(String company_username) 
	{
		return this.employerDao.getBlockedCandidate(company_username);
	}
	
	@Override
	public List<AppliedJobs> getAppliedJobs(String company_username) 
	{
		
		return this.employerDao.getAppliedJobs(company_username);
	}
	
	@Override
	public boolean lock(int id,String applicant_username, String company_username) 
	{
		JobId jobid=new JobId(id,applicant_username);
		AppliedJobs job_detail=this.employerDao.isJobDetailsValid(jobid, company_username);
		if(job_detail==null)
			return false;
		LockCandidate candidate=new LockCandidate(jobid);
		boolean status=this.employerDao.changeStatus(candidate,"locked");
		if(status)
		{
			this.employerDao.lock(candidate);
			return true;
		}
		return false;
	}

	@Override
	public boolean block(String applicant_username, String company_username)
	{
		List<AppliedJobs> job_detail=this.employerDao.isJobDetailsValid(applicant_username,company_username);
		if(job_detail.size()==0)
				return false;
		BlockId blockid=new BlockId(applicant_username,company_username);
		BlockCandidate candidate=new BlockCandidate();
		candidate.setBlockid(blockid);
		boolean status=this.employerDao.deleteAppliedJob(candidate);
		if(status)
		{
			this.employerDao.block(candidate);
			return true;
		}
		return false;
	}
    
	@Override
	public JobDetails getJob(int id) 
	{
		return this.employerDao.getJob(id);
	}
	
	@Override
	public void updateJob(JobDetails js)
	{
		this.employerDao.updateJob(js);
		
	}
	
	@Override
	public boolean unblock(BlockCandidate candidate) 
	{
			int res=this.employerDao.unblock(candidate);
			if(res==1)
				return true;
			return false;
	}
	
	@Override
	public boolean unlock(LockCandidate candidate,String company_username) 
	{
		AppliedJobs job_detail=this.employerDao.isJobDetailsValid(candidate.getJobid(),company_username);
		if(job_detail==null)
				return false;
		boolean status=this.employerDao.changeStatus(candidate,"submitted");
		if(status)
		{
			this.employerDao.unlock(candidate);
			return true;
		}
		return false;
		
	}
	

	@Override
	public boolean delete(int id,String user) 
	{
		
		return this.employerDao.delete(id,user);
	}



}

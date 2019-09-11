package com.ashish.serviceImpl;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashish.dao.AdminDao;
import com.ashish.dto.Admin;
import com.ashish.dto.Employer;
import com.ashish.dto.JobDetails;
import com.ashish.dto.PersonalDetails;
import com.ashish.model.AdminModel;
import com.ashish.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService
{
	@Autowired
	private AdminDao adminDao;

	@Override
	public String login(String username, String password)
	{
		Admin admin=this.adminDao.login(username);
		if(admin==null)
			return "loginfailed";
		if(admin.getFirstlogin())
		{
			if(admin.getPassword().equals(password))
				return "firstlogin";
			else
				return "loginfailed";
		}
		else
		{
			if (BCrypt.checkpw(password,admin.getPassword()))
				return "loginsuccess";
			else
				return "loginfailed";
		}
		
	}



	@Override
	public boolean change_password(String username, String password, String password_new, String firstlogin) 
	{
		if(username==null || password==null || password_new==null || firstlogin==null)
			return false;
		String original_password=this.adminDao.get_Password(username);
		if(original_password==null)
			return false;
		if(firstlogin.equals("true"))
		{
			if(original_password.equals(password))
			{
				Admin admin=new Admin();
				admin.setFirstlogin(false);
				admin.setUsername(username);
				String hashed = BCrypt.hashpw(password_new, BCrypt.gensalt());
				admin.setPassword(hashed);
				Boolean Status=this.adminDao.change_password(admin);
				if(Status)
					return true;
				else
					return false;
			}
			else
				return false;
		}
		else
		{
			if (BCrypt.checkpw(password,original_password))
			{
				Admin admin=new Admin();
				admin.setFirstlogin(false);
				admin.setUsername(username);
				String hashed = BCrypt.hashpw(password_new, BCrypt.gensalt());
				admin.setPassword(hashed);
				Boolean Status=this.adminDao.change_password(admin);
				if(Status)
					return true;
				else
					return false;
			}
			else
				return false;
		}
	}



	@Override
	public boolean saveAdmin(AdminModel model) 
	{
		if(model.getAdmin()!=null && model.getAdmindetails()!=null && model.getAdmin().getUsername()!=null && model.getAdmin().getPassword()!=null)
		{
			boolean Status=this.adminDao.isAdminExists(model.getAdmin().getUsername());
		
			if(!Status)
			{
				model.getAdmindetails().setUsername(model.getAdmin().getUsername());
				model.getAdmin().setFirstlogin(true);
				this.adminDao.saveAdmin(model);
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}
	
	@Override
	public long getEmployerCount() 
	{
		return this.adminDao.getEmployerCount();
	}
	
	@Override
	public long getJobSeekerCount() 
	{
		return this.adminDao.getJobSeekerCount();
	}
	
	@Override
	public long getJobCount() 
	{
		return this.adminDao.getJobCount();
	}
	
	@Override
	public long getAppliedJobCount() 
	{
		return this.adminDao.getAppliedJobsCount();
	}
	
	@Override
	public List<Employer> getEmployers() 
	{
		return this.adminDao.getEmployers();
	}
	

	@Override
	public List<PersonalDetails> getJobSeekers()
	{
		return this.adminDao.getJobSeekers();
	}
	
	@Override
	public List<JobDetails> getJobs(boolean status) 
	{
		return this.adminDao.getJobs(status);
	}
}

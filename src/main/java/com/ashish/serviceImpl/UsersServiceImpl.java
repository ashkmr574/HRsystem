package com.ashish.serviceImpl;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashish.dao.UsersDao;
import com.ashish.dto.AppliedJobs;
import com.ashish.dto.Employer;
import com.ashish.dto.JobDetails;
import com.ashish.dto.Users;
import com.ashish.model.JobSeekerDetails;
import com.ashish.service.*;

@Service
public class UsersServiceImpl implements UsersService
{
	
	@Autowired
	private UsersDao usersDao;

	@Override
	public Boolean login(String username, String password,String usertype) 
	{
		String actual_password=this.usersDao.getPassword(username,usertype);
		if(actual_password==null)
			return false;
		if (BCrypt.checkpw(password,actual_password))
			return true;
		else
			return false;
	}
	
	@Override
	public Boolean changePassword(String username, String current_password,String password,String usertype) 
	{
		String actual_password=this.usersDao.getPassword(username,usertype);
		if(actual_password==null)
			return false;
		if (BCrypt.checkpw(current_password,actual_password))
		{
			String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
			return this.usersDao.changePassword(username,hashed);
		}
		return false;
	}
	
	@Override
	public Boolean changePassword(String username, String password) 
	{
		String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
		return this.usersDao.changePassword(username, hashed);
	}
	
	@Override
	public List<AppliedJobs> getAppliedJobs(String username)
	{
		return this.usersDao.getApliedJobs(username);
	}

	@Override
	public List<JobDetails> getPostedJobs(String username) 
	{
		return this.usersDao.getPostedJobs(username);
	}

	@Override
	public String isUserExists(String username) 
	{
		Users user=this.usersDao.isUserExists(username);
		if(user==null)
			return null;
		else
			return user.getUsertype();
	}

	@Override
	public JobSeekerDetails getProfile(String username) 
	{
		JobSeekerDetails jbs=new JobSeekerDetails();
		jbs.setPersonaldetails(this.usersDao.getPersonalDetails(username));
		jbs.setProfessionaldetails(this.usersDao.getProfessionalDetails(username));
		jbs.setEducationaldetails(this.usersDao.getEducationalDetailsDetails(username));
		return jbs;
	}

	@Override
	public Employer getProfile(String username, boolean employer)
	{
		return this.usersDao.getEmployerProfile(username);
	}

}

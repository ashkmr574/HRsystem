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
	public Integer login(String username, String password) 
	{
		Users usr=this.usersDao.getPassword(username);
		if(usr==null)
			return -1;
		if (BCrypt.checkpw(password,usr.getPassword()))
			return usr.getRoleId();
		else
			return -1;
	}
	
	@Override
	public Boolean changePassword(String username, String current_password,String password) 
	{
		Users usr=this.usersDao.getPassword(username);
		if(usr==null)
			return false;
		if (BCrypt.checkpw(current_password,usr.getPassword()))
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
	public Integer isUserExists(String username) 
	{
		Users user=this.usersDao.isUserExists(username);
		if(user==null)
			return null;
		else
			return user.getRoleId();
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

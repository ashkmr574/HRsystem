package com.ashish.daoImpl;

import com.ashish.dao.*;
import com.ashish.dto.AppliedJobs;
import com.ashish.dto.EducationalDetails;
import com.ashish.dto.Employer;
import com.ashish.dto.JobDetails;
import com.ashish.dto.PersonalDetails;
import com.ashish.dto.ProfessionalDetails;
import com.ashish.dto.Users;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UsersDaoImpl implements UsersDao
{
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public String getPassword(String username,String usertype)
	{
		Session session=this.sessionFactory.getCurrentSession();
		String password=(String)session.createQuery("select password from Users where username=:username and usertype=:usertype").setParameter("username",username).setParameter("usertype",usertype).uniqueResult();
		return password;
	}

	@Override
	public Boolean changePassword(String username, String password) 
	{
		Session session= this.sessionFactory.getCurrentSession();
		Query query=session.createQuery("update Users set password=:password where username=:username");
		query.setString("password",password);
		query.setString("username",username);
		query.executeUpdate();
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AppliedJobs> getApliedJobs(String username) 
	{
		Session session=this.sessionFactory.getCurrentSession();
		return session.createQuery("from AppliedJobs where applicant_username=:username").setParameter("username",username).list();
	}

	@Override
	public List<JobDetails> getPostedJobs(String username) 
	{
		Session session=this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<JobDetails> jb_detail=session.createQuery("from JobDetails where username=:username").setParameter("username",username).list();
		return jb_detail;
	}

	@Override
	public Users isUserExists(String username) 
	{
		Session session=this.sessionFactory.getCurrentSession();
		Users user=(Users)session.createQuery("from Users where username=:username").setParameter("username",username).uniqueResult();
		return user;
	}

	@Override
	public PersonalDetails getPersonalDetails(String username)
	{
		Session session= this.sessionFactory.getCurrentSession();
		PersonalDetails ps=(PersonalDetails)session.createQuery("from PersonalDetails where username=:username").setParameter("username",username).uniqueResult();
		return ps;
	}


	@Override
	public List<EducationalDetails> getEducationalDetailsDetails(String username) {
		Session session= this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<EducationalDetails> eds=session.createQuery("from EducationalDetails where username=:username").setParameter("username",username).list();
		return eds;
	}


	@Override
	public ProfessionalDetails getProfessionalDetails(String username) {
		Session session= this.sessionFactory.getCurrentSession();
		ProfessionalDetails pfs=(ProfessionalDetails)session.createQuery("from ProfessionalDetails where username=:username").setParameter("username",username).uniqueResult();
		return pfs;
	}

	@Override
	public Employer getEmployerProfile(String username) 
	{
		Session session= this.sessionFactory.getCurrentSession();
		Employer emp=(Employer)session.createQuery("from Employer where username=:username").setParameter("username",username).uniqueResult();
		return emp;
	}

	
}

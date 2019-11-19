package com.ashish.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ashish.dao.UsersDao;
import com.ashish.dto.AppliedJobs;
import com.ashish.dto.EducationalDetails;
import com.ashish.dto.Employer;
import com.ashish.dto.JobDetails;
import com.ashish.dto.PersonalDetails;
import com.ashish.dto.ProfessionalDetails;
import com.ashish.dto.Users;

@Repository
@Transactional
public class UsersDaoImpl implements UsersDao
{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Users getPassword(String username)
	{
		Session session=this.sessionFactory.getCurrentSession();
		return (Users)session.createQuery("from Users where username=:username").setParameter("username",username).uniqueResult();
	}

	@Override
	public Boolean changePassword(String username, String password) 
	{
		Session session= this.sessionFactory.getCurrentSession();
		session.createQuery("update Users set password=:password where username=:username")
		                .setParameter("password",password)
		                .setParameter("username",username)
		                .executeUpdate();
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AppliedJobs> getApliedJobs(String username) 
	{
		Session session=this.sessionFactory.getCurrentSession();
		return session.createQuery("from AppliedJobs where applicant_username=:username").setParameter("username",username).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JobDetails> getPostedJobs(String username) 
	{
		Session session=this.sessionFactory.getCurrentSession();
		return session.createQuery("from JobDetails where username=:username").setParameter("username",username).list();
	}

	@Override
	public Users isUserExists(String username) 
	{
		Session session=this.sessionFactory.getCurrentSession();
		return (Users)session.createQuery("from Users where username=:username").setParameter("username",username).uniqueResult();
	}

	@Override
	public PersonalDetails getPersonalDetails(String username)
	{
		Session session= this.sessionFactory.getCurrentSession();
		return (PersonalDetails)session.createQuery("from PersonalDetails where username=:username").setParameter("username",username).uniqueResult();
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
		return (ProfessionalDetails)session.createQuery("from ProfessionalDetails where username=:username").setParameter("username",username).uniqueResult();
	}

	@Override
	public Employer getEmployerProfile(String username) 
	{
		Session session= this.sessionFactory.getCurrentSession();
		return (Employer)session.createQuery("from Employer where username=:username").setParameter("username",username).uniqueResult();
	}

	
}

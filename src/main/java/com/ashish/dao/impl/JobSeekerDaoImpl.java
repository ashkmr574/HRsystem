package com.ashish.dao.impl;

import com.ashish.dao.JobSeekerDao;
import com.ashish.dto.AppliedJobs;
import com.ashish.dto.BlockCandidate;
import com.ashish.dto.EducationalDetails;
import com.ashish.dto.JobDetails;
import com.ashish.dto.JobId;
import com.ashish.dto.PersonalDetails;
import com.ashish.dto.ProfessionalDetails;
import com.ashish.dto.Users;
import com.ashish.model.JobSeekerDetails;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class JobSeekerDaoImpl implements JobSeekerDao
{
	@Autowired
	private SessionFactory sessionFactory;

    @Override
	public String getPassword(String Username)
	{
		Session session=this.sessionFactory.getCurrentSession();
		String password=(String)session.createQuery("select password from Users where username=:username").setParameter("username",Username).uniqueResult();
		return password;
	}

	@Override
	public String getApplicantName(String username) 
	{
		Session session= this.sessionFactory.getCurrentSession();
		String hql="select name from PersonalDetails where username= '"+username+"'";
		return (String)session.createQuery(hql).uniqueResult();
	}

	@Override
	public void saveJobSeeker(JobSeekerDetails js) 
	{
		Session session= this.sessionFactory.getCurrentSession();
		session.save(js.getUsers());
		session.save(js.getPersonaldetails());
		session.save(js.getProfessionaldetails());
	}


	@Override
	public String getContact(String username)
	{
		Session session= this.sessionFactory.getCurrentSession();
		String hql="select contact from PersonalDetails where username= '"+username+"'";
		return (String)session.createQuery(hql).uniqueResult();
	}


	@Override
	public boolean isuserexists(String username) 
	{
		Session session= this.sessionFactory.getCurrentSession();
		String user=(String)session.createQuery("select username from Users where username=:username").setParameter("username",username).uniqueResult();
		if(user==null)
			return false;
		else
			return true;
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
	public boolean updatePersonalDetails(PersonalDetails ps) {
		try
		{
			Session session= this.sessionFactory.getCurrentSession();
			session.update(ps);
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}


	@Override
	public boolean updateProfessionalDetails(ProfessionalDetails ps) 
	{
		try
		{
			Session session= this.sessionFactory.getCurrentSession();
			session.update(ps);
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}


	@Override
	public boolean updateEducationalDetails(Users user)
	{
		Session session= this.sessionFactory.getCurrentSession();
		session.update(user);
		return true;
	}
	
	@Override
	public String isemailexists(String email)
	{
		Session session= this.sessionFactory.getCurrentSession();
		String email1=(String)session.createQuery("select email from PersonalDetails where email=:email").setParameter("email",email).uniqueResult();
		return email1;
	}

	@Override
	public boolean validate(String email, Date date,String username) {
		Session session= this.sessionFactory.getCurrentSession();
		PersonalDetails ps=(PersonalDetails)session.createQuery("from PersonalDetails where email=:email and dob=:dob and username=:username" ).setParameter("email",email).setParameter("dob",date).setParameter("username",username).uniqueResult();
		if(ps==null)
			return false;
		return true;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<JobDetails> getJobsList(Date date) 
	{
		Session session= this.sessionFactory.getCurrentSession();
		return session.createQuery("from JobDetails where last_apply_date>=:date").setParameter("date",date).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<JobId> getJobIds(String username) 
	{
		Session session=this.sessionFactory.getCurrentSession();
		return session.createQuery("select jobid from AppliedJobs where applicant_username=:username")
		                    .setParameter("username", username).list();
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<JobDetails> getJobsList(List<Integer> jobids,Date date) 
	{
		Session session=this.sessionFactory.getCurrentSession();
		return session.createQuery("from JobDetails as jb where jb.job_id not in (:jobids) and last_apply_date>=:date")
		     .setParameterList("jobids",jobids)
		     .setParameter("date",date).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<JobDetails> getJobsListByLocation(String text,Date date) 
	{
		
		Session session=this.sessionFactory.getCurrentSession();
		return session.createQuery("from JobDetails where job_location like '%"+text+"%' and last_apply_date>=:date")
		.setParameter("date",date).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<JobDetails> getJobsListBySkills(String skills,Date date) 
	{
		Set<JobDetails> total_list=new HashSet<>();
		Session session=this.sessionFactory.getCurrentSession();
		StringTokenizer str=new StringTokenizer(skills,",");
		if(str.countTokens()>0)
		{
			while(str.hasMoreTokens())
			{
				List<JobDetails> jb_details=session.createQuery("from JobDetails where req_skills like '%"+str.nextToken()+"%' and last_apply_date>=:date")
				.setParameter("date",date).list();
				total_list.addAll(jb_details);
			}
		}
		return new ArrayList<>(total_list);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<JobDetails> getJobsListByCompany(String company,Date date) 
	{
		Session session=this.sessionFactory.getCurrentSession();
		return session.createQuery("from JobDetails where company_name like '%"+company+"%' or company_username like '%"+company+"%' and last_apply_date>=:date")
		                                    .setParameter("date",date).list();
	}
	
	@Override
	public JobDetails getJob(int id)
	{
		Session session=this.sessionFactory.getCurrentSession();
		return (JobDetails)session.createQuery("from JobDetails where id=:id").setParameter("id", id).uniqueResult();
	}
	
	@Override
	public BlockCandidate isBlocked(String applicant_username, String company_username) 
	{
		Session session=this.sessionFactory.getCurrentSession();
	    return (BlockCandidate)session.createQuery("from BlockCandidate where company_username=:company_username and applicant_username=:applicant_username").setParameter("applicant_username", applicant_username).setParameter("company_username", company_username).uniqueResult();
	}
	
	@Override
	public String getCompanyUserName(int id)
	{
		Session session=this.sessionFactory.getCurrentSession();
		String username=(String)session.createQuery("select username from JobDetails where job_id=:id").setParameter("id",id).uniqueResult();
		return username;
	}
	
	@Override
	public void saveAppliedJobs(AppliedJobs jobs) 
	{
		Session session=this.sessionFactory.getCurrentSession();
		session.save(jobs);
	}
	

	@Override
	public void withdraw(int id,String user) 
	{
		Session session=this.sessionFactory.getCurrentSession();
		session.createQuery("delete from AppliedJobs where job_id=:id and applicant_username=:user").setParameter("id", id).setParameter("user",user).executeUpdate();
		session.createQuery("delete from LockCandidate where job_id=:id and applicant_username=:user").setParameter("id",id).setParameter("user",user).executeUpdate();
	}
	
}


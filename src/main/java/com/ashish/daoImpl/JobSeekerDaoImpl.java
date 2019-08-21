package com.ashish.daoImpl;

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

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class JobSeekerDaoImpl implements JobSeekerDao
{
	@Autowired
	private SessionFactory sessionFactory;

	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    @Override
	public String getPassword(String Username)
	{
		Session session=this.sessionFactory.getCurrentSession();
		String password=(String)session.createQuery("select password from Users where username=:username and usertype='jobseeker'").setParameter("username",Username).uniqueResult();
		return password;
	}

	@Override
	public String getApplicantName(String username) 
	{
		Session session= this.sessionFactory.getCurrentSession();
		String hql="select name from PersonalDetails where username= '"+username+"'";
		Query query=session.createQuery(hql);
		String name=(String)query.uniqueResult();
		return name;
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
		Query query=session.createQuery(hql);
		String contact=(String)query.uniqueResult();
		return contact;
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
		PersonalDetails ps=(PersonalDetails)session.createQuery("from PersonalDetails where email=:email and dob=:dob and username=:username" ).setParameter("email",email).setDate("dob",date).setParameter("username",username).uniqueResult();
		if(ps==null)
			return false;
		return true;
	}


	@Override
	public List<JobDetails> getJobsList(Date date) 
	{
		Session session= this.sessionFactory.getCurrentSession();
		Query query=session.createQuery("from JobDetails where last_apply_date>=:date");
		query.setDate("date",date);
		@SuppressWarnings("unchecked")
		List<JobDetails> jb_details=query.list();
		return jb_details;
	}
	
	@Override
	public List<JobId> getJobIds(String username) 
	{
		Session session=this.sessionFactory.getCurrentSession();
		Query query=session.createQuery("select jobid from AppliedJobs where applicant_username=:username");
		query.setParameter("username", username);
		@SuppressWarnings("unchecked")
		List<JobId> jb_detail=query.list();
		return jb_detail;
		
	}


	@Override
	public List<JobDetails> getJobsList(List<Integer> jobids,Date date) 
	{
		Session session=this.sessionFactory.getCurrentSession();
		Query query=session.createQuery("from JobDetails as jb where jb.job_id not in (:jobids) and last_apply_date>=:date");
		query.setParameterList("jobids",jobids);
		query.setDate("date",date);
		@SuppressWarnings("unchecked")
		List<JobDetails> jb_detail=query.list();
		return jb_detail;
	}
	

	@Override
	public List<JobDetails> getJobsListByLocation(String text,Date date) 
	{
		
		Session session=this.sessionFactory.getCurrentSession();
		Query query=session.createQuery("from JobDetails where job_location like '%"+text+"%' and last_apply_date>=:date");
		query.setDate("date",date);
		@SuppressWarnings("unchecked")
		List<JobDetails> jb_details=query.list();
		return jb_details;
	}
	
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
				Query query=session.createQuery("from JobDetails where req_skills like '%"+str.nextToken()+"%' and last_apply_date>=:date");
				query.setDate("date",date);
				@SuppressWarnings("unchecked")
				List<JobDetails> jb_details=query.list();
				total_list.addAll(jb_details);
			}
		}
		return new ArrayList<JobDetails>(total_list);
	}


	@Override
	public List<JobDetails> getJobsListByCompany(String company,Date date) 
	{
		Session session=this.sessionFactory.getCurrentSession();
		Query query=session.createQuery("from JobDetails where company_name like '%"+company+"%' or company_username like '%"+company+"%' and last_apply_date>=:date");
		query.setDate("date",date);
		@SuppressWarnings("unchecked")
		List<JobDetails> jb_details=query.list();
		return jb_details;
	}
	
	@Override
	public JobDetails getJob(int id)
	{
		Session session=this.sessionFactory.getCurrentSession();
		Query query=session.createQuery("from JobDetails where id=:id");
		query.setParameter("id", id);
		JobDetails jb_detail=(JobDetails)query.uniqueResult();
		return jb_detail;
	}
	
	@Override
	public BlockCandidate isBlocked(String applicant_username, String company_username) 
	{
		Session session=this.sessionFactory.getCurrentSession();
		BlockCandidate candidate=(BlockCandidate)session.createQuery("from BlockCandidate where company_username=:company_username and applicant_username=:applicant_username").setParameter("applicant_username", applicant_username).setParameter("company_username", company_username).uniqueResult();
		return candidate;
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


package com.ashish.daoImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ashish.dao.AdminDao;
import com.ashish.dto.Admin;
import com.ashish.dto.Employer;
import com.ashish.dto.JobDetails;
import com.ashish.dto.PersonalDetails;
import com.ashish.model.AdminModel;

@Repository
@Transactional
public class AdminDaoImpl implements AdminDao
{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Admin login(String username) 
	{
		Session session=this.sessionFactory.getCurrentSession();
		return (Admin)session.createQuery("from Admin where username=:username").setParameter("username",username).uniqueResult();
	}

	@Override
	public boolean change_password(Admin admin) 
	{
		Session session=this.sessionFactory.getCurrentSession();
		try
		{
			session.update(admin);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public String get_Password(String username) 
	{
		Session session=this.sessionFactory.getCurrentSession();
		return (String)session.createQuery("select password from Admin where username=:username").setParameter("username",username).uniqueResult();
	}

	@Override
	public boolean saveAdmin(AdminModel model)
	{
		try
		{
			Session session=this.sessionFactory.getCurrentSession();
			session.save(model.getAdmin());
			session.save(model.getAdmindetails());
			return true;
		}catch(Exception e)
		{
			return false;
		}
	}

	@Override
	public boolean isAdminExists(String username)
	{
		Session session=this.sessionFactory.getCurrentSession();
		Admin admin=(Admin)session.createQuery("from Admin where username=:username").setParameter("username",username).uniqueResult();
		if(admin==null)
			return false;
		return true;
	}
	
	@Override
	public long getEmployerCount() 
	{
		Session session=this.sessionFactory.getCurrentSession();
		return (long)session.createQuery("select count(*) from Employer").uniqueResult();
		
	}
	
	@Override
	public long getJobSeekerCount() 
	{
		Session session= this.sessionFactory.getCurrentSession();
		return (long)session.createQuery("select count(*) from PersonalDetails").uniqueResult();
	}
	
	@Override
	public long getJobCount() 
	{
		Session session=this.sessionFactory.getCurrentSession();
		return (long)session.createQuery("select count(*) from JobDetails").uniqueResult();
	}
	
	@Override
	public long getAppliedJobsCount()
	{

		Session session=this.sessionFactory.getCurrentSession();
		return (long)session.createQuery("select count(*) from AppliedJobs").uniqueResult();
	}
	
	@Override
	public List<Employer> getEmployers() 
	{
		Session session=this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Employer> emp=session.createQuery("from Employer").list();
		return emp;
	}
	

	@Override
	public List<PersonalDetails> getJobSeekers() 
	{
		Session session= this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<PersonalDetails> ps_detail=session.createQuery("from PersonalDetails").list();
		return ps_detail;
	}
	
	@Override
	public List<JobDetails> getJobs(boolean status) 
	{
		Session session=this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<JobDetails> jb_detail=session.createQuery("from JobDetails").list();
		return jb_detail;
	}
}

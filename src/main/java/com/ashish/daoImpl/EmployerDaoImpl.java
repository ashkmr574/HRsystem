package com.ashish.daoImpl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ashish.dao.EmployerDao;
import com.ashish.dto.AppliedJobs;
import com.ashish.dto.BlockCandidate;
import com.ashish.dto.Employer;
import com.ashish.dto.JobDetails;
import com.ashish.dto.JobId;
import com.ashish.dto.LockCandidate;
import com.ashish.model.EmployerDetails;


@Repository
@Transactional
public class EmployerDaoImpl implements EmployerDao
{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public String getCompanyName(String username)
	{
		Session session=this.sessionFactory.getCurrentSession();
		String name=(String)session.createQuery("select company_name from Employer where username=:username").setParameter("username",username).uniqueResult();
		return name;
	}
	
	@Override
	public void saveEmployer(EmployerDetails emp) 
	{
		Session session=this.sessionFactory.getCurrentSession();
		session.save(emp.getUsers());
		session.save(emp.getEmployer());
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
	public Employer getEmployer(String username) {
		Session session= this.sessionFactory.getCurrentSession();
		Employer emp=(Employer)session.createQuery("from Employer where username=:username").setParameter("username",username).uniqueResult();
		return emp;
		
	}

	@Override
	public boolean updateEmployer(Employer emp) {
		Session session= this.sessionFactory.getCurrentSession();
		session.update(emp);
		return true;
	}

	@Override
	public boolean validate(String email, Date date, String username) 
	{
		Session session= this.sessionFactory.getCurrentSession();
		Employer emp=(Employer)session.createQuery("from Employer where email=:email and reg_date=:reg_date and username=:username" ).setParameter("email",email).setDate("reg_date",date).setParameter("username",username).uniqueResult();
		if(emp==null)
			return false;
		return true;
	}
	
	@Override
	public void saveJob(JobDetails jb) 
	{
		Session session= this.sessionFactory.getCurrentSession();
		session.save(jb);
		
	}
	
	@Override
	public List<BlockCandidate> getBlockedCandidate(String company_username) 
	{
		Session session=this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<BlockCandidate> candidate=session.createQuery("from BlockCandidate where company_username=:company_username").setParameter("company_username", company_username).list();
		return candidate;
	}
	
	@Override
	public List<AppliedJobs> getAppliedJobs(String username) 
	{
		Session session=this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<AppliedJobs> jobs=session.createQuery("from AppliedJobs where company_username=:username").setParameter("username",username).list();
		return jobs;
	}

	@Override
	public AppliedJobs isJobDetailsValid(JobId jobid, String company_username) 
	{
		Session session=this.sessionFactory.getCurrentSession();
		AppliedJobs jb_detail=(AppliedJobs)session.createQuery("from AppliedJobs where job_id=:job_id and applicant_username=:applicant_username and company_username=:company_username").setParameter("job_id", jobid.getJob_id()).setParameter("applicant_username",jobid.getApplicant_username()).setParameter("company_username",company_username).uniqueResult();
		return jb_detail;
	}
	
	@Override
	public void lock(LockCandidate candidate) 
	{
		Session session=this.sessionFactory.getCurrentSession();
		session.save(candidate);
	}
	
	@Override
	public boolean changeStatus(LockCandidate candidate,String status) 
	{
		Session session=this.sessionFactory.getCurrentSession();
		Query query=session.createQuery("update AppliedJobs set status=:status where job_id=:job_id and applicant_username=:applicant_username");
		query.setString("applicant_username",candidate.getJobid().getApplicant_username());
		query.setParameter("job_id",candidate.getJobid().getJob_id());
		query.setString("status",status);
		query.executeUpdate();
		return true;
		
	}

	@Override
	public List<AppliedJobs> isJobDetailsValid(String applicant_username, String company_username) 
	{

		Session session=this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<AppliedJobs> jb_detail=(List<AppliedJobs>)session.createQuery("from AppliedJobs where applicant_username=:applicant_username and company_username=:company_username").setParameter("applicant_username",applicant_username).setParameter("company_username",company_username).list();
		return jb_detail;
	}
	
	@Override
	public boolean deleteAppliedJob(BlockCandidate bid) 
	{
		Session session=this.sessionFactory.getCurrentSession();
		session.createQuery("delete from AppliedJobs where company_username=:company_username and applicant_username=:user").setParameter("company_username",bid.getBlockid().getCompany_username()).setParameter("user",bid.getBlockid().getApplicant_username()).executeUpdate();
		return true;
	}
	
	@Override
	public void block(BlockCandidate candidate) 
	{
		Session session=this.sessionFactory.getCurrentSession();
		session.save(candidate);	
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
	public void updateJob(JobDetails js) {
		Session session=this.sessionFactory.getCurrentSession();
		session.update(js);
		
	}
	
	@Override
	public int unblock(BlockCandidate candidate)
	{
		Session session=this.sessionFactory.getCurrentSession();
		return session.createQuery("delete from BlockCandidate where applicant_username=:applicant_username and company_username=:company_username").setParameter("applicant_username",candidate.getBlockid().getApplicant_username()).setParameter("company_username",candidate.getBlockid().getCompany_username()).executeUpdate();
	}
	
	@Override
	public void unlock(LockCandidate candidate) 
	{
		Session session=this.sessionFactory.getCurrentSession();
		session.delete(candidate);
		
	}
	
	@Override
	public boolean delete(int id,String user) 
	{
		Session session=this.sessionFactory.getCurrentSession();
		try
		{
			int count=session.createQuery("delete from JobDetails where job_id=:id and username=:username").setParameter("id",id).setParameter("username",user).executeUpdate();
			if(count==1)
			{
				session.createQuery("delete from AppliedJobs where job_id=:id").setParameter("id",id).executeUpdate();
				session.createQuery("delete from LockCandidate where job_id=:id").setParameter("id",id).executeUpdate();
				return true;
			}
			else
				return false;
		}
		catch(Exception e)
		{
			return false;
		}
	}

}

package com.ashish.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="appliedjobs")
public class AppliedJobs 
{
	@EmbeddedId
	private JobId jobid;
	
	@Column(name="company_username")
	@NotBlank(message="It should not be empty")
	private String company_username;
	
	@Column(name="company_status")
	private String status;
	
	@Temporal(TemporalType.DATE)
	@Column(name="job_submission_date")
	private Date submission_date;
	
	@Column(name="designation")
	@NotBlank
	private String post;
	
	@Column(name="joblocation")
	@NotBlank
	private String job_location;
	
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getJob_location() {
		return job_location;
	}
	public void setJob_location(String job_location) {
		this.job_location = job_location;
	}
	public String getCompany_username() {
		return company_username;
	}
	public void setCompany_username(String company_username) {
		this.company_username = company_username;
	}
	public JobId getJobid() {
		return jobid;
	}
	public void setJobid(JobId jobid) {
		this.jobid = jobid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getSubmission_date() {
		return submission_date;
	}
	public void setSubmission_date(Date submission_date) {
		this.submission_date = submission_date;
	}
}

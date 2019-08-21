package com.ashish.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class JobId implements Serializable
{
	@Column(name="job_id")
	private int job_id;
	
	@Column(name="applicant_username")
	private String applicant_username;
	
	public int getJob_id() {
		return job_id;
	}
	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}
	
	public String getApplicant_username() {
		return applicant_username;
	}
	public void setApplicant_username(String applicant_name) {
		this.applicant_username = applicant_name;
	}
	public JobId()
	{
		
	}
	public JobId(int job_id,String applicant_name)
	{
		this.job_id=job_id;
		this.applicant_username=applicant_name;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(this==o)
			return true;
		if(!(o instanceof JobId))
			return false;
		JobId that=(JobId)o;
		return Objects.equals(getApplicant_username(),that.getApplicant_username()) && Objects.equals(this.getJob_id(), that.getJob_id());
	}
	public int hashCode()
	{
		return Objects.hash(getJob_id(),getApplicant_username());
	}
}

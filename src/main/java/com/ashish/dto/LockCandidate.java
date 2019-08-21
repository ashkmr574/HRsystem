package com.ashish.dto;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import javax.persistence.Table;

@Entity
@Table(name="lockedcandidate")
public class LockCandidate 
{
	@EmbeddedId
	private JobId jobid;

	public JobId getJobid() {
		return jobid;
	}

	public void setJobid(JobId jobid) {
		this.jobid = jobid;
	}

	public LockCandidate(JobId jobid) {
		super();
		this.jobid = jobid;
	}

	public LockCandidate() 
	{
	}
	
	
}

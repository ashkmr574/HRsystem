package com.ashish.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class BlockId implements Serializable
{
	@Column(name="applicant_username")
	private String applicant_username;
	
	@Column(name="company_username")
	private String company_username;

	public String getApplicant_username() {
		return applicant_username;
	}

	public void setApplicant_username(String applicant_username) {
		this.applicant_username = applicant_username;
	}

	public String getCompany_username() {
		return company_username;
	}

	public void setCompany_username(String company_username) {
		this.company_username = company_username;
	}

	public BlockId() {}

	public BlockId(String applicant_username, String company_username) {
		super();
		this.applicant_username = applicant_username;
		this.company_username = company_username;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCompany_username(), getApplicant_username());
	}

	@Override
	public boolean equals(Object o) {
		if(this==o)
			return true;
		if(!(o instanceof BlockId))
			return false;
		BlockId that=(BlockId)o;
		return Objects.equals(getApplicant_username(),that.getApplicant_username()) && Objects.equals(this.getCompany_username(), that.getCompany_username());
	}
	
}

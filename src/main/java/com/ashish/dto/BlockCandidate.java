package com.ashish.dto;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="blockedcandidate")
public class BlockCandidate 
{
	@EmbeddedId
	private BlockId blockid;

	public BlockId getBlockid() {
		return blockid;
	}

	public void setBlockid(BlockId blockid) {
		this.blockid = blockid;
	}
}

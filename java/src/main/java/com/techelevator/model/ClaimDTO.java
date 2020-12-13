package com.techelevator.model;

import java.math.BigDecimal;

public class ClaimDTO {

	private Long claimId;
	private BigDecimal claimAmount;
	private String status;
	private String description;
	private String username;
	private Long potholeId;
	
	public ClaimDTO() {
		
	}
	
	public ClaimDTO(Long claimId, BigDecimal claimAmount, String status, String description,
			String username, Long potholeId) {
		
		this.claimId =  claimId;
		this.claimAmount = claimAmount;
		this.status = status;
		this.description = description;
		this.username = username;
		this.potholeId = potholeId;
		
			
	}

	public Long getClaimId() {
		return claimId;
	}

	public void setClaimId(Long claimId) {
		this.claimId = claimId;
	}

	public BigDecimal getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(BigDecimal claimAmount) {
		this.claimAmount = claimAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String statusId) {
		this.status = statusId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userId) {
		this.username = userId;
	}

	public Long getPotholeId() {
		return potholeId;
	}

	public void setPotholeId(Long potholeId) {
		this.potholeId = potholeId;
		
		
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Claim [claimId=" + claimId + ", claimAmount=" + claimAmount + ", status=" + status + ", username="
				+ username + ", potholeId=" + potholeId + ", description=" + description +"]";
	}
}


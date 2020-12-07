package com.techelevator.model;

public class Claim {

	private Long claimId;
	private double claimAmount;
	private Long statusId;
	
	private Long userId;
	private Long potholeId;
	
	public Claim() {
		
	}

	public Long getClaimId() {
		return claimId;
	}

	public void setClaimId(Long claimId) {
		this.claimId = claimId;
	}

	public double getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(double claimAmount) {
		this.claimAmount = claimAmount;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getPotholeId() {
		return potholeId;
	}

	public void setPotholeId(Long potholeId) {
		this.potholeId = potholeId;
		
		
	}

	@Override
	public String toString() {
		return "Claim [claimId=" + claimId + ", claimAmount=" + claimAmount + ", statusId=" + statusId + ", userId="
				+ userId + ", potholeId=" + potholeId + "]";
	}
}

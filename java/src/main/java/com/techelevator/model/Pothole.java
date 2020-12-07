package com.techelevator.model;

public class Pothole {

	private Long potholeId;
	private Integer statusId;
	private Long LocationId;
	private Integer severityId;
	
	public Pothole(){
		
		
	}

	public Long getPotholeId() {
		return potholeId;
	}

	public void setPotholeId(Long potholeId) {
		this.potholeId = potholeId;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public Long getLocationId() {
		return LocationId;
	}

	public void setLocationId(Long locationId) {
		LocationId = locationId;
	}

	public Integer getSeverityId() {
		return severityId;
	}

	public void setSeverityId(Integer severityId) {
		this.severityId = severityId;
		
		
	}

	@Override
	public String toString() {
		return "Pothole [potholeId=" + potholeId + ", statusId=" + statusId + ", LocationId=" + LocationId
				+ ", severityId=" + severityId + "]";
	}
}

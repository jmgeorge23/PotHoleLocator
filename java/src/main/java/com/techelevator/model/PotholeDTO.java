package com.techelevator.model;

import java.math.BigDecimal;

public class PotholeDTO {
	

	private Long potholeId;
	private String status;
	private String severity;
	private String username;
	private BigDecimal latitude;
	private BigDecimal longitude;
	private String roadName;
	private String direction;
	private String lane;
	
	
	public PotholeDTO(String severity, BigDecimal latitude, BigDecimal longitude) {

		this.severity = severity;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public PotholeDTO(String severity, BigDecimal latitude, BigDecimal longitude, String status, String username) {

		this.severity = severity;
		this.latitude = latitude;
		this.longitude = longitude;
		this.status = status;
		this.username =  username;
		
	}
	
	public PotholeDTO(String severity, BigDecimal latitude, BigDecimal longitude, String status, String username, Long potholeId) {

		this.severity = severity;
		this.latitude = latitude;
		this.longitude = longitude;
		this.status = status;
		this.username =  username;
		this.potholeId = potholeId;
		
	}
	
	
	
	public PotholeDTO() {
		
	}
	

	public Long getPotholeId() {
		return potholeId;
	}

	public void setPotholeId(Long potholeId) {
		this.potholeId = potholeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public String getRoadName() {
		return roadName;
	}

	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getLane() {
		return lane;
	}

	public void setLane(String lane) {
		this.lane = lane;
	}

	@Override
	public String toString() {
		return "Pothole [potholeId=" + potholeId + ", status=" + status + ", severity=" + severity + ", username=" + username + ", latitude=" + latitude + ", longitude="
				+ longitude + ", roadName=" + roadName + ", direction=" + direction + ", lane=" + lane + "]";
	}

}

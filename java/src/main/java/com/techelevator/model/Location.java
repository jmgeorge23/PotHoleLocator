package com.techelevator.model;

public class Location {

	private Long locationId;
	private Long latitude;
	private Long longitude;
	private String roadName;
	private String direction;
	private String lane;
	
	
	public Location() {
		
		
	}


	public Long getLocationId() {
		return locationId;
	}


	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}


	public Long getLatitude() {
		return latitude;
	}


	public void setLatitude(Long latitude) {
		this.latitude = latitude;
	}


	public Long getLongitude() {
		return longitude;
	}


	public void setLongitude(Long longitude) {
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
		return "Location [locationId=" + locationId + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", roadName=" + roadName + ", direction=" + direction + ", lane=" + lane + "]";
	}
}

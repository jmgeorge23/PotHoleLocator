package com.techelevator.model;

import java.math.BigDecimal;

public class CommentDTO {

	private Long commentId;
	private String username;
	private String comment;
	private Long potholeId;
	private String dateTime;
	private BigDecimal latitude;
	private BigDecimal longitude;
	
	public CommentDTO() {
		
	}
	
	public CommentDTO(Long commentId, String username, String comment, Long potholeId,String dateTime,
						BigDecimal latitude, BigDecimal longitude) {
		
		this.commentId = commentId;
		this.username = username;
		this.comment = comment;
		this.potholeId = potholeId;
		this.dateTime = dateTime;
		this.latitude = latitude;
		this.longitude = longitude;
		
	}
	

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getPotholeId() {
		return potholeId;
	}

	public void setPotholeId(Long potholeId) {
		this.potholeId = potholeId;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
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

}

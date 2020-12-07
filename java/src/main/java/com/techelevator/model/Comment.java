package com.techelevator.model;

import java.time.LocalDate;

public class Comment {

	private Long commentId;
	private Long userId;
	private Long potholeId;
	private String comment;
	private LocalDate timeStamp;
	
	
	public Comment() {
		
		
	}


	public Long getCommentId() {
		return commentId;
	}


	public void setCommentId(Long commentId) {
		this.commentId = commentId;
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


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public LocalDate getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(LocalDate timeStamp) {
		this.timeStamp = timeStamp;
		
		
	}


	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", userId=" + userId + ", potholeId=" + potholeId + ", comment="
				+ comment + ", timeStamp=" + timeStamp + "]";
	}
}

package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Comment;

public interface CommentDAO {
	
	//Get all comments
	List<Comment> getAllComments();
	
	//Get comments by user
	List<Comment> getCommentsByUser(Long userId);
	
	//Get comments by pothole
	List<Comment> getCommentsByPotholeId(Long potholeId);
	
	//add comment
	Comment createComment(Comment newComment);
	
	//delete comment
	Comment deleteComment(Long id);
	

}

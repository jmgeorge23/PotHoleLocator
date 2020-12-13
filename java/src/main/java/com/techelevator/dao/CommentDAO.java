package com.techelevator.dao;

import java.util.List;


import com.techelevator.model.CommentDTO;

public interface CommentDAO {
	
	//Get all comments
	List<CommentDTO> getAllComments();
	
	//Get comments by user
	List<CommentDTO> getCommentsByUsername(String username);
	
	//Get comments by pothole
	List<CommentDTO> getCommentsByPotholeId(Long potholeId);
	
	//add comment
	boolean createComment(CommentDTO newComment);
	
	//delete comment
	boolean deleteComment(Long commentId);
	

}

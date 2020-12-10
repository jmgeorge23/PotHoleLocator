package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Comment;
import com.techelevator.model.CommentDTO;

public interface CommentDAO {
	
	//Get all comments
	List<CommentDTO> getAllComments();
	
	//Get comments by user
	List<CommentDTO> getCommentsByUser(Long userId);
	
	//Get comments by pothole
	List<CommentDTO> getCommentsByPotholeId(Long potholeId);
	
	//add comment
	CommentDTO createComment(CommentDTO newComment);
	
	//delete comment
	CommentDTO deleteComment(Long id);
	

}

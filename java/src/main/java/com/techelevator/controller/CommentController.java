package com.techelevator.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.CommentDAO;
import com.techelevator.model.Comment;
import com.techelevator.model.CommentDTO;

//@RestController
public class CommentController {
	
	private CommentDAO commentDAO;
		
	public CommentController(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}
	
	//Get all comments
//	@RequestMapping(path = "/comments", method = RequestMethod.GET)
//	public List<Comment> getAllComments() {
//		return commentDAO.getAllComments();
//	}
	
	//Get comments by user
//	@RequestMapping(path = "/comments/user/{userId}", method = RequestMethod.GET)
//	public List<Comment> getCommentsByUserId(@PathVariable int userId) {
//		return commentDAO.getCommentsByUserId(userId);
//	}
	
	//Get comments by pothole
//	@RequestMapping(path = "/comments/pothole/{potholeId}", method = RequestMethod.GET)
//	public List<Comment> getCommentsByPotholeId(@PathVariable int potholeId) {
//		return commentDAO.getCommentsByPothileId(potholeId);
//	}
	
	//Add comments
//	@ResponseStatus(HttpStatus.CREATED)
//	@RequestMapping(path = "/comments", method = RequestMethod.POST)
//	public void createComment(@Valid @RequestBody CommentDTO newComment) {
//		commentDAO.createComment(newComment);
//	}
	
	//Delete comment
//	@RequestMapping(path = "/comments/{id}", method = RequestMethod.DELETE)
//	public void deleteComment(@PathVariable int id) {
//		commentDAO.deleteComment(id);
//	}
}

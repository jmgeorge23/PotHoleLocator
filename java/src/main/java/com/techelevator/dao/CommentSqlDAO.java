package com.techelevator.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.Comment;

public class CommentSqlDAO implements CommentDAO {

private JdbcTemplate jdbcTemplate;
	
	public CommentSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Comment> getAllComments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> getCommentsByUser(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> getCommentsByPotholeId(Long potholeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment createComment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment deleteComment() {
		// TODO Auto-generated method stub
		return null;
	}

	
}

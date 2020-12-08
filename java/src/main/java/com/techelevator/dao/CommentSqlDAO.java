package com.techelevator.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

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
	public Comment createComment(Comment newComment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment deleteComment(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Comment maptoComment(SqlRowSet cm) {
		
		Comment comments =  new Comment();
		
		comments.setCommentId(cm.getLong("comment_id"));
		comments.setUserId(cm.getLong("user_id"));
		comments.setPotholeId(cm.getLong("pothole_id"));
		comments.setComment(cm.getString("comment"));
		comments.setTimeStamp(cm.getDate("time_stamp"));
		return comments;
		
	}

	
}

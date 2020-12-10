package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.Comment;
import com.techelevator.model.CommentDTO;

public class CommentSqlDAO implements CommentDAO {

	private JdbcTemplate jdbcTemplate;

	public CommentSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<CommentDTO> getAllComments() {

		List<CommentDTO> allComments = new ArrayList<>();

		String getAllComments = "SELECT c.comment_id, c.user_comment, c.posted_at, u.username, p.pothole_id, p.lat, p.lng FROM comments c "
				+ "INNER JOIN users_comments uc ON c.comment_id = uc.comment_id "
				+ "INNER JOIN users u ON uc.user_id = u.user_id "
				+ "INNER JOIN potholes_comments pc ON c.comment_id = pc.comment_id "
				+ "INNER JOIN potholes p ON pc.pothole_id = p.pothole_id";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(getAllComments);
		
		while (result.next()) {
			CommentDTO comment = mapToComment(result);
			allComments.add(comment);
		}

		return allComments;
	}

	@Override
	public List<CommentDTO> getCommentsByUser(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommentDTO> getCommentsByPotholeId(Long potholeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommentDTO createComment(CommentDTO newComment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommentDTO deleteComment(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	private CommentDTO mapToComment(SqlRowSet cm) {

		CommentDTO comments = new CommentDTO();

		comments.setCommentId(cm.getLong("comment_id"));
		comments.setUsername(cm.getString("username"));
		comments.setComment(cm.getString("user_comment"));
		comments.setPotholeId(cm.getLong("pothole_id"));
		comments.setDateTime(cm.getString("posted_at"));
		comments.setLatitude(cm.getBigDecimal("lat"));
		comments.setLongitude(cm.getBigDecimal("lng"));
		return comments;

	}

}

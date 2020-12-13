package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.CommentDTO;
import com.techelevator.model.User;

@Service
public class CommentSqlDAO implements CommentDAO {

	private JdbcTemplate jdbcTemplate;

	public CommentSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// Method to get a list of all comments
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
	
	// Method to get a list of comments by a username
	@Override
	public List<CommentDTO> getCommentsByUsername(String username) {

		List<CommentDTO> allCommentsByUsername = new ArrayList<>();

		String getAllComments = "SELECT c.comment_id, c.user_comment, c.posted_at, u.username, p.pothole_id, p.lat, p.lng FROM comments c "
				+ "INNER JOIN users_comments uc ON c.comment_id = uc.comment_id "
				+ "INNER JOIN users u ON uc.user_id = u.user_id "
				+ "INNER JOIN potholes_comments pc ON c.comment_id = pc.comment_id "
				+ "INNER JOIN potholes p ON pc.pothole_id = p.pothole_id WHERE username = ?";

		SqlRowSet result = jdbcTemplate.queryForRowSet(getAllComments, username);

		while (result.next()) {
			CommentDTO comment = mapToComment(result);
			allCommentsByUsername.add(comment);
		}

		return allCommentsByUsername;
	}

	// Method to get a list of comments by a potholes ID
	@Override
	public List<CommentDTO> getCommentsByPotholeId(Long potholeId) {

		List<CommentDTO> allCommentsByPothole = new ArrayList<>();

		String getAllComments = "SELECT c.comment_id, c.user_comment, c.posted_at, u.username, p.pothole_id, p.lat, p.lng FROM comments c "
				+ "INNER JOIN users_comments uc ON c.comment_id = uc.comment_id "
				+ "INNER JOIN users u ON uc.user_id = u.user_id "
				+ "INNER JOIN potholes_comments pc ON c.comment_id = pc.comment_id "
				+ "INNER JOIN potholes p ON pc.pothole_id = p.pothole_id WHERE p.pothole_id = ?";

		SqlRowSet result = jdbcTemplate.queryForRowSet(getAllComments, potholeId);

		while (result.next()) {
			CommentDTO comment = mapToComment(result);
			allCommentsByPothole.add(comment);
		}

		return allCommentsByPothole;
	}
	
	// Method to get a new comments comment ID
	public Long getCommentId(CommentDTO newComment, String username) {

			String getNewCommentWithId = "SELECT comment_id FROM comments WHERE user_comment = ?";

			return jdbcTemplate.queryForObject(getNewCommentWithId, long.class, newComment.getComment());
		}

	// Method to create a new comment
	@Override
	public boolean createComment(CommentDTO newComment) {

		boolean comment = false;
		
		String addToComments = "INSERT INTO comments (comment_id, user_comment, posted_at) "
				+ "VALUES (DEFAULT, ?, CURRENT_TIMESTAMP);";
		// Add to comment to comments table
		int result = jdbcTemplate.update(addToComments, newComment.getComment());

		if (result == 1) {
			// Get the user ID who submitted the new comment
			User user = getUserId(newComment);
			// Get the comment ID of the new comment
			Long newCommentId = getCommentId(newComment, user.getUsername());
			// Set the new comment with its comment ID
			newComment.setCommentId(newCommentId);
			// Add comment to the users_comment table
			if (addToUsersComments(newComment, user)) {
				// Add comment to potholes_comments table
				if (addToPotholesComments(newComment)) {

					comment = true;
				}
			}
		}
		return comment;
	}
	
	// Method to delete a comment from the comment table and all associative tables
	@Override
	public boolean deleteComment(Long commentId) {

		boolean commentDeleted = false;
		// Delete from users_comments table
		if (deleteFromUsersComments(commentId)) {
			// Delete from potholes_comments table
			if (deleteFromPotholesComments(commentId)) {
				// Delete from comments table
				if (deleteFromComments(commentId)) {
					commentDeleted = true;
				}
			}
		}
		return commentDeleted;

	}

	// Method to get a users ID from the new comment
	private User getUserId(CommentDTO newComment) {

		User user = null;

		String userFromUsername = "SELECT * FROM users WHERE username = ?";

		SqlRowSet result = jdbcTemplate.queryForRowSet(userFromUsername, newComment.getUsername());

		while (result.next()) {
			user = mapToUser(result);
		}
		return user;
	}

	// Method to add a comment to the users_comments table
	private boolean addToUsersComments(CommentDTO newComment, User user) {

		boolean comment = false;

		String addToUsersComments = "INSERT INTO users_comments (user_id, comment_id) VALUES (?, ?)";

		int result = jdbcTemplate.update(addToUsersComments, user.getId(), newComment.getCommentId());

		if (result == 1) {
			comment = true;
		}

		return comment;
	}

	// Method to add a comment to the potholes_comments table 
	private boolean addToPotholesComments(CommentDTO newComment) {

		boolean comment = false;

		String addToPotholesComments = "INSERT INTO potholes_comments (pothole_id, comment_id) VALUES (?, ?)";

		int result = jdbcTemplate.update(addToPotholesComments, newComment.getPotholeId(), newComment.getCommentId());

		if (result == 1) {
			comment = true;
		}
		return comment;
	}

	// Method to delete a comment from users_comments table
	private boolean deleteFromUsersComments(Long commentId) {

		boolean deleted = false;

		String deleteFromUsersComments = "DELETE FROM users_comments where comment_id = ?";

		int result = jdbcTemplate.update(deleteFromUsersComments, commentId);

		if (result >= 0) {
			deleted = true;
		}

		return deleted;
	}

	// Method to delete a commment from potholes_comments table
	private boolean deleteFromPotholesComments(Long commentId) {

		boolean deleted = false;

		String deleteFromPotholesComments = "DELETE FROM potholes_comments where comment_id = ?";

		int result = jdbcTemplate.update(deleteFromPotholesComments, commentId);

		if (result >= 0) {
			deleted = true;
		}

		return deleted;
	}

	// Method to delete a comment from comments table
	private boolean deleteFromComments(Long commentId) {

		boolean deleted = false;

		String deleteFromComments = "DELETE FROM comments where comment_id = ?";

		int result = jdbcTemplate.update(deleteFromComments, commentId);

		if (result >= 0) {
			deleted = true;
		}

		return deleted;
	}

	// Method to map a Sql row set from the comments and associative tables
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

	// Method to map a Sql row set from the users table
	private User mapToUser(SqlRowSet u) {

		User user = new User();

		user.setId(u.getLong("user_id"));
		user.setUsername(u.getString("username"));

		return user;
	}

}

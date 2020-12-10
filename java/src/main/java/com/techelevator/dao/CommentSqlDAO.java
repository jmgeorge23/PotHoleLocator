package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.Comment;
import com.techelevator.model.CommentDTO;
import com.techelevator.model.User;

@Service
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

	@Override
	public boolean createComment(CommentDTO newComment) {

		boolean comment = false;
		// add to comments
		String addToComments = "INSERT INTO comments (comment_id, user_comment, posted_at) "
				+ "VALUES (DEFAULT, ?, CURRENT_TIMESTAMP);";

		int result = jdbcTemplate.update(addToComments, newComment.getComment());

		if (result == 1) {
			// add to users_comments

			User user = getUserId(newComment);
			CommentDTO newCommentWithId = getCommentId(newComment, user);

			if (addToUsersComments(newCommentWithId, user)) {

				// add to potholes_comments
				if (addToPotholesComments(newCommentWithId)) {

					comment = true;
				}
			}
		}
		return comment;
	}

	@Override
	public CommentDTO deleteComment(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	private CommentDTO getCommentId(CommentDTO newComment, User user) {

		CommentDTO newCommentWithId = null;

		String getNewCommentWithId = "SELECT comment_id, user_comment, posted_at FROM comments WHERE user_comment = ?";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(getNewCommentWithId, newComment.getComment());
		
		while(result.next()) {
			newCommentWithId = mapToComment(result);
		}
		
		return newCommentWithId;
	}

	private User getUserId(CommentDTO newComment) {

		User user = null;

		String userFromUsername = "SELECT * FROM users WHERE username = ?";

		SqlRowSet result = jdbcTemplate.queryForRowSet(userFromUsername, newComment.getUsername());

		while (result.next()) {
			user = mapToUser(result);
		}
		return user;
	}

	private boolean addToUsersComments(CommentDTO newComment, User user) {

		boolean comment = false;

		String addToUsersComments = "INSERT INTO users_comments (user_id, comment_id) VALUES (?, ?)";

		int result = jdbcTemplate.update(addToUsersComments, user.getId(), newComment.getCommentId());

		if (result == 1) {
			comment = true;
		}

		return comment;
	}

	private boolean addToPotholesComments(CommentDTO newComment) {

		boolean comment = false;

		String addToPotholesComments = "INSERT INTO potholes_comments (pothole_id, comment_id) VALUES (?, ?)";

		int result = jdbcTemplate.update(addToPotholesComments, newComment.getPotholeId(), newComment.getCommentId());

		if (result == 1) {
			comment = true;
		}
		return comment;
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

	private User mapToUser(SqlRowSet u) {

		User user = new User();

		user.setId(u.getLong("user_id"));
		user.setUsername(u.getString("username"));

		return user;
	}

}

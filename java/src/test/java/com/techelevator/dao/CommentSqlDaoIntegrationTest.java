package com.techelevator.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.sql.DataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.ClaimDTO;
import com.techelevator.model.CommentDTO;
import com.techelevator.model.PotholeDTO;

public class CommentSqlDaoIntegrationTest extends DAOIntegrationTest {

	private CommentSqlDAO commentSqlDAO;
	private PotholeSqlDAO potholeSqlDAO;
	
	@Before
	public void setup() {
		DataSource dataSource = this.getDataSource();
		JdbcTemplate jdbcTemplate =  new JdbcTemplate(dataSource);
		commentSqlDAO =  new CommentSqlDAO(jdbcTemplate);
		potholeSqlDAO =  new PotholeSqlDAO(jdbcTemplate);
	}
	

	@Test
	public void getCommentsByUsername_ShouldReturnAllCommentsFromAUsername(){
		
		PotholeDTO newPothole = new PotholeDTO ("Low", new BigDecimal(99.99), new BigDecimal(99.99),"Reported", "user");

		potholeSqlDAO.createPothole(newPothole);
		Long potholeId = potholeSqlDAO.getPotholesId(newPothole);
		
		CommentDTO newComment =  new CommentDTO ((long) 99,"user", "Test comment", potholeId, "Test datetime",
										new BigDecimal(99.99), new BigDecimal(99.99));
		
		List<CommentDTO> commentsByUsernameBefore = commentSqlDAO.getCommentsByUsername("user");
		boolean createComment = commentSqlDAO.createComment(newComment);
		List<CommentDTO> commentsByUsernameAfter = commentSqlDAO.getCommentsByUsername("user");
		
		Assert.assertTrue(createComment);
		Assert.assertEquals(commentsByUsernameBefore.size() +1 , commentsByUsernameAfter.size());
	
	}
	
	@Test
	public void getCommentsByPotholeId_ShouldReturnAllCommentsFromAPotholeId() {
		
		PotholeDTO newPothole = new PotholeDTO ("Low", new BigDecimal(99.99), new BigDecimal(99.99),"Reported", "user");

		potholeSqlDAO.createPothole(newPothole);
		Long potholeId = potholeSqlDAO.getPotholesId(newPothole);
		
		CommentDTO newComment =  new CommentDTO ((long) 99,"user", "Test comment", potholeId, "Test datetime",
										new BigDecimal(99.99), new BigDecimal(99.99));
		List<CommentDTO> commentsBefore = commentSqlDAO.getCommentsByPotholeId(potholeId);
		boolean createComment = commentSqlDAO.createComment(newComment);
		List<CommentDTO> commentsAfter = commentSqlDAO.getCommentsByPotholeId(potholeId);
		
		Assert.assertTrue(createComment);
		Assert.assertEquals(commentsBefore.size() +1 , commentsAfter.size());

	}
	
	@Test
	public void createComment_ShouldCreateANewComment() {
		
		PotholeDTO newPothole = new PotholeDTO ("Low", new BigDecimal(99.99), new BigDecimal(99.99),"Reported", "user");

		potholeSqlDAO.createPothole(newPothole);
		Long potholeId = potholeSqlDAO.getPotholesId(newPothole);
		
		CommentDTO newComment =  new CommentDTO ((long) 99,"user", "Test comment", potholeId, "Test datetime",
										new BigDecimal(99.99), new BigDecimal(99.99));
		List<CommentDTO> commentsBefore = commentSqlDAO.getAllComments();
		boolean createComment = commentSqlDAO.createComment(newComment);
		List<CommentDTO> commentsAfter = commentSqlDAO.getAllComments();
		
		Assert.assertTrue(createComment);
		Assert.assertEquals(commentsBefore.size() +1 , commentsAfter.size());



		
	}
	
	@Test
	public void deleteComment_ShouldDeleteACommentByCommentId() {
		
		PotholeDTO newPothole = new PotholeDTO ("Low", new BigDecimal(99.99), new BigDecimal(99.99),"Reported", "user");

		potholeSqlDAO.createPothole(newPothole);
		Long potholeId = potholeSqlDAO.getPotholesId(newPothole);
		
		CommentDTO newComment =  new CommentDTO ((long) 99,"user", "Test comment", potholeId, "Test datetime",
										new BigDecimal(99.99), new BigDecimal(99.99));
		
		List<CommentDTO> commentsBefore = commentSqlDAO.getAllComments();
		boolean createComment = commentSqlDAO.createComment(newComment);
		List<CommentDTO> commentsDuring = commentSqlDAO.getAllComments();
		Long commentId = commentSqlDAO.getCommentId(newComment, "user");
		boolean deleteComment = commentSqlDAO.deleteComment(commentId);
		List<CommentDTO> commentsAfter = commentSqlDAO.getAllComments();
		
		Assert.assertTrue(createComment);
		Assert.assertTrue(deleteComment);
		Assert.assertEquals(commentsBefore.size(), commentsAfter.size());
		Assert.assertEquals(commentsBefore.size() +1, commentsDuring.size());


		
		
	}
}

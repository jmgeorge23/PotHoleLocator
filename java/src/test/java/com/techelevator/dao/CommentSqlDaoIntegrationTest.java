package com.techelevator.dao;
import com.techelevator.model.Comment;
import javax.sql.DataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class CommentSqlDaoIntegrationTest extends DAOIntegrationTest {

	private CommentSqlDAO commentSqlDAO;
	
	@Before
	public void setup() {
		DataSource dataSource = this.getDataSource();
		JdbcTemplate jdbcTemplate =  new JdbcTemplate(dataSource);
		commentSqlDAO =  new CommentSqlDAO(jdbcTemplate);
	}
	
	@Test
	public void getAllComments_ShouldReturnAllComments() {
		
	}
	
	@Test
	public void getCommentsByUsername_ShouldReturnAllCommentsFromAUsername(){
		
	}
	
	@Test
	public void getCommentsByPotholeId_ShouldReturnAllCommentsFromAPotholeId() {
		
	}
	
	@Test
	public void createComment_ShouldCreateANewComment() {
		
	}
	
	@Test
	public void deleteComment_ShouldDeleteACommentByCommentId() {
		
	}
}

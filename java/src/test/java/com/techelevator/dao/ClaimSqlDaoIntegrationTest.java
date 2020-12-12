package com.techelevator.dao;
import com.techelevator.model.Claim;
import javax.sql.DataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class ClaimSqlDaoIntegrationTest extends DAOIntegrationTest {
	
	private ClaimSqlDAO claimSqlDAO;
	

	@Before 
	public void setup() {
		DataSource dataSource =  this.getDataSource();
		JdbcTemplate jdbcTemplate =  new JdbcTemplate(dataSource);
		claimSqlDAO = new ClaimSqlDAO(jdbcTemplate);
		
	}
	
	@Test
	public void getAllClaims_ShouldReturnAllClaims() {
		
	}
	
	@Test
	public void getClaimById_ShouldReturnClaimbyClaimId() {
		
	}
	
	@Test 
	public void getUsersClaim_ShouldReturnAllClaimsfromAUsername() {
		
	}
	
	@Test
	public void createNewClaim_ShouldCreateNewClaim() {
		
	}
	
	@Test
	public void  updateClaim_ShouldUpdateExisitingClaim() {
		
	}

}

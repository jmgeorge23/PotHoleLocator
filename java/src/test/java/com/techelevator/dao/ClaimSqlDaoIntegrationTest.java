package com.techelevator.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.sql.DataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.ClaimDTO;
import com.techelevator.model.PotholeDTO;

public class ClaimSqlDaoIntegrationTest extends DAOIntegrationTest {
	
	private ClaimSqlDAO claimSqlDAO;
	private PotholeSqlDAO potholeSqlDAO;

	@Before 
	public void setup() {
		DataSource dataSource =  this.getDataSource();
		JdbcTemplate jdbcTemplate =  new JdbcTemplate(dataSource);
		claimSqlDAO = new ClaimSqlDAO(jdbcTemplate);
		potholeSqlDAO =  new PotholeSqlDAO(jdbcTemplate);
		
	}
	
	
	@Test
	public void getClaimById_ShouldReturnClaimbyClaimId() {
		
		PotholeDTO newPothole = new PotholeDTO ("Low", new BigDecimal(99.99), new BigDecimal(99.99),"Reported", "user");

		potholeSqlDAO.createPothole(newPothole);
		Long potholeId = potholeSqlDAO.getPotholesId(newPothole);
		
		ClaimDTO  newClaim =  new ClaimDTO( (long)99, new BigDecimal (100), "Approved", "Test Claim", "user", potholeId);
		boolean createClaim = claimSqlDAO.createClaim(newClaim);
		Long newClaimId =  claimSqlDAO.getClaimId(newClaim);
		ClaimDTO returnedClaim = claimSqlDAO.getClaimById(newClaimId);
		
		Assert.assertTrue(createClaim);
		Assert.assertEquals(newClaimId, returnedClaim.getClaimId());
//		Assert.assertEquals(new BigDecimal (100.00 ), returnedClaim.getClaimAmount());
		Assert.assertEquals("Approved", returnedClaim.getStatus());
		Assert.assertEquals("Test Claim", returnedClaim.getDescription());
		Assert.assertEquals("user", returnedClaim.getUsername());
	}
	
	@Test 
	public void getUsersClaim_ShouldReturnAllClaimsfromAUsername() {
		
		PotholeDTO newPothole = new PotholeDTO ("Low", new BigDecimal(99.99), new BigDecimal(99.99),"Reported", "user");

		potholeSqlDAO.createPothole(newPothole);
		Long potholeId = potholeSqlDAO.getPotholesId(newPothole);
		
		ClaimDTO  newClaim =  new ClaimDTO( (long)99, new BigDecimal (100), "Approved", "Test Claim", "user", potholeId);
		List<ClaimDTO> claimsByUsernameBefore = claimSqlDAO.getUsersClaim("user");
		boolean createClaim = claimSqlDAO.createClaim(newClaim);
		List<ClaimDTO> claimsByUsernameAfter = claimSqlDAO.getUsersClaim("user");
		
		Assert.assertTrue(createClaim);
		Assert.assertEquals(claimsByUsernameBefore.size() +1 , claimsByUsernameAfter.size());

	}
	
	@Test
	public void createNewClaim_ShouldCreateNewClaim() {
		
		PotholeDTO newPothole = new PotholeDTO ("Low", new BigDecimal(99.99), new BigDecimal(99.99),"Reported", "user");

		potholeSqlDAO.createPothole(newPothole);
		Long potholeId = potholeSqlDAO.getPotholesId(newPothole);
		
		ClaimDTO  newClaim =  new ClaimDTO( (long)99, new BigDecimal (100), "Approved", "Test Claim", "user", potholeId);
		
		List<ClaimDTO> allClaimsBefore =  claimSqlDAO.getAllClaims();
		boolean createClaim = claimSqlDAO.createClaim(newClaim);
		List<ClaimDTO> allClaimsAfter =  claimSqlDAO.getAllClaims();
		
		Assert.assertTrue(createClaim);
		Assert.assertEquals(allClaimsBefore.size() +1 , allClaimsAfter.size());

		
	}
	
	@Test
	public void  updateClaim_ShouldUpdateExisitingClaim() {
		
		PotholeDTO newPothole = new PotholeDTO ("Low", new BigDecimal(99.99), new BigDecimal(99.99),"Reported", "user");

		potholeSqlDAO.createPothole(newPothole);
		Long potholeId = potholeSqlDAO.getPotholesId(newPothole);
		
		ClaimDTO  newClaim =  new ClaimDTO( (long)99, new BigDecimal (100), "Approved", "Test Claim", "user", potholeId);
		
		List<ClaimDTO> allClaimsBefore =  claimSqlDAO.getAllClaims();
		boolean createClaim = claimSqlDAO.createClaim(newClaim);
		ClaimDTO  updatedClaim =  new ClaimDTO( (long)99, new BigDecimal (50), "Denied", "Test Claim", "user", potholeId);
		Long newClaimId =  claimSqlDAO.getClaimId(newClaim);
		List<ClaimDTO> allClaimsDuring =  claimSqlDAO.getAllClaims();

		boolean updateClaim = claimSqlDAO.updateClaim(updatedClaim, newClaimId.intValue());
		List<ClaimDTO> allClaimsAfter =  claimSqlDAO.getAllClaims();
		
		Assert.assertTrue(createClaim);
		Assert.assertTrue(updateClaim);
		Assert.assertEquals(allClaimsBefore.size() +1 , allClaimsAfter.size());
		Assert.assertEquals(allClaimsDuring.size(), allClaimsAfter.size());

	
	}

}

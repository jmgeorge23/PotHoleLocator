package com.techelevator.dao;

import com.techelevator.model.Pothole;
import com.techelevator.model.PotholeDTO;

import java.math.BigDecimal;
import java.util.List;

import javax.sql.DataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class PotholeSqlDaoIntegrationTest extends DAOIntegrationTest {

	private PotholeSqlDAO potholeSqlDAO;

	
	

	
	@Before
	public void setup() {
		

		DataSource dataSource = this.getDataSource();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		potholeSqlDAO = new PotholeSqlDAO(jdbcTemplate);
		
		
	
	}

//	   @Test
//	   public void findAllPotholes_ShouldReturnAllPotholes() {
//		   
//	   }
//
//	   @Test
//	   public void getPotholeById_ShouldReturnAPotholeByPotholeId() {
//		   
//	   }
//	   
//	   @Test
//	   public void getPotholeByStatus_ShouldReturnAllPotholesOfACertainStatus(){
//		   
//	   }

	@Test
	public void createPothole_ShouldCreateANewPothole() {
		PotholeDTO newPothole = new PotholeDTO ("Low", new BigDecimal(99.99), new BigDecimal(99.99),"Reported");

		List<PotholeDTO> allPotholes = potholeSqlDAO.findAllPotholes();
		boolean potholeCreated = potholeSqlDAO.createPothole(newPothole);
		List<PotholeDTO> checkAllPotholes = potholeSqlDAO.findAllPotholes();

//		Assert.assertTrue(potholeCreated);
		Assert.assertEquals(allPotholes.size() + 1, checkAllPotholes.size());

	}

//	@Test
//	public void updatePothole_ShouldUpdateAPotholeByPotholeId() {
//
//	}
//
//	@Test
//	public void deletePothole_ShouldDeleteAPotholeByPotholeId() {
//
//	}
}

package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.Claim;

public class ClaimSqlDAO implements ClaimDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public ClaimSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Claim> getAllClaims() {
		List<Claim> reports = new ArrayList<>();
		Claim claims = null;
		
		
		
		
		return reports;
	}

	@Override
	public Claim getClaimById(Long ClaimId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Claim> getUsersClaim(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Claim createClaim(Claim newClaim) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Claim updateClaim(Claim updatedClaim) {
		// TODO Auto-generated method stub
		return null;
		
	}
	private Claim maptoClaim(SqlRowSet cl) {
		
		Claim claims = new Claim();
		
		claims.setClaimId(cl.getLong("claim_id"));
		claims.setClaimAmount(cl.getDouble("claim_amount"));
		claims.setStatusId(cl.getLong("status_id"));
		claims.setUserId(cl.getLong("user_id"));
		claims.setPotholeId(cl.getLong("pothole_id"));
		
		return claims;
		

	
	}

	

	

}

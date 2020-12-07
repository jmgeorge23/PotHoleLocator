package com.techelevator.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.Claim;

public class ClaimSqlDAO implements ClaimDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public ClaimSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Claim> getAllClaims() {
		// TODO Auto-generated method stub
		return null;
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
	public Claim createClaim() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Claim updateClaim() {
		// TODO Auto-generated method stub
		return null;
	}


	

}

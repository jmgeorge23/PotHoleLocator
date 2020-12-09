package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.Claim;
import com.techelevator.model.ClaimDTO;

public class ClaimSqlDAO implements ClaimDAO {

	private JdbcTemplate jdbcTemplate;

	public ClaimSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<ClaimDTO> getAllClaims() {

		List<ClaimDTO> gettingAllClaims = new ArrayList<>();
		
		String gettingClaims = "SELECT * FROM claims;";
		
//		SqlRowSet result = jdbc
		
		return gettingAllClaims;
	}

	@Override
	public ClaimDTO getClaimById(Long ClaimId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClaimDTO> getUsersClaim(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createClaim(ClaimDTO newClaim) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean updateClaim(ClaimDTO updatedClaim) {
		// TODO Auto-generated method stub
		return true;

	}

	private ClaimDTO maptoClaim(SqlRowSet cl) {

		ClaimDTO claims = new ClaimDTO();

		claims.setClaimId(cl.getLong("claim_id"));
		claims.setClaimAmount(cl.getDouble("claim_amount"));
		claims.setStatusId(cl.getLong("status_id"));
		claims.setUserId(cl.getLong("user_id"));
		claims.setPotholeId(cl.getLong("pothole_id"));

		return claims;

	}

}

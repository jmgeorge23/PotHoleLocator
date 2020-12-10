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

		List<ClaimDTO> allClaims = new ArrayList<>();
		
		String gettingClaims = "SELECT * FROM claims;";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(gettingClaims);
		
		while(result.next()) {
			ClaimDTO claim = mapToClaim(result);
			allClaims.add(claim);
		}
		
		return allClaims;
	}

	@Override
	public ClaimDTO getClaimById(int claimId) {
		
		ClaimDTO claim=  null;
		
		String claimById = "SELECT * FROM claims WHERE claim__id =?;";
		
		SqlRowSet result =jdbcTemplate.queryForRowSet(claimById, claimId);
		
		while (result.next()) {
			claim = mapToClaim(result);
		}
		return claim;
	}

	@Override
	public List<ClaimDTO> getUsersClaim(int userId) {
		
		List<ClaimDTO> userClaims =  new ArrayList<>();
		
		String gettingClaimsByUser = "SELECT c.claim_id, c.amount, c.description, c.claim_status_id, uc.user_id FROM claims c INNER JOIN users_claims uc ON c.claim_id = uc.claim_id WHERE uc.user_id = ?;";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(gettingClaimsByUser);
		
		while(result.next()) {
			ClaimDTO claim = mapToClaim(result);
			userClaims.add(claim);
		}
			
		return userClaims;
	}

	@Override
	public boolean createClaim(ClaimDTO newClaim) {
		
		boolean claim = false;
		
		String createNewClaim ="INSERT INTO claims(claim_id, amount, description, claim_status_id) VALUES(DEFAULT, ?, ?,?);";
		
		int result = jdbcTemplate.update(createNewClaim, newClaim.getClaimId(), newClaim.getClaimAmount(), newClaim.getDescription(), newClaim.getStatusId() );
		
		
		
		return claim;
	}

	@Override
	public boolean updateClaim(ClaimDTO updatedClaim, int claimId) {
		// TODO Auto-generated method stub
		return true;

	}

	private ClaimDTO mapToClaim(SqlRowSet cl) {

		ClaimDTO claims = new ClaimDTO();

		claims.setClaimId(cl.getLong("claim_id"));
		claims.setClaimAmount(cl.getDouble("claim_amount"));
		claims.setStatusId(cl.getLong("claim_status_id"));
		claims.setUserId(cl.getLong("user_id"));
		claims.setPotholeId(cl.getLong("pothole_id"));

		return claims;

	}

}

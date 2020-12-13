package com.techelevator.dao;

import java.util.List;


import com.techelevator.model.ClaimDTO;

public interface ClaimDAO {
	
	//Get all claims
	List<ClaimDTO> getAllClaims ();
	
	//Get claim by claim Id
	ClaimDTO getClaimById(int claimId);
	
	//Get claim by username
	List<ClaimDTO> getUsersClaim (String username);
	
	//Add claim
	boolean createClaim (ClaimDTO newClaim);
	
	//Update Claim status
	boolean updateClaim (ClaimDTO updatedClaim, int claimId );
	
}

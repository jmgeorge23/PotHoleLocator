package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Claim;

public interface ClaimDAO {
	
	//Get all claims
	List<Claim> getAllClaims ();
	
	//Get claim by claim Id
	Claim getClaimById(Long ClaimId);
	
	//Get claim by user Id
	List<Claim> getUsersClaim (Long userId);
	
	//Add claim
	Claim createClaim ();
	
	//Update Claim status
	Claim updateClaim ();
	
}

package com.techelevator.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.ClaimDAO;
import com.techelevator.model.Claim;
import com.techelevator.model.ClaimDTO;

@RestController
public class ClaimController {
	
	private ClaimDAO claimDAO;
	
	public ClaimController(ClaimDAO claimDAO) {
		this.claimDAO = claimDAO;
	}
	
	//Get all claims
	@RequestMapping(path = "/claims", method = RequestMethod.GET)
	public List<ClaimDTO> listAllClaims() {
		return claimDAO.getAllClaims();
	}
	
	//Get claim by claim ID
	@RequestMapping(path = "/claims/{id}", method = RequestMethod.GET)
	public ClaimDTO getClaimById(@PathVariable int id) {
		return claimDAO.getClaimById(id);
	}
	
	//Get claim by username
	@RequestMapping(path = "/claims/usernameallss/{username}", method = RequestMethod.GET)
	public List<ClaimDTO> getUsersClaim(@PathVariable String username) {
		return claimDAO.getUsersClaim(username);
	}
	
	//Add Claim
//	@ResponseStatus(HttpStatus.CREATED)
//	@RequestMapping(value = "/claims", method = RequestMethod.POST);
//	public void addClaim(@Valid @RequestBody ClaimDTO newClaim) {
//		claimDAO.createClaim(newClaim);
//	}
	
	//Update claim status
//	@ResponseStatus(HttpStatus.OK)
//	@RequestMapping(value = "/claims/{id}", method = RequestMethod.PUT)
//	public void updateClaimStatus(@Valid @RequestBody Claim changedClaim, @PathVariable int id) {
//		claimDAO.updateClaim(changedClaim);
//	}
	
	
	

}

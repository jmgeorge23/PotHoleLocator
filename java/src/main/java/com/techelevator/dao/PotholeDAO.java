package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Pothole;
import com.techelevator.model.PotholeDTO;

public interface PotholeDAO {
	
	//get all potholes
	List<Pothole> findAllPotholes();
	
	//get pothole by id
	Pothole getPothole(Integer potholeId);
	
	//get pothole by status
	List<Pothole> getPotholeByStatus(Integer statusId);
	
	//add pothole
	boolean createPothole(PotholeDTO newPothole);
	
	
	//update pothole severity
	boolean updatePotholeSeverity(int potholeId, String severity);
	
	//update pothole status
	boolean updatePotholeStatus(int potholeId, String status);
	
	//update pothole general
	boolean updatePothole(PotholeDTO updatedPothole, int potholeId);
	
	//delete pothole
	boolean deletePothole(int potholeId);
	
	

}

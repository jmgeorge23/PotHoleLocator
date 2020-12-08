package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Pothole;

public interface PotholeDAO {
	
	//get all potholes
	List<Pothole> findAllPotholes();
	
	//get pothole by id
	Pothole getPothole(Long potholeId);
	
	//get pothole by status
	Pothole getPotholeByStatus(Integer statusId);
	
	//add pothole
	boolean createPothole(Pothole newPothole);
	
	//add pothole severity
	Pothole addPotholeSeverity(Integer severityId);
	
	//update pothole severity
	Pothole updatePotholeSeverity(Integer severityId);
	
	//update pothole status
	Pothole updatePotholeStatus(Integer stausId);
	
	//update pothole general
	Pothole updatePothole(Pothole updatedPothole);
	
	//delete pothole
	Pothole deletePothole(Long potholeId);
	
	

}

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
	boolean updatePotholeSeverity(Integer severityId,Integer potholeId);
	
	//update pothole status
	boolean updatePotholeStatus(Integer statusId,Integer potholeId);
	
	//update pothole general
	boolean updatePothole(Pothole updatedPothole);
	
	//delete pothole
	Pothole deletePothole(Long potholeId);
	
	

}

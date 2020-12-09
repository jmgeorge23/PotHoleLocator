package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Pothole;
import com.techelevator.model.PotholeDTO;

public interface PotholeDAO {

	// get all potholes
	List<PotholeDTO> findAllPotholes();

	// get pothole by id
	PotholeDTO getPotholeById(int potholeId);

	// get pothole by status
	List<PotholeDTO> getPotholeByStatus(int statusId);

	// add pothole
	boolean createPothole(PotholeDTO newPothole);

	// update pothole severity
	boolean updatePotholeSeverity(int potholeId, PotholeDTO updatedPothole);

	// update pothole status
	boolean updatePotholeStatus(int potholeId, PotholeDTO updatedPothole);

	// update pothole general
	boolean updatePothole(PotholeDTO updatedPothole, int potholeId);

	// delete pothole
	boolean deletePothole(int potholeId);

}

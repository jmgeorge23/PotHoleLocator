package com.techelevator.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.PotholeDAO;

import com.techelevator.model.PotholeAlreadyExistException;
import com.techelevator.model.PotholeDTO;
import com.techelevator.services.PotholeLatLngService;

@RestController
@CrossOrigin
public class PotholeController {

	private PotholeDAO potholeDAO;
	private PotholeLatLngService potholeService;

	public PotholeController(PotholeDAO potholeDAO) {
		this.potholeDAO = potholeDAO;
	}

	// Get all potholes
	@RequestMapping(value = "/potholes", method = RequestMethod.GET)
	public List<PotholeDTO> listAllPotholes() {
		return potholeDAO.findAllPotholes();
	}

	// Get pothole by ID
	@RequestMapping(value = "/potholes/{id}", method = RequestMethod.GET)
	public PotholeDTO getPothole(@PathVariable int id) {
		return potholeDAO.getPotholeById(id);
	}

	// Get pothole by Status
	@RequestMapping(value = "/potholes/status/{status}", method = RequestMethod.GET)
	public List<PotholeDTO> getPotholeByStatus(@PathVariable String status) {
		return potholeDAO.getPotholesByStatus(status);
	}

	// Add pothole
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/potholes", method = RequestMethod.POST)
	public void addPothole(@Valid @RequestBody PotholeDTO newPothole) {
//		BigDecimal range = new BigDecimal(0.00001);
//		BigDecimal currentLat = newPothole.getLatitude();
//		BigDecimal currentLng = newPothole.getLongitude();
//		
//		PotholeDTO potholePlusLat = newPothole;
//		PotholeDTO potholeMinusLat = newPothole;
//		PotholeDTO potholePlusLng = newPothole;
//		PotholeDTO potholeMinusLng = newPothole;
//		PotholeDTO potholePlusBoth = newPothole;
//		PotholeDTO potholeMinusBoth = newPothole;
//		PotholeDTO potholePlusLatMinusLng = newPothole;
//		PotholeDTO potholeMinusLatPlusLng = newPothole;
//
//		
//		potholePlusLat.setLatitude(currentLat.add(range)); 
//		potholeMinusLat.setLatitude(currentLat.subtract(range)); 
//		potholePlusLng.setLongitude(currentLng.add(range)); 
//		potholeMinusLng.setLongitude(currentLng.subtract(range)); 
//		potholePlusBoth.setLatitude(currentLat.add(range)); 
//		potholePlusBoth.setLongitude(currentLng.add(range)); 
//		potholeMinusBoth.setLatitude(currentLat.subtract(range)); 
//		potholeMinusBoth.setLongitude(currentLng.subtract(range)); 
//		potholePlusLatMinusLng.setLatitude(currentLat.add(range));
//		potholePlusLatMinusLng.setLongitude(currentLng.subtract(range));
//		potholeMinusLatPlusLng.setLatitude(currentLat.subtract(range));
//		potholeMinusLatPlusLng.setLongitude(currentLng.add(range));
//		
		PotholeDTO pothole1 = potholeDAO.getPotholeByLatLng(newPothole);
//		PotholeDTO pothole2  = potholeDAO.getPotholeByLatLng(potholePlusLat);
//		PotholeDTO pothole3  = potholeDAO.getPotholeByLatLng(potholeMinusLat);
//		PotholeDTO pothole4  = potholeDAO.getPotholeByLatLng(potholePlusLng);
//		PotholeDTO pothole5  = potholeDAO.getPotholeByLatLng(potholeMinusLng);
//		PotholeDTO pothole6  = potholeDAO.getPotholeByLatLng(potholePlusBoth);
//		PotholeDTO pothole7  = potholeDAO.getPotholeByLatLng(potholeMinusBoth);
//		PotholeDTO pothole8  = potholeDAO.getPotholeByLatLng(potholeMinusBoth);
//		PotholeDTO pothole9  = potholeDAO.getPotholeByLatLng(potholeMinusBoth);


//		if (pothole1 == null && pothole2 == null && pothole3 == null && pothole4 == null && pothole5 == null
//				&& pothole6 == null && pothole7 == null && pothole8 == null && pothole9 == null) {
		if(pothole1 == null) {
			potholeDAO.createPothole(newPothole);
		} else {
			throw new PotholeAlreadyExistException();
		}
	}

	// Update pothole
	@RequestMapping(value = "/potholes/{potholeId}", method = RequestMethod.PUT)
	public void updatePothole(@Valid @RequestBody PotholeDTO updatedPothole, @PathVariable int potholeId) {
		potholeDAO.updatePothole(updatedPothole, potholeId);
	}

	// Update pothole severity
	@RequestMapping(value = "/potholes/{potholeId}/severity", method = RequestMethod.PUT)
	public void updatePotholeSeverity(@Valid @RequestBody PotholeDTO updatedPothole, @PathVariable int potholeId) {
		potholeDAO.updatePotholeSeverity(potholeId, updatedPothole);
	}

	// Update pothole status
	@RequestMapping(value = "/potholes/{potholeId}/status", method = RequestMethod.PUT)
	public void updatePotholeStatus(@Valid @RequestBody PotholeDTO updatedPothole, @PathVariable int potholeId) {
		potholeDAO.updatePotholeStatus(potholeId, updatedPothole);
	}

	// Delete pothole Might not use may just changed status to deleted
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/potholes/{potholeId}", method = RequestMethod.DELETE)
	public void deletelPothole(@PathVariable int potholeId) {
		potholeDAO.deletePothole(potholeId);
	}
}

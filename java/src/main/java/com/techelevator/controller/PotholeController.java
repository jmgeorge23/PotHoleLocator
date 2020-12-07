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

import com.techelevator.dao.PotholeDAO;
import com.techelevator.model.Pothole;
import com.techelevator.model.PotholeDTO;

@RestController
public class PotholeController {
	
	private PotholeDAO potholeDAO;

	public PotholeController(PotholeDAO potholeDAO) {
		this.potholeDAO = potholeDAO;
	}
	
//	@RequestMapping(value = "/potholes", method = RequestMethod.GET)
//	public List<Pothole> listAllPotholes() {
//		return potholeDAO.findAllPotholes();
//	}
	
//	@RequestMapping(value = "/potholes/{id}", method = RequestMethod.GET)
//	public Pothole getPothole(@PathVariable int id) {
//		return potholeDAO.getPothole(id);
//	}
	
//	@ResponseStatus(HttpStatus.CREATED)
//	@RequestMapping(value = "/potholes", method = RequestMethod.POST)
//	public void addPothole(@Valid @RequestBody PotholeDTO newPothole) {
//		potholeDAO.createPothole(newPothole);
//	}
	
//	@RequestMapping(value = "potholes/{id}", method = RequestMethod.PUT)
//	public void updatePotholeStatus(@Valid @RequestBody Pothole potholeStatus, @PathVariable int id) {
//		potholeDAO.updatePotholeStatus(id);
//	}
	
//	@ResponseStatus(HttpStatus.OK)
//	@RequestMapping(value = "/potholes/{id}", method = RequestMethod.DELETE)
//	public void deletelPothole(@PathVariable int id) {
//		potholeDAO.deletePothole(id);
//	}
}

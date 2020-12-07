package com.techelevator.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.LocationDAO;
import com.techelevator.model.Location;

@RestController
public class LocationController {
	
	private LocationDAO locationDAO;

	public LocationController(LocationDAO locationDAO) {
		this.locationDAO = locationDAO;
	}
	
//	@RequestMapping(path = "/locations", method = RequestMethod.GET)
//	public List<Location> listAllLocations() {
//		return locationDAO.getAllPotholeLocations();
//	}
	
	
//	@RequestMapping(path = "/locations/{id}", method = RequestMethod.PUT)
//	public void updatePotholeLocation(@Valid @RequestBody Location updatedLocation, @PathVariable int id) {
//		locationDAO.updatePotholeLocation(updatedLocation);
//	}
}

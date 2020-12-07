package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Location;

public interface LocationDAO {

	//get all pothole locations
	List<Location> getAllPotholeLocations();
	
	//add pothole location
	Location createLocation();
	
	//update pothole location
	Location updatePotholelocation();
	
	
	
	
}

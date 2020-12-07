package com.techelevator.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.Location;

public class LocationSqlDAO implements LocationDAO{

	private JdbcTemplate jdbcTemplate;

	public LocationSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Location> getAllPotholeLocations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location createLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location updatePotholelocation() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

package com.techelevator.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.Pothole;

public class PotholeSqlDAO implements PotholeDAO{
	
	private JdbcTemplate jdbcTemplate;

	public PotholeSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Pothole> findAllPotholes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pothole getPothole(Long potholeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pothole getPotholeByStatus(Integer statusId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pothole createPothole(Pothole newPothole) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pothole addPotholeSeverity(Integer severityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pothole updatePotholeSeverity(Integer severityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pothole updatePotholeStatus(Integer stausId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pothole deletePothole(Long potholeId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Pothole mapToPothole(SqlRowSet ph) {
		
		Pothole potholes = new Pothole();
		potholes.setPotholeId(ph.getLong("pothole_id"));
		potholes.setStatusId(ph.getInt("status_id"));
		potholes.setLocationId(ph.getLong("location_id"));
		potholes.setSeverityId(ph.getInt("severity_id"));
		return potholes;
		
		
	}

	@Override
	public Pothole updatePothole(Pothole updatedPothole) {
		// TODO Auto-generated method stub
		return null;
	}

}

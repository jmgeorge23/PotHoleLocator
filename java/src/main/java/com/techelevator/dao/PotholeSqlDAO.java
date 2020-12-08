package com.techelevator.dao;

import java.util.ArrayList;
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
		
		List<Pothole> allPotholes = new ArrayList<>();
		
//		String getAllPotholes = "SELECT p.pothole_id, l.lat, l.lng, ps.status, s.severity FROM potholes p INNER JOIN locations l ON p.location_id = l.location_id INNER JOIN pothole_status ps ON p.pothole_status_id = ps.pothole_status_id INNER JOIN severity s ON s.severity_id = p.severity_id;"; 
//		
//				SqlRowSet result = jdbcTemplate.queryForRowSet(getAllPotholes);
//				
//				while(result.next()) {
//					Pothole pothole = mapToPothole(result);
//					allPotholes.add(pothole);
//				}
				
		return allPotholes ;
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
		potholes.setStatus(ph.getString("status"));
		potholes.setSeverity(ph.getString("severity"));
		potholes.setUserId(ph.getLong("user_id"));
		potholes.setLatitude(ph.getLong("lat"));
		potholes.setLongitude(ph.getLong("lng"));
		potholes.setRoadName(ph.getString("road_name"));
		potholes.setDirection(ph.getString("direction"));
		potholes.setLane(ph.getString("lane"));
		return potholes;
		
		
	}

	@Override
	public Pothole updatePothole(Pothole updatedPothole) {
		// TODO Auto-generated method stub
		return null;
	}

}

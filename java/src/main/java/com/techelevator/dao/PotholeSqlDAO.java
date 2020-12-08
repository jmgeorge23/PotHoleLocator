package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.Pothole;
@Service
public class PotholeSqlDAO implements PotholeDAO{

	private JdbcTemplate jdbcTemplate;

	public PotholeSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Pothole> findAllPotholes() {

		List<Pothole> allPotholes = new ArrayList<>();

		String getAllPotholes = "SELECT p.pothole_id, p.lat, p.lng, ps.status, s.severity FROM potholes p  INNER JOIN pothole_status ps ON p.pothole_status_id = ps.pothole_status_id INNER JOIN severity s ON s.severity_id = p.severity_id"; 

		SqlRowSet result = jdbcTemplate.queryForRowSet(getAllPotholes);

		while(result.next()) {
			Pothole pothole = mapToPothole(result);
			allPotholes.add(pothole);
		}

		return allPotholes ;
	}

	@Override
	public Pothole getPothole(Long potholeId) {

		Pothole potholes = null;

		String potholeById = "SELECT p.pothole_id, p.lat, p.lng, ps.status, s.severity FROM potholes p INNER JOIN pothole_status ps ON p.pothole_status_id = ps.pothole_status_id INNER JOIN severity s ON s.severity_id = p.severity_id WHERE pothole_id = ?;"; 

		SqlRowSet result = jdbcTemplate.queryForRowSet(potholeById, potholeId);

		while(result.next()) {
			potholes = mapToPothole(result);

		}

		return potholes;
	}

	@Override
	public Pothole getPotholeByStatus(Integer statusId) {
		Pothole potholes = null;

		String potholeByStat = "SELECT p.pothole_id, p.lat, p.lng, ps.status, s.severity FROM potholes p INNER JOIN pothole_status ps ON p.pothole_status_id = ps.pothole_status_id INNER JOIN severity s ON s.severity_id = p.severity_id WHERE status_id = ?;"; 

		SqlRowSet result = jdbcTemplate.queryForRowSet(potholeByStat, statusId);

		while(result.next()) {
			potholes = mapToPothole(result);
		}

		return potholes;
	}


	@Override
	public boolean createPothole(Pothole newPothole) {
		boolean potholes = false;
		
		String makePothole = "BEGIN TRANSACTION;"
						+ "INSERT INTO potholes(pothole_id, lat, lng, pothole_status_id, severity_id)"
						+ "VALUES(DEFAULT(?,?,(SELECT pothole_status_id FROM pothole_status WHERE status = 'Reported'),(SELECT severity_id FROM severity WHERE severity = 'Not Inspected')"
						+ "COMMIT";
		
		int result = jdbcTemplate.update(makePothole, newPothole.getLatitude(), newPothole.getLongitude(), newPothole.getStatus(), newPothole.getSeverity());
		
		if( result == 1) {
			potholes = true;
		}
	return potholes;
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
		potholes.setLatitude(ph.getBigDecimal("lat"));
		potholes.setLongitude(ph.getBigDecimal("lng"));
		potholes.setStatus(ph.getString("status"));
		potholes.setSeverity(ph.getString("severity"));
//		potholes.setRoadName(ph.getString("roadname"));
//		potholes.setDirection(ph.getString("direction"));
//		potholes.setLane(ph.getString("lane"));
//		potholes.setUserId(ph.getLong("user_id"));
		return potholes;


	}

	@Override
	public Pothole updatePothole(Pothole updatedPothole) {
		// TODO Auto-generated method stub
		return null;
	}

}

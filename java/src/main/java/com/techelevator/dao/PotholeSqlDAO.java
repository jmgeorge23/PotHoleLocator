package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.Pothole;
import com.techelevator.model.PotholeDTO;
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
	public Pothole getPothole(Integer potholeId) {

		Pothole potholes = null;

		String potholeById = "SELECT p.pothole_id, p.lat, p.lng, ps.status, s.severity FROM potholes p INNER JOIN pothole_status ps ON p.pothole_status_id = ps.pothole_status_id INNER JOIN severity s ON s.severity_id = p.severity_id WHERE pothole_id = ?;"; 

		SqlRowSet result = jdbcTemplate.queryForRowSet(potholeById, potholeId);

		while(result.next()) {
			potholes = mapToPothole(result);

		}

		return potholes;
	}

	@Override
	public List<Pothole> getPotholeByStatus(Integer statusId) {
		
		List<Pothole> potholesStat =  new ArrayList<>();

		String potholeByStat = "SELECT p.pothole_id, p.lat, p.lng, ps.status, s.severity FROM potholes p INNER JOIN pothole_status ps ON p.pothole_status_id = ps.pothole_status_id INNER JOIN severity s ON s.severity_id = p.severity_id WHERE status_id = ?;"; 

		SqlRowSet result = jdbcTemplate.queryForRowSet(potholeByStat, statusId);

		while(result.next()) {
			Pothole pothole = mapToPothole(result);
			potholesStat.add(pothole);
		}


		return potholesStat;
	}


	@Override
	public boolean createPothole(PotholeDTO newPothole) {
		boolean potholes = false;
		
//		String makePothole = "BEGIN TRANSACTION;"
//						+ "INSERT INTO potholes(pothole_id, lat, lng, pothole_status_id, severity_id)"
//						+ "VALUES(DEFAULT(?,?,(SELECT pothole_status_id FROM pothole_status WHERE status = ?),(SELECT severity_id FROM severity WHERE severity = ?)"
//						+ "COMMIT";
//		
//		int result = jdbcTemplate.update(makePothole, newPothole.getLatitude(), newPothole.getLongitude(), newPothole.getStatus(), newPothole.getSeverity());
//		
//		if( result == 0) {
//			potholes = true;
//		}
	return potholes;
	}

	@Override
	public boolean addPotholeSeverity(Integer severityId, Integer potholeId) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean updatePotholeSeverity(Integer severityId, Integer potholeId) {
		boolean potholes = false;
		
		String updateSeverity="UPDATE potholes SET severity_id =? WHERE pothole_id = ?";
		
		int result = jdbcTemplate.update(updateSeverity,severityId, potholeId);
				
				if( result == 0) {
					potholes = true;
				}
			return potholes;
			}

	@Override
	public boolean updatePotholeStatus(Integer statusId,Integer potholeId ) {
		boolean potholes = false;
		
		String updateStatus="UPDATE potholes SET pothole_status_id =? WHERE pothole_id = ?";
		
		int result = jdbcTemplate.update(updateStatus,statusId, potholeId);
				
				if( result == 0) {
					potholes = true;
				}
			return potholes;
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
	public boolean updatePothole(Pothole updatedPothole) {
		// TODO Auto-generated method stub
		return true;
	}

}

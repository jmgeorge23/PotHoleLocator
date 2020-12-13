package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;


import com.techelevator.model.PotholeDTO;

@Service
public class PotholeSqlDAO implements PotholeDAO {

	private JdbcTemplate jdbcTemplate;
	

	public PotholeSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	

	@Override
	public List<PotholeDTO> findAllPotholes() {

		List<PotholeDTO> allPotholes = new ArrayList<>();

		String getAllPotholes = "SELECT p.pothole_id, p.lat, p.lng, ps.status, s.severity FROM potholes p "
				+ "INNER JOIN pothole_status ps ON p.pothole_status_id = ps.pothole_status_id "
				+ "INNER JOIN severity s ON s.severity_id = p.severity_id";

		SqlRowSet result = jdbcTemplate.queryForRowSet(getAllPotholes);

		while (result.next()) {
			PotholeDTO pothole = mapToPothole(result);
			allPotholes.add(pothole);
		}

		return allPotholes;
	}

	@Override
	public PotholeDTO getPotholeById(int potholeId) {

		PotholeDTO potholes = null;

		String potholeById = "SELECT p.pothole_id, p.lat, p.lng, ps.status, s.severity FROM potholes p "
				+ "INNER JOIN pothole_status ps ON p.pothole_status_id = ps.pothole_status_id "
				+ "INNER JOIN severity s ON s.severity_id = p.severity_id WHERE pothole_id = ?;";

		SqlRowSet result = jdbcTemplate.queryForRowSet(potholeById, potholeId);

		while (result.next()) {
			potholes = mapToPothole(result);

		}

		return potholes;
	}

	@Override
	public List<PotholeDTO> getPotholesByStatus(String status) {

		List<PotholeDTO> potholesStatus = new ArrayList<>();

		String potholeByStatus = "SELECT p.pothole_id, p.lat, p.lng, ps.status, s.severity FROM potholes p "
				+ "INNER JOIN pothole_status ps ON p.pothole_status_id = ps.pothole_status_id "
				+ "INNER JOIN severity s ON s.severity_id = p.severity_id WHERE ps.status = ?;";

		SqlRowSet result = jdbcTemplate.queryForRowSet(potholeByStatus, status);

		while (result.next()) {
			PotholeDTO pothole = mapToPothole(result);
			potholesStatus.add(pothole);
		}

		return potholesStatus;
	}
	
	@Override
	public PotholeDTO getPotholeByLatLng(PotholeDTO newPothole) {
		PotholeDTO pothole = null;
		
		String getPotholeByLatLng = "SELECT p.pothole_id, p.lat, p.lng, ps.status, s.severity FROM potholes p "
								+ "INNER JOIN pothole_status ps ON p.pothole_status_id = ps.pothole_status_id "
								+ "INNER JOIN severity s ON s.severity_id = p.severity_id WHERE lat = ? AND lng = ?";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(getPotholeByLatLng, newPothole.getLatitude(), newPothole.getLongitude());
		
		while(result.next()) {
			pothole = mapToPothole(result);
		}
		
		return pothole;
	}

	@Override
	public boolean createPothole(PotholeDTO newPothole) {
		
		boolean potholes = false;

		newPothole.setStatus("Reported");

		String addToPotholes = 
				 "INSERT INTO potholes(pothole_id, lat, lng, pothole_status_id, severity_id)"
				+ "VALUES(DEFAULT,?,?,(SELECT pothole_status_id FROM pothole_status WHERE status = ?),"
				+ "(SELECT severity_id FROM severity WHERE severity = ?));";
				

		int result = jdbcTemplate.update(addToPotholes, newPothole.getLatitude(), newPothole.getLongitude(),
				newPothole.getStatus(), newPothole.getSeverity());

		if (result == 1) {

			Long potholeWithId = getPotholesId(newPothole);
			newPothole.setPotholeId(potholeWithId);

			if (addToPotholesUsers(newPothole)) {

				if (addToPotholesHistory(newPothole)) {

					potholes = true;
					
				}
			}
		}
		return potholes;
	}

	@Override
	public boolean updatePothole(PotholeDTO updatedPothole, int potholeId) {

		boolean potholes = false;

		String updatePotholes = "UPDATE potholes SET pothole_status_id=(SELECT pothole_status_id FROM pothole_status WHERE status = ?), "
				+ "severity_id =(SELECT severity_id FROM severity WHERE severity = ?), lat = ?, lng = ? WHERE pothole_id =?;";

		if (addToPotholesHistory(updatedPothole)) {
			int result = jdbcTemplate.update(updatePotholes, updatedPothole.getStatus(), updatedPothole.getSeverity(),
					updatedPothole.getLatitude(), updatedPothole.getLongitude(), potholeId);

			if (result == 1) {
				potholes = true;
			}
		}
		return potholes;
	}

	@Override
	public boolean updatePotholeSeverity(int potholeId, PotholeDTO updatedPothole) {
		boolean potholes = false;

		String updateSeverity = "UPDATE potholes SET severity_id =(SELECT severity_id FROM severity WHERE severity=?) WHERE pothole_id = ?";

		if (addToPotholesHistory(updatedPothole)) {

			int result = jdbcTemplate.update(updateSeverity, updatedPothole.getSeverity(), potholeId);

			if (result == 1) {
				potholes = true;
			}
		}
		return potholes;
	}

	@Override
	public boolean updatePotholeStatus(int potholeId, PotholeDTO updatedPothole) {
		boolean potholes = false;

		String updateStatus = "UPDATE potholes SET pothole_status_id =(SELECT pothole_status_id FROM pothole_status WHERE status =?) "
				+ "WHERE pothole_id = ?";

		if (addToPotholesHistory(updatedPothole)) {

			int result = jdbcTemplate.update(updateStatus, updatedPothole.getStatus(), potholeId);

			if (result == 1) {
				potholes = true;
			}
		}
		return potholes;
	}

	@Override
	public boolean deletePothole(int potholeId) {

		boolean potholes = false;

		if (deleteFromPotholesUsers(potholeId)) {

			if (deleteFromPotholesImages(potholeId)) {

				if (deleteFromPotholesComments(potholeId)) {

					if (deleteFromPotholesClaims(potholeId)) {

						String deleteFromPotholes = "DELETE FROM potholes WHERE pothole_id = ? ";

						PotholeDTO deletedPothole = getPotholeById(potholeId);

						deletedPothole.setStatus("Deleted");

						if (addToPotholesHistory(deletedPothole)) {

							int result = jdbcTemplate.update(deleteFromPotholes, potholeId);

							if (result == 1) {
								potholes = true;
							}
						}
					}
				}
			}
		}
		return potholes;
	}

	private boolean addToPotholesHistory(PotholeDTO newPothole) {

		boolean potholes = false;

		String addToPotholesHistory = "INSERT INTO potholes_history "
				+ "(pothole_history_id, pothole_id, pothole_status_id, severity_id, lat, lng, datetime) "
				+ "VALUES(DEFAULT, ? ,(SELECT pothole_status_id FROM pothole_status WHERE status = ?),"
				+ "(SELECT severity_id FROM severity WHERE severity = ?), ?, ?, CURRENT_TIMESTAMP)";

		int result = jdbcTemplate.update(addToPotholesHistory, newPothole.getPotholeId(), newPothole.getStatus(),
				newPothole.getSeverity(), newPothole.getLatitude(), newPothole.getLongitude());

		if (result == 1) {
			potholes = true;
		}
		return potholes;

	}

	private boolean addToPotholesUsers(PotholeDTO newPothole) {

		boolean potholes = false;

		String addToPotholesUsers = "INSERT INTO users_potholes (user_id, pothole_id) VALUES ((SELECT user_id FROM users WHERE username = ?), ?)";

		int result = jdbcTemplate.update(addToPotholesUsers, newPothole.getUsername(),
				newPothole.getPotholeId());

		if (result == 1) {
			potholes = true;
		}
		return potholes;

	}

	private boolean deleteFromPotholesUsers(int potholeId) {

		boolean potholes = false;

		String deleteFromUsersPotholes = "DELETE FROM users_potholes WHERE pothole_id =?";

		int result = jdbcTemplate.update(deleteFromUsersPotholes, potholeId);

		if (result >= 0) {
			potholes = true;
		}
		return potholes;

	}

	private boolean deleteFromPotholesImages(int potholeId) {

		boolean potholes = false;

		String deleteFromUsersPotholes = "DELETE FROM potholes_images WHERE pothole_id =?";

		int result = jdbcTemplate.update(deleteFromUsersPotholes, potholeId);

		if (result >= 0) {
			potholes = true;
		}
		return potholes;

	}

	private boolean deleteFromPotholesComments(int potholeId) {

		boolean potholes = false;

		String deleteFromUsersPotholes = "DELETE FROM potholes_comments WHERE pothole_id =?";

		int result = jdbcTemplate.update(deleteFromUsersPotholes, potholeId);

		if (result >= 0) {
			potholes = true;
		}
		return potholes;

	}

	// TODO: Should this add to users_claims_history
	private boolean deleteFromPotholesClaims(int potholeId) {

		boolean potholes = false;

		String deleteFromUsersPotholes = "DELETE FROM potholes_claims WHERE pothole_id =?";

		int result = jdbcTemplate.update(deleteFromUsersPotholes, potholeId);

		if (result >= 0) {
			potholes = true;
		}
		return potholes;

	}

	public Long getPotholesId(PotholeDTO newPothole) {

		

		String getPotholeId = "SELECT p.pothole_id FROM potholes p INNER JOIN pothole_status ps ON p.pothole_status_id = ps.pothole_status_id "
				+ "INNER JOIN severity s ON s.severity_id = p.severity_id WHERE lat = ? AND lng = ?";

		return jdbcTemplate.queryForObject(getPotholeId,long.class, newPothole.getLatitude(), newPothole.getLongitude());

		
		
	}

	private PotholeDTO mapToPothole(SqlRowSet ph) {

		PotholeDTO potholes = new PotholeDTO();
//		String roadName = ph.getString("roadname");
//		String direction = ph.getString("direction");
//		String lane = ph.getString("lane");
//		Long userId = ph.getLong("user_id");

		potholes.setPotholeId(ph.getLong("pothole_id"));
		potholes.setLatitude(ph.getBigDecimal("lat"));
		potholes.setLongitude(ph.getBigDecimal("lng"));
		potholes.setStatus(ph.getString("status"));
		potholes.setSeverity(ph.getString("severity"));

	

		return potholes;

	}

	

}

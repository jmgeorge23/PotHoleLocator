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

	// Method to get a list of all active potholes
	@Override
	public List<PotholeDTO> findAllPotholes() {

		List<PotholeDTO> allPotholes = new ArrayList<>();

		String getAllPotholes = "SELECT p.pothole_id, p.lat, p.lng, ps.status, s.severity, p.roadname, p.direction FROM potholes p "
				+ "INNER JOIN pothole_status ps ON p.pothole_status_id = ps.pothole_status_id "
				+ "INNER JOIN severity s ON s.severity_id = p.severity_id";

		SqlRowSet result = jdbcTemplate.queryForRowSet(getAllPotholes);

		while (result.next()) {
			PotholeDTO pothole = mapToPothole(result);
			allPotholes.add(pothole);
		}

		return allPotholes;
	}

	// Method to get a pothole from a pothole ID
	@Override
	public PotholeDTO getPotholeById(int potholeId) {

		PotholeDTO potholes = null;

		String potholeById = "SELECT p.pothole_id, p.lat, p.lng, ps.status, s.severity, p.roadname, p.direction  FROM potholes p "
				+ "INNER JOIN pothole_status ps ON p.pothole_status_id = ps.pothole_status_id "
				+ "INNER JOIN severity s ON s.severity_id = p.severity_id WHERE pothole_id = ?;";

		SqlRowSet result = jdbcTemplate.queryForRowSet(potholeById, potholeId);

		while (result.next()) {
			potholes = mapToPothole(result);

		}

		return potholes;
	}

	// Method to get a list of potholes based on pothole Status
	@Override
	public List<PotholeDTO> getPotholesByStatus(String status) {

		List<PotholeDTO> potholesStatus = new ArrayList<>();

		String potholeByStatus = "SELECT p.pothole_id, p.lat, p.lng, ps.status, s.severity, p.roadname, p.direction  FROM potholes p "
				+ "INNER JOIN pothole_status ps ON p.pothole_status_id = ps.pothole_status_id "
				+ "INNER JOIN severity s ON s.severity_id = p.severity_id WHERE ps.status = ?;";

		SqlRowSet result = jdbcTemplate.queryForRowSet(potholeByStatus, status);

		while (result.next()) {
			PotholeDTO pothole = mapToPothole(result);
			potholesStatus.add(pothole);
		}

		return potholesStatus;
	}

	// Method to get a pothole from Lat and Lng
	@Override
	public PotholeDTO getPotholeByLatLng(PotholeDTO newPothole) {
		PotholeDTO pothole = null;

		String getPotholeByLatLng = "SELECT p.pothole_id, p.lat, p.lng, ps.status, s.severity, p.roadname, p.direction  FROM potholes p "
				+ "INNER JOIN pothole_status ps ON p.pothole_status_id = ps.pothole_status_id "
				+ "INNER JOIN severity s ON s.severity_id = p.severity_id WHERE lat = ? AND lng = ?";

		SqlRowSet result = jdbcTemplate.queryForRowSet(getPotholeByLatLng, newPothole.getLatitude(),
				newPothole.getLongitude());

		while (result.next()) {
			pothole = mapToPothole(result);
		}

		return pothole;
	}

	// Method to get a newly created potholes ID
	public Long getPotholesId(PotholeDTO newPothole) {

		String getPotholeId = "SELECT p.pothole_id FROM potholes p INNER JOIN pothole_status ps ON p.pothole_status_id = ps.pothole_status_id "
				+ "INNER JOIN severity s ON s.severity_id = p.severity_id WHERE lat = ? AND lng = ?";

		return jdbcTemplate.queryForObject(getPotholeId, long.class, newPothole.getLatitude(),
				newPothole.getLongitude());

	}

	// Method to create a new pothole
	// This method will also add the pothole to the potholes_history table
	@Override
	public boolean createPothole(PotholeDTO newPothole) {

		boolean potholes = false;
		// Set all new potholes to have the status of reported
		newPothole.setStatus("Reported");

		String addToPotholes = "INSERT INTO potholes(pothole_id, lat, lng, pothole_status_id, severity_id, roadname, direction)"
				+ "VALUES(DEFAULT,?,?,(SELECT pothole_status_id FROM pothole_status WHERE status = ?),"
				+ "(SELECT severity_id FROM severity WHERE severity = ?),?,?);";
		// Adds the pothole to the potholes table
		int result = jdbcTemplate.update(addToPotholes, newPothole.getLatitude(), newPothole.getLongitude(),
				newPothole.getStatus(), newPothole.getSeverity(), newPothole.getRoadName(), newPothole.getDirection());

		if (result == 1) {
			// Returns the ID of the newly created pothole
			Long potholeWithId = getPotholesId(newPothole);
			// Sets the new pothole with the ID that was returned above
			newPothole.setPotholeId(potholeWithId);
			// Adds the new pothole to the pothole_users table
			if (addToPotholesUsers(newPothole)) {
				// Adds the new pothole to the pothole_history table
				if (addToPotholesHistory(newPothole)) {

					potholes = true;

				}
			}
		}
		return potholes;
	}

	// Method to update any data in a pothole
	// This method will also add the pothole to the potholes_history table
	@Override
	public boolean updatePothole(PotholeDTO updatedPothole, int potholeId) {

		boolean potholes = false;

			String updatePotholes = "UPDATE potholes SET pothole_status_id=(SELECT pothole_status_id FROM pothole_status WHERE status = ?), "
					+ "severity_id =(SELECT severity_id FROM severity WHERE severity = ?), lat = ?, lng = ?, roadname = ?, direction = ? WHERE pothole_id =?;";
			// Adds the updated pothole to the pothole_history table
			if (addToPotholesHistory(updatedPothole)) {
				// Updates the pothole in the potholes table
				int result = jdbcTemplate.update(updatePotholes, updatedPothole.getStatus(), updatedPothole.getSeverity(),
						updatedPothole.getLatitude(), updatedPothole.getLongitude(), updatedPothole.getRoadName(), updatedPothole.getDirection(), potholeId);
	
				if (result == 1) {
					potholes = true;
				}
			}

		return potholes;
	}

	// Method to udate just a potholes severity
	// This method will also add the pothole to the potholes_history table
	@Override
	public boolean updatePotholeSeverity(int potholeId, PotholeDTO updatedPothole) {
		boolean potholes = false;

		String updateSeverity = "UPDATE potholes SET severity_id =(SELECT severity_id FROM severity WHERE severity=?) WHERE pothole_id = ?";
		// Adds the updated pothole to the potholes_history table
		if (addToPotholesHistory(updatedPothole)) {
			// Updates the potholes severity
			int result = jdbcTemplate.update(updateSeverity, updatedPothole.getSeverity(), potholeId);

			if (result == 1) {
				potholes = true;
			}
		}
		return potholes;
	}

	// Method to update a potholes status
	// This method will also add the pothole to the potholes_history table
	@Override
	public boolean updatePotholeStatus(int potholeId, PotholeDTO updatedPothole) {
		boolean potholes = false;

		String updateStatus = "UPDATE potholes SET pothole_status_id =(SELECT pothole_status_id FROM pothole_status WHERE status =?) "
				+ "WHERE pothole_id = ?";
		// Adds the updated pothole to the pothole_history table
		if (addToPotholesHistory(updatedPothole)) {
			// Updates the pothole in the potholes table
			int result = jdbcTemplate.update(updateStatus, updatedPothole.getStatus(), potholeId);

			if (result == 1) {
				potholes = true;
			}
		}
		return potholes;
	}

	// Method to delete a pothole from the potholes table and all associative tables.
	// This method will also add the pothole to the potholes_history table
	@Override
	public boolean deletePothole(int potholeId) {

		boolean potholes = false;
		// Deletes the pothole from the pothole_users table
		if (deleteFromPotholesUsers(potholeId)) {
			// Deletes the pothole from the pothole_images table
			if (deleteFromPotholesImages(potholeId)) {
				// Deletes the pothole from the pothole_comments table
				if (deleteFromPotholesComments(potholeId)) {
					// Deletes the pothole from the pothole_claims table
					if (deleteFromPotholesClaims(potholeId)) {

						String deleteFromPotholes = "DELETE FROM potholes WHERE pothole_id = ? ";

						PotholeDTO deletedPothole = getPotholeById(potholeId);
						// Sets status to deleted before adding it to potholes_history table
						deletedPothole.setStatus("Deleted");
						// Adds the pothole to the potholes_history table
						if (addToPotholesHistory(deletedPothole)) {
							// Deletes the pothole from the potholes table
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

	// Method to add a pothole to the potholes_history table
	private boolean addToPotholesHistory(PotholeDTO newPothole) {

		boolean potholes = false;

		String addToPotholesHistory = "INSERT INTO potholes_history "
				+ "(pothole_history_id, pothole_id, pothole_status_id, severity_id, lat, lng, roadname,direction, datetime) "
				+ "VALUES(DEFAULT, ? ,(SELECT pothole_status_id FROM pothole_status WHERE status = ?),"
				+ "(SELECT severity_id FROM severity WHERE severity = ?), ?, ?,?,?, CURRENT_TIMESTAMP)";

		int result = jdbcTemplate.update(addToPotholesHistory, newPothole.getPotholeId(), newPothole.getStatus(),
				newPothole.getSeverity(), newPothole.getLatitude(), newPothole.getLongitude(), newPothole.getRoadName(), newPothole.getDirection());

		if (result == 1) {
			potholes = true;
		}
		return potholes;

	}

	// Method to add a pothole to the potholes_users table
	private boolean addToPotholesUsers(PotholeDTO newPothole) {

		boolean potholes = false;

		String addToPotholesUsers = "INSERT INTO users_potholes (user_id, pothole_id) VALUES ((SELECT user_id FROM users WHERE username = ?), ?)";

		int result = jdbcTemplate.update(addToPotholesUsers, newPothole.getUsername(), newPothole.getPotholeId());

		if (result == 1) {
			potholes = true;
		}
		return potholes;

	}

	// Method to delete a pothole from the potholes_users table
	private boolean deleteFromPotholesUsers(int potholeId) {

		boolean potholes = false;

		String deleteFromUsersPotholes = "DELETE FROM users_potholes WHERE pothole_id =?";

		int result = jdbcTemplate.update(deleteFromUsersPotholes, potholeId);

		if (result >= 0) {
			potholes = true;
		}
		return potholes;

	}

	// Method to delete a pothole from the potholes_images table
	private boolean deleteFromPotholesImages(int potholeId) {

		boolean potholes = false;

		String deleteFromUsersPotholes = "DELETE FROM potholes_images WHERE pothole_id =?";

		int result = jdbcTemplate.update(deleteFromUsersPotholes, potholeId);

		if (result >= 0) {
			potholes = true;
		}
		return potholes;

	}

	// Method to delete a pothole from the potholes_comments table
	private boolean deleteFromPotholesComments(int potholeId) {

		boolean potholes = false;

		String deleteFromUsersPotholes = "DELETE FROM potholes_comments WHERE pothole_id =?";

		int result = jdbcTemplate.update(deleteFromUsersPotholes, potholeId);

		if (result >= 0) {
			potholes = true;
		}
		return potholes;

	}

	// Method to delete a pothole from the potholes_claims table
	private boolean deleteFromPotholesClaims(int potholeId) {

		boolean potholes = false;

		String deleteFromUsersPotholes = "DELETE FROM potholes_claims WHERE pothole_id =?";

		int result = jdbcTemplate.update(deleteFromUsersPotholes, potholeId);

		if (result >= 0) {
			potholes = true;
		}
		return potholes;

	}

	// Method to map a Sql row set to a pothole object
	private PotholeDTO mapToPothole(SqlRowSet ph) {

		PotholeDTO potholes = new PotholeDTO();
	
//		String lane = ph.getString("lane");
//		Long userId = ph.getLong("user_id");

		potholes.setPotholeId(ph.getLong("pothole_id"));
		potholes.setLatitude(ph.getBigDecimal("lat"));
		potholes.setLongitude(ph.getBigDecimal("lng"));
		potholes.setStatus(ph.getString("status"));
		potholes.setSeverity(ph.getString("severity"));
		potholes.setRoadName(ph.getString("roadname"));
		potholes.setDirection(ph.getString("direction"));
//		potholes.setUsername(ph.getString("username"));

		return potholes;

	}

}

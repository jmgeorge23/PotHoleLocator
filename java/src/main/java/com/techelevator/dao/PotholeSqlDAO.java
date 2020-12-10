package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.Pothole;
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

		String getAllPotholes = "SELECT p.pothole_id, p.lat, p.lng, ps.status, s.severity FROM potholes p  INNER JOIN pothole_status ps ON p.pothole_status_id = ps.pothole_status_id INNER JOIN severity s ON s.severity_id = p.severity_id";

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

		String potholeById = "SELECT p.pothole_id, p.lat, p.lng, ps.status, s.severity FROM potholes p INNER JOIN pothole_status ps ON p.pothole_status_id = ps.pothole_status_id INNER JOIN severity s ON s.severity_id = p.severity_id WHERE pothole_id = ?;";

		SqlRowSet result = jdbcTemplate.queryForRowSet(potholeById, potholeId);

		while (result.next()) {
			potholes = mapToPothole(result);

		}

		return potholes;
	}

	@Override
	public List<PotholeDTO> getPotholeByStatus(int statusId) {

		List<PotholeDTO> potholesStat = new ArrayList<>();

		String potholeByStat = "SELECT p.pothole_id, p.lat, p.lng, ps.status, s.severity FROM potholes p INNER JOIN pothole_status ps ON p.pothole_status_id = ps.pothole_status_id INNER JOIN severity s ON s.severity_id = p.severity_id WHERE p.pothole_status_id = ?;";

		SqlRowSet result = jdbcTemplate.queryForRowSet(potholeByStat, statusId);

		while (result.next()) {
			PotholeDTO pothole = mapToPothole(result);
			potholesStat.add(pothole);
		}

		return potholesStat;
	}

	@Override
	public boolean createPothole(PotholeDTO newPothole) {
		boolean potholes = false;

		String addToPotholes = "BEGIN TRANSACTION;"
				+ "INSERT INTO potholes(pothole_id, lat, lng, pothole_status_id, severity_id)"
				+ "VALUES(DEFAULT,?,?,(SELECT pothole_status_id FROM pothole_status WHERE status = ?),(SELECT severity_id FROM severity WHERE severity = ?));"
				+ "COMMIT;";

		int result = jdbcTemplate.update(addToPotholes, newPothole.getLatitude(), newPothole.getLongitude(),
				newPothole.getStatus(), newPothole.getSeverity());

		if (result == 1) {

			PotholeDTO potholeWithId = getPotholesId(newPothole);

			if (addToPotholesUsers(potholeWithId, newPothole)) {

				if (addToPotholesHistory(potholeWithId)) {

					potholes = true;
					
				}
			}
		}
		return potholes;
	}

	@Override
	public boolean updatePothole(PotholeDTO updatedPothole, int potholeId) {

		boolean potholes = false;

		String updatePotholes = "UPDATE potholes SET pothole_status_id=(SELECT pothole_status_id FROM pothole_status WHERE status = ?), severity_id =(SELECT severity_id FROM severity WHERE severity = ?), lat = ?, lng = ? WHERE pothole_id =?;";

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

		String updateStatus = "UPDATE potholes SET pothole_status_id =(SELECT pothole_status_id FROM pothole_status WHERE status =?) WHERE pothole_id = ?";

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

		if (deleteFromPotholeUsers(potholeId)) {

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
		return potholes;
	}

	private boolean addToPotholesHistory(PotholeDTO pothole) {

		boolean potholes = false;

		String addToPotholesHistory = "INSERT INTO potholes_history "
				+ "(pothole_history_id, pothole_id, pothole_status_id, severity_id, lat, lng, datetime) "
				+ "VALUES(DEFAULT, ? ,(SELECT pothole_status_id FROM pothole_status WHERE status = ?),"
				+ "(SELECT severity_id FROM severity WHERE severity = ?), ?, ?, CURRENT_TIMESTAMP)";

		int result = jdbcTemplate.update(addToPotholesHistory, pothole.getPotholeId(), pothole.getStatus(),
				pothole.getSeverity(), pothole.getLatitude(), pothole.getLongitude());

		if (result == 1) {
			potholes = true;
		}
		return potholes;

	}

	private boolean addToPotholesUsers(PotholeDTO potholeWithId, PotholeDTO potholeWithUserId) {

		boolean potholes = false;

		String addToPotholesUsers = "INSERT INTO users_potholes (user_id, pothole_id) VALUES ( ?, ?)";

		int result = jdbcTemplate.update(addToPotholesUsers, potholeWithUserId.getUserId(),
				potholeWithId.getPotholeId());

		if (result == 1) {
			potholes = true;
		}
		return potholes;

	}

	private boolean deleteFromPotholeUsers(int potholeId) {

		boolean potholes = false;

		String deleteFromUsersPotholes = "DELETE from users_potholes WHERE pothole_id =?";

		int result = jdbcTemplate.update(deleteFromUsersPotholes, potholeId);

		if (result == 1) {
			potholes = true;
		}
		return potholes;

	}

	private PotholeDTO getPotholesId(PotholeDTO pothole) {

		PotholeDTO potholes = null;

		String getPotholeId = "SELECT p.pothole_id, p.lat, p.lng, ps.status, s.severity FROM potholes p INNER JOIN pothole_status ps ON p.pothole_status_id = ps.pothole_status_id "
				+ "INNER JOIN severity s ON s.severity_id = p.severity_id WHERE lat = ? AND lng = ?";

		SqlRowSet result = jdbcTemplate.queryForRowSet(getPotholeId, pothole.getLatitude(), pothole.getLongitude());

		while (result.next()) {
			potholes = mapToPothole(result);

		}

		return potholes;
	}

	private PotholeDTO mapToPothole(SqlRowSet ph) {

		PotholeDTO potholes = new PotholeDTO();

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

}

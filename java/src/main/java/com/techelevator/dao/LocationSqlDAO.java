//package com.techelevator.dao;
//
//import java.util.List;
//
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.support.rowset.SqlRowSet;
//
//import com.techelevator.model.Location;
//
//public class LocationSqlDAO implements LocationDAO{
//
//	private JdbcTemplate jdbcTemplate;
//
//	public LocationSqlDAO(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}
//
//	@Override
//	public List<Location> getAllPotholeLocations() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Location createLocation(Location newLocation) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Location updatePotholelocation(Location updatedLocation) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//	private Location mapToLocation(SqlRowSet l) {
//		
//		Location locations = new Location();
//		locations.setLocationId(l.getLong("location_id"));
//		locations.setLatitude(l.getLong("latitude"));
//		locations.setLongitude(l.getLong("longitude"));
//		locations.setRoadName(l.getString("road_name"));
//		locations.setDirection(l.getString("direction"));
//		locations.setLane(l.getString("lane"));
//				
//		return locations;
//		
//		
//	}
//	
//}

package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class LocationSqlDAO implements LocationDAO{

	private JdbcTemplate jdbcTemplate;

	public LocationSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
}

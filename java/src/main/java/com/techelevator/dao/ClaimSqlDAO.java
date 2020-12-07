package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class ClaimSqlDAO implements ClaimDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public ClaimSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	

}

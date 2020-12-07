package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class PotholeSqlDAO implements PotholeDAO{
	
	private JdbcTemplate jdbcTemplate;

	public PotholeSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}

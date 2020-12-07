package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class ImageSqlDAO implements ImageDAO {
	
	private JdbcTemplate jdbcTemplate;

	public ImageSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}

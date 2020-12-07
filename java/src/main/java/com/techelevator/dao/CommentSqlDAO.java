package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class CommentSqlDAO implements CommentDAO {

private JdbcTemplate jdbcTemplate;
	
	public CommentSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}

package com.techelevator.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.Pothole;

public class PotholeSqlDAO implements PotholeDAO{
	
	private JdbcTemplate jdbcTemplate;

	public PotholeSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Pothole> findAllPotholes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pothole getPothole(Long potholeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pothole getPotholeByStatus(Integer statusId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pothole createPothole() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pothole addPotholeSeverity(Integer severityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pothole updatePotholeSeverity(Integer severityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pothole updatePotholeStatus(Integer stausId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pothole deletePothole() {
		// TODO Auto-generated method stub
		return null;
	}

}

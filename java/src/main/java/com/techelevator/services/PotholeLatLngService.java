package com.techelevator.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.techelevator.model.PotholeAlreadyExistException;
import com.techelevator.model.PotholeDTO;
import com.techelevator.dao.PotholeDAO;

@Service
public class PotholeLatLngService {

	private PotholeDAO potholeDAO;

	public PotholeDTO LatLngService(PotholeDTO newPothole) {

		PotholeDTO pothole = null;
		List<PotholeDTO> LatLngRanges = new ArrayList<>();

		BigDecimal range = new BigDecimal(0.00001);
		BigDecimal currentLat = newPothole.getLatitude();
		BigDecimal currentLng = newPothole.getLongitude();

		PotholeDTO newPotholePlusLat = newPothole;
//		PotholeDTO newPotholeMinusLat = newPothole;
//		PotholeDTO newPotholePlusLng = newPothole;
//		PotholeDTO newPotholeMinusLng = newPothole;
//		PotholeDTO newPotholePlusLatPlusLng = newPothole;
//		PotholeDTO newPotholeMinusLatMinusLng = newPothole;

		newPotholePlusLat.setLatitude(currentLat.add(range));
		newPotholePlusLat.setLongitude(currentLng);
//		newPotholeMinusLat.setLatitude(currentLat.subtract(range));
//		newPotholePlusLng.setLongitude(currentLng.add(range));
//		newPotholeMinusLng.setLongitude(currentLng.subtract(range));
//		newPotholePlusLatPlusLng.setLatitude(currentLat.add(range));
//		newPotholePlusLatPlusLng.setLongitude(currentLng.add(range));
//		newPotholeMinusLatMinusLng.setLatitude(currentLat.subtract(range));
//		newPotholeMinusLatMinusLng.setLongitude(currentLng.subtract(range));
//
//		LatLngRanges.add(newPotholePlusLat);
//		LatLngRanges.add(newPotholeMinusLat);
//		LatLngRanges.add(newPotholePlusLng);
//		LatLngRanges.add(newPotholePlusLng);
//		LatLngRanges.add(newPotholePlusLatPlusLng);
//		LatLngRanges.add(newPotholeMinusLatMinusLng);

//		for (PotholeDTO rangePotholes : LatLngRanges) {
//
//			PotholeDTO LatLngpothole = potholeDAO.getPotholeByLatLng(rangePotholes);
//
//			if (LatLngpothole != null) {
//				pothole = rangePotholes;
//
//			}
//
//		}
		
		PotholeDTO pothole1 = potholeDAO.getPotholeByLatLng(newPotholePlusLat);
//		PotholeDTO pothole2 = potholeDAO.getPotholeByLatLng(newPotholeMinusLat);
//		PotholeDTO pothole3 = potholeDAO.getPotholeByLatLng(newPotholeMinusLng);
//		PotholeDTO pothole4 = potholeDAO.getPotholeByLatLng(newPotholePlusLng);
//		PotholeDTO pothole5 = potholeDAO.getPotholeByLatLng(newPotholePlusLatPlusLng);
//		PotholeDTO pothole6 = potholeDAO.getPotholeByLatLng(newPotholeMinusLatMinusLng);
		
		if(pothole1 != null) {
			pothole = pothole1;
	}
//		if(pothole2 != null) {
//			pothole = pothole2;
//		}
//		if(pothole3 != null) {
//			pothole = pothole3;
//		}
//		if(pothole4 != null) {
//			pothole = pothole4;
//		}
//		if(pothole5 != null) {
//			pothole = pothole5;
//		}
//		if(pothole6 != null) {
//			pothole = pothole6;
//		}


		return pothole;
	}
}

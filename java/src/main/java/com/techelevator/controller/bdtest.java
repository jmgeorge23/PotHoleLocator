package com.techelevator.controller;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class bdtest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BigDecimal test = new BigDecimal(39.11111);
		BigDecimal range = new BigDecimal(.00001);
		//range.setScale(5, BigDecimal.ROUND_UP);
		DecimalFormat df = new DecimalFormat("##.#####");
		//test.setScale(3, BigDecimal.ROUND_HALF_EVEN);

		
		test = test.setScale(5, RoundingMode.UP);
		
		
		System.out.println(test.add(range).setScale(5, RoundingMode.UP));
	}

}

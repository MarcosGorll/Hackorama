package com.careem.kmsandmore.business;

import org.springframework.stereotype.Service;

@Service
public class PeakService {

	public double peakMultiplier(long timeInMillis) {
		return /*new Random().nextDouble() +*/ 1.0;
	}
	
}

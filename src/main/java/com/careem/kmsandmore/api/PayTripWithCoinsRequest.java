package com.careem.kmsandmore.api;

public class PayTripWithCoinsRequest {

	private double value;
	private double percentage;

	public PayTripWithCoinsRequest() {
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

}

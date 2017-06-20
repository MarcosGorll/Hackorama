package com.careem.kmsandmore.api;

public class TripEndingRequest {

	private double kilometers;
	private int paymentTypeCode;

	public TripEndingRequest() {
	}

	public double getKilometers() {
		return kilometers;
	}

	public void setKilometers(double kilometers) {
		this.kilometers = kilometers;
	}

	public int getPaymentTypeCode() {
		return paymentTypeCode;
	}

	public void setPaymentTypeCode(int paymentTypeCode) {
		this.paymentTypeCode = paymentTypeCode;
	}

}

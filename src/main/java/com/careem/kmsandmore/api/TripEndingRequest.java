package com.careem.kmsandmore.api;

public class TripEndingRequest {

	private double value;
	private int paymentTypeCode;

	public TripEndingRequest() {
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getPaymentTypeCode() {
		return paymentTypeCode;
	}

	public void setPaymentTypeCode(int paymentTypeCode) {
		this.paymentTypeCode = paymentTypeCode;
	}

}

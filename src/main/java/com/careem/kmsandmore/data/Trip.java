package com.careem.kmsandmore.data;

import java.util.Date;

public class Trip {

	private Date date;
	private double kilometers;
	private int paymentTypeId;

	public Trip() {
	}

	public Trip(Date date, double kilometers, int paymentTypeId) {
		this.date = date;
		this.kilometers = kilometers;
		this.paymentTypeId = paymentTypeId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getKilometers() {
		return kilometers;
	}

	public void setKilometers(double kilometers) {
		this.kilometers = kilometers;
	}

	public int getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(int paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

}

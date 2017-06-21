package com.careem.kmsandmore.data;

import java.util.Date;

public class Trip {

	private Date date;
	private double value;
	private int paymentTypeId;

	public Trip() {
	}

	public Trip(Date date, double value, int paymentTypeId) {
		this.date = date;
		this.value = value;
		this.paymentTypeId = paymentTypeId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(int paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

}

package com.careem.kmsandmore.data;

public class CareemWallet {

	private double coins;

	public CareemWallet() {
	}

	public double getCoins() {
		return coins;
	}

	public void setCoins(double coins) {
		this.coins = coins;
	}

	public void addCoins(double coins) {
		this.setCoins(getCoins() + coins);
	}

}

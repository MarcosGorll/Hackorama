package com.careem.kmsandmore.data;

public enum PaymentType {

	CASH(1),

	CREDIT_CARD(2);

	private final int code;

	private PaymentType(int code) {
		this.code = code;
	}

	public static PaymentType forCode(int code) {
		for (PaymentType paymentType : PaymentType.values()) {
			if (code == paymentType.getCode())
				return paymentType;
		}
		return null;
	}

	public int getCode() {
		return code;
	}

}

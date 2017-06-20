package com.careem.kmsandmore.business;

import org.springframework.stereotype.Service;

import com.careem.kmsandmore.data.PaymentType;

@Service
public class PaymentService {

	public double paymentCoinsMultiplier(PaymentType paymentType) {
		return paymentType == PaymentType.CREDIT_CARD ? 1.5 : 1.0;
	}

}

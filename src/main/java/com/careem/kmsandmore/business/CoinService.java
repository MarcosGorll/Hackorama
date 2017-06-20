package com.careem.kmsandmore.business;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.careem.kmsandmore.api.TransferCoinsRequest;
import com.careem.kmsandmore.api.TripEndingRequest;
import com.careem.kmsandmore.data.CareemWallet;
import com.careem.kmsandmore.data.PaymentType;
import com.careem.kmsandmore.data.User;
import com.careem.kmsandmore.data.UserRepository;

@Service
public class CoinService {

	@Autowired
	PaymentService paymentService;

	@Autowired
	PeakService peakService;

	@Autowired
	UserRepository userRepository;

	public CareemWallet getWallet(String userId) {
		User user = userRepository.findOne(userId);
		if (user == null) {
			return null;
		}
		return user.getWallet();
	}

	@Async
	public void registerTripEnding(String userId, TripEndingRequest tripEndingRequest) {
		User user = userRepository.findOne(userId);
		if (user == null) {
			return;
		}
		double coins = tripEndingRequest.getKilometers() * 0.001;// TODO
		coins *= paymentService.paymentCoinsMultiplier(PaymentType.forCode(tripEndingRequest.getPaymentTypeCode()));
		coins *= peakService.peakMultiplier(System.currentTimeMillis());
		user.getWallet().setCoins(coins);
		user.incTrip();
		userRepository.save(user);
	}

	@Async
	public void transferCoinsToUser(String userId, String targetUser, TransferCoinsRequest transferCoinsRequest) {
		User user = userRepository.findOne(userId);
		if (user == null) {
			return;
		}

		User target = userRepository.findOne(targetUser);
		if (target == null) {
			return;
		}

		if (user.getWallet().getCoins() < transferCoinsRequest.getAmount()) {
			return;
		}

		user.getWallet().removeCoins(transferCoinsRequest.getAmount());
		target.getWallet().addCoins(transferCoinsRequest.getAmount());

		userRepository.save(Arrays.asList(user, target));
	}

}

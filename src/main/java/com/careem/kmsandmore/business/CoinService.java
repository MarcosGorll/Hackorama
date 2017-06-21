package com.careem.kmsandmore.business;

import java.util.Arrays;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.careem.kmsandmore.api.RedeemResponse;
import com.careem.kmsandmore.api.TransferCoinsRequest;
import com.careem.kmsandmore.api.TripEndingRequest;
import com.careem.kmsandmore.data.CareemWallet;
import com.careem.kmsandmore.data.PaymentType;
import com.careem.kmsandmore.data.Product;
import com.careem.kmsandmore.data.ProductRepository;
import com.careem.kmsandmore.data.Trip;
import com.careem.kmsandmore.data.User;
import com.careem.kmsandmore.data.UserRepository;

@Service
public class CoinService {

	private static Logger LOG = LoggerFactory.getLogger(CoinService.class);

	@Autowired
	PaymentService paymentService;

	@Autowired
	PeakService peakService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProductRepository productRepository;

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
		double coins = tripEndingRequest.getValue() * 2.0;
		coins *= paymentService.paymentCoinsMultiplier(PaymentType.forCode(tripEndingRequest.getPaymentTypeCode()));
		coins *= peakService.peakMultiplier(System.currentTimeMillis());
		user.incTrip();
		user.getTrips().add(new Trip(new Date(), tripEndingRequest.getValue(), tripEndingRequest.getPaymentTypeCode()));
		user.getWallet().addCoins(coins);
		userRepository.save(user);
	}

	public RedeemResponse transferCoinsToUser(String userId, String targetUser, TransferCoinsRequest transferCoinsRequest) {
		User user = userRepository.findOne(userId);
		if (user == null) {
			return new RedeemResponse(false, "User not found.");
		}

		User target = userRepository.findOne(targetUser);
		if (target == null) {
			return new RedeemResponse(false, "User not found.");
		}

		if (user.getWallet().getCoins() < transferCoinsRequest.getAmount()) {
			return new RedeemResponse(false, "Amount not available.");
		}

		user.getWallet().removeCoins(transferCoinsRequest.getAmount());
		target.getWallet().addCoins(transferCoinsRequest.getAmount());

		userRepository.save(Arrays.asList(user, target));

		return new RedeemResponse(true, "OK.");
	}

	public RedeemResponse payTrip(String userId, double value, double percentage) {
		User user = userRepository.findOne(userId);
		if (user == null) {
			return new RedeemResponse(false, "User not found.");
		}

		double valueInCoins = value * percentage * 100.0;

		if (user.getWallet().getCoins() < valueInCoins) {
			return new RedeemResponse(false, "Amount not available.");
		}

		user.getWallet().removeCoins(valueInCoins);
		userRepository.save(user);
		return new RedeemResponse(true, "OK.");
	}

	public RedeemResponse burnCoins(String userId, double amount) {
		User user = userRepository.findOne(userId);
		if (user == null) {
			return new RedeemResponse(false, "User not found.");
		}
		if (user.getWallet().getCoins() < amount) {
			return new RedeemResponse(false, "Amount not available.");
		}

		user.getWallet().removeCoins(amount);
		userRepository.save(user);
		return new RedeemResponse(true, "OK.");
	}

	public RedeemResponse buyProduct(String userId, long productId) {
		User user = userRepository.findOne(userId);
		if (user == null) {
			return new RedeemResponse(false, "User not found.");
		}

		Product product = productRepository.findOne(productId);
		if (product == null) {
			return new RedeemResponse(false, "Product not found.");
		}

		if (user.getWallet().getCoins() < product.getPrice()) {
			return new RedeemResponse(false, "Amount not available.");
		}

		user.getWallet().removeCoins(product.getPrice());
		userRepository.save(user);

		return new RedeemResponse(true, "OK.");
	}

}

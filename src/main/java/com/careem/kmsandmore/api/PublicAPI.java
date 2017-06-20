package com.careem.kmsandmore.api;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.careem.kmsandmore.data.CareemWallet;
import com.careem.kmsandmore.data.User;
import com.careem.kmsandmore.data.UserRepository;

@RequestMapping("/careemcoin")
@RestController
public class PublicAPI {

	@Autowired
	UserRepository userRepository;

	// Sanity
	@RequestMapping(path = "/test", method = RequestMethod.GET)
	public String test() {
		return "OK";
	}

	@RequestMapping(path = "/{userId}/wallet", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CareemWallet getWallet(@PathVariable("userId") String userId) {
		User user = userRepository.findOne(userId);
		if (user == null) {
			return null;
		}
		return user.getWallet();
	}

	@RequestMapping(path = "/{userId}/endtrip", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void registerTripEnding(@PathVariable("userId") String userId, @RequestBody TripEndingRequest tripEndingRequest) {
		User user = userRepository.findOne(userId);
		if (user == null) {
			return;
		}
		user.getWallet().setCoins(tripEndingRequest.getKilometers() * 0.001); // TODO
		userRepository.save(user);
	}

	@RequestMapping(path = "/{userId}/{targetUser}/transfer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void transferCoinsToUser(@PathVariable("userId") String userId, @PathVariable("targetUser") String targetUser, @RequestBody TransferCoinsRequest transferCoinsRequest) {
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

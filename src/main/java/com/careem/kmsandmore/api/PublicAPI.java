package com.careem.kmsandmore.api;

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
		user.getWallet().setCoins(tripEndingRequest.getKilometers() * 0.001);
		userRepository.save(user);
	}
	
	

}

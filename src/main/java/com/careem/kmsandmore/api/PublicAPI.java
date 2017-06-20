package com.careem.kmsandmore.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.careem.kmsandmore.business.CoinService;
import com.careem.kmsandmore.data.CareemWallet;
import com.careem.kmsandmore.data.Product;
import com.careem.kmsandmore.data.ProductRepository;

@RequestMapping("/careemcoin")
@RestController
public class PublicAPI {

	@Autowired
	CoinService coinService;

	@Autowired
	ProductRepository productRepository;

	@RequestMapping(path = "/{userId}/wallet", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public CareemWallet getWallet(@PathVariable("userId") String userId) {
		return coinService.getWallet(userId);
	}

	@RequestMapping(path = "/{userId}/endtrip", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void registerTripEnding(@PathVariable("userId") String userId, @RequestBody TripEndingRequest tripEndingRequest) {
		coinService.registerTripEnding(userId, tripEndingRequest);
	}

	@RequestMapping(path = "/products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	@RequestMapping(path = "/{userId}/{targetUser}/transfer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void transferCoinsToUser(@PathVariable("userId") String userId, @PathVariable("targetUser") String targetUser, @RequestBody TransferCoinsRequest transferCoinsRequest) {
		coinService.transferCoinsToUser(userId, targetUser, transferCoinsRequest);
	}

}

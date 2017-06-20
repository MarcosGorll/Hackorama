package com.careem.kmsandmore.business;

import com.careem.kmsandmore.data.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarmupService {
	
	private static Logger LOG = LoggerFactory.getLogger(WarmupService.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProductRepository productRepository;

	public void createDefaultRecords() {
		createDefaultProductsIfNotExists();
		createDefaultUsersIfNotExists();
	}

	public void createDefaultUsersIfNotExists() {
		
		if (!userRepository.exists("ozan")) {
			LOG.info("Creating Ozan");
			
			User user = new User();
			user.setId("ozan");
			user.setName("Ozan Yildiz");
			user.setWallet(new CareemWallet());
			userRepository.save(user);
		}
		
		if (!userRepository.exists("marcos")) {
			LOG.info("Creating Marcos");
			
			User user = new User();
			user.setId("marcos");
			user.setName("Marcos Gorll");
			user.setWallet(new CareemWallet());
			userRepository.save(user);
		}
		
		if (!userRepository.exists("selim")) {
			LOG.info("Creating Selim");
			
			User user = new User();
			user.setId("selim");
			user.setName("Selim Turki");
			user.setWallet(new CareemWallet());
			userRepository.save(user);
		}
		
	}

	public void createDefaultProductsIfNotExists() {

		if (!productRepository.exists(1L)) {
			LOG.info("Creating Free ride product.");

			Product product = new Product();
			product.setId(1);
			product.setName("Free ride");
			product.setPrice(9000);
			productRepository.save(product);
		}

		if (!productRepository.exists(2L)) {
			LOG.info("Creating Careem T-shirt product.");

			Product product = new Product();
			product.setId(2);
			product.setName("Careem T-shirt");
			product.setPrice(100);

			productRepository.save(product);
		}

		if (!productRepository.exists(3L)) {
			LOG.info("Creating Car type upgrade product.");

			Product product = new Product();
			product.setId(3);
			product.setName("Car type upgrade");
			product.setPrice(2000);

			productRepository.save(product);
		}

	}
}

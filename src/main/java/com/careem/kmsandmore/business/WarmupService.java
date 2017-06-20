package com.careem.kmsandmore.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careem.kmsandmore.data.CareemWallet;
import com.careem.kmsandmore.data.User;
import com.careem.kmsandmore.data.UserRepository;

@Service
public class WarmupService {
	
	private static Logger LOG = LoggerFactory.getLogger(WarmupService.class);

	@Autowired
	UserRepository userRepository;
	
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
	
}

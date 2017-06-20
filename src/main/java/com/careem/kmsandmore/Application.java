package com.careem.kmsandmore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.careem.kmsandmore.business.WarmupService;

@SpringBootApplication
public class Application {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		
		WarmupService warmupService = context.getBean(WarmupService.class);
		warmupService.createDefaultUsersIfNotExists();
	}

}

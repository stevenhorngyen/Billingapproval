package com.netflix.billing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BillingApplication {
	
	/**
	 * Billing Service Application
	 * @param args
	 * main parameters
	 */
	public static void main(String[] args) {
		SpringApplication.run(BillingApplication.class, args);
	}
}

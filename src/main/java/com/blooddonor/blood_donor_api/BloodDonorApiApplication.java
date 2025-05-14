package com.blooddonor.blood_donor_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "com.blooddonor.blood_donor_api.model")
@SpringBootApplication
public class BloodDonorApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloodDonorApiApplication.class, args);
	}

}

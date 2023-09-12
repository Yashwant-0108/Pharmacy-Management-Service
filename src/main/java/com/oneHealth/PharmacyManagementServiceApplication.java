package com.oneHealth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class PharmacyManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PharmacyManagementServiceApplication.class, args);
	}
	@GetMapping
    public String Welcome() {

        // This method handles GET requests to the root URL and returns a welcome message.

        return "Welcome From OneHealth Team (Pharmacy Management Service)!!!";

    }

}

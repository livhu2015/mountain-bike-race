package com.clickatel.raceclub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ovalleaf.client.controller", "com.ovalleaf.client.repository", "com.ovalleaf.client.service"})
public class RaceClubServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RaceClubServiceApplication.class, args);
	}

}

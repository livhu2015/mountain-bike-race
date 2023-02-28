package com.clickatel.raceclub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.clickatel.raceclub.config", "com.clickatel.raceclub.controller",
		"com.clickatel.raceclub.dto", "com.clickatel.raceclub.exception", "com.clickatel.raceclub.model",
		"com.clickatel.raceclub.repository", "com.clickatel.raceclub.service"})
public class RaceClubServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RaceClubServiceApplication.class, args);
	}

}

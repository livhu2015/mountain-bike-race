package com.clickatel.raceclub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class RaceClubServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RaceClubServiceApplication.class, args);
	}

}

package com.kingofnone.rentpentti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan( basePackages = "com.kingofnone.rentpentti.model" )
public class RentPenttiApplication {


	public static void main(String[] args) {
		SpringApplication.run(RentPenttiApplication.class, args);
	}

}

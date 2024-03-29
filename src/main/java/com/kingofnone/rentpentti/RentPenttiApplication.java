package com.kingofnone.rentpentti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EntityScan( basePackages = "com.kingofnone.rentpentti.model" )
public class RentPenttiApplication {


	public static void main(String[] args) {
		SpringApplication.run(RentPenttiApplication.class, args);
	}

}

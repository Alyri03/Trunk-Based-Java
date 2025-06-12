package com.trunk.based;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BasedApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasedApplication.class, args);
		System.out.println("Hola esto es una pruena de CI/CD, cuando hago un PR"); 
	}

}

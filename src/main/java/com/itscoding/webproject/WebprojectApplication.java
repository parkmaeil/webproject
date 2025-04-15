package com.itscoding.webproject;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SecurityScheme(name = "itscoding-api" , scheme = "bearer", type = SecuritySchemeType.HTTP, in= SecuritySchemeIn.HEADER)
public class WebprojectApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebprojectApplication.class, args);
	}

}

package com.itscoding.webproject.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title="User API",
                version="Ver 1.0",
                contact = @Contact(
                        name = "itscoding", email = "bitcocom@empas.com", url=""
                ),
                license = @License(
                       name ="Apache 2.0", url = ""
                ),
                termsOfService = "",
                description = "Spring Boot Restful API Demo by Park"
        )
)
public class SwaggerConfig {

}

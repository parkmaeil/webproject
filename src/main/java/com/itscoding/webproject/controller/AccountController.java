package com.itscoding.webproject.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
     @GetMapping("/")
     public String index(){
         return "Hello Spring Boot";
     }

     @GetMapping("/test")
     @Tag(name = "Test", description = "The Test API")
     @SecurityRequirement(name = "itscoding-api")
    public String test(){
         return "Test~";
    }
}

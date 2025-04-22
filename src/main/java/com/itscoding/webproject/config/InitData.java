package com.itscoding.webproject.config;

import com.itscoding.webproject.entity.Account;
import com.itscoding.webproject.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;

//@Configuration
@RequiredArgsConstructor
public class InitData implements CommandLineRunner {

    private final AuthService authService;

    @Override
    public void run(String... args) throws Exception {
        Account account01=new Account();
        Account account02=new Account();

        account01.setEmail("aaa@empas.com");
        account01.setPassword("aaaaa");
        account01.setRole("ROLE_USER");

        account02.setEmail("bbb@empas.com");
        account02.setPassword("bbbbb");
        account02.setRole("ROLE_ADMIN");

        authService.save(account01);
        authService.save(account02);

    }
}
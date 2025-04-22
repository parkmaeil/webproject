package com.itscoding.webproject.repository;

import com.itscoding.webproject.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Account, Long> {

    public Optional<Account> findByEmail(String email);
}
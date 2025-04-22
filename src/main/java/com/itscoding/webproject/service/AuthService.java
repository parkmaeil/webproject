package com.itscoding.webproject.service;

import com.itscoding.webproject.dto.AccountViewDTO;
import com.itscoding.webproject.entity.Account;
import com.itscoding.webproject.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService implements  UserDetailsService{

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Account save(Account account){
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        if(account.getRole()==null){
            account.setRole("ROLE_USER");
        }
        return authRepository.save(account);
    }

    @Transactional(readOnly=true)
    public List<AccountViewDTO> findAll(){
        List<Account> accounts= authRepository.findAll();
        return accounts.stream().map(account -> {
            return  new AccountViewDTO(account.getId(), account.getEmail(), account.getRole()) ;
        }).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Account> optionalAccount= authRepository.findByEmail(email);
        if(!optionalAccount.isPresent()){
            throw new UsernameNotFoundException("Account not found");
        }
        Account account=optionalAccount.get();
        // 권한처리 : ROLE_USER(String), 1개 -> GrantedAuthority, 권한여러개,
        List<GrantedAuthority> grantedAuthority=new ArrayList<>();
        grantedAuthority.add(new SimpleGrantedAuthority(account.getRole()));
        // 내부에서 패스워드 검증 시도.....
        return new User(account.getEmail(), account.getPassword(), grantedAuthority);
    }

}
package com.itscoding.webproject.controller;

import com.itscoding.webproject.dto.AccountDTO;
import com.itscoding.webproject.dto.TokenDTO;
import com.itscoding.webproject.dto.UserLoginDTO;
import com.itscoding.webproject.entity.Account;
import com.itscoding.webproject.service.AccountService;
import com.itscoding.webproject.service.JwtTokenService;
import com.itscoding.webproject.util.constants.AccountError;
import com.itscoding.webproject.util.constants.AccountSuccess;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name="Auth Controller", description = "Controller for Account management")
@Slf4j
public class AuthController {

    private  final AccountService accountService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService tokenService;

    @PostMapping("/token")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TokenDTO> token(@RequestBody UserLoginDTO userLogin) throws AuthenticationException {
        try{
            Authentication authentication=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLogin.getEmail(),  userLogin.getPassword()));
            // 인증성공 후 JWT 생성하는 부분
            return ResponseEntity.ok(new TokenDTO(tokenService.generateToken(authentication)));
        }catch (Exception e) {
            log.debug(AccountError.TOKEN_GENERATION_ERROR.toString()+":"+e.getMessage());
            return new ResponseEntity<>(new TokenDTO(null), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/users/add")
    @ResponseStatus(HttpStatus.OK) // { "email" : "aaa@aaa.aa.aa", "password" : "11111" }
    public ResponseEntity<?> adduser(@RequestBody AccountDTO accountDTO){
        try{
            Account account=new Account();
            account.setEmail(accountDTO.getEmail());
            account.setPassword(accountDTO.getPassword());
            account.setRole("ROLE_USER");
            accountService.save(account);
            return ResponseEntity.ok(AccountSuccess.ACCOUNT_ADDED.toString());
        }catch(Exception e){
            log.debug(AccountError.ADD_ACCOUNT_ERROR.toString()+": "+e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/users")
    @SecurityRequirement(name = "itscoding-api")
    public ResponseEntity<?> users(){
        return new ResponseEntity<>(accountService.findAll(), HttpStatus.OK);
    }
}

package com.chubb.controller;

import com.chubb.model.Account;
import com.chubb.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {
    
    @Autowired
    private AccountService accountService;

    @GetMapping("/users/{userId}/accounts")
    public ResponseEntity<List<Account>> getAllAccountsByUserId(
            @PathVariable(value = "userId") Long userId) {
        
        List<Account> accounts = accountService.getAllAccountsByUserId(userId);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @PostMapping("/users/{userId}/accounts")
    public ResponseEntity<Account> createAccount(
            @PathVariable(value = "userId") Long userId,
            @RequestBody Account account) {
        
        Account createdAccount = accountService.createAccount(userId, account);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable("id") Long id) {
        Account account = accountService.getAccountById(id)
            .orElseThrow(() -> new RuntimeException("Account not found with id = " + id));
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity<Account> updateAccount(
            @PathVariable("id") Long id, 
            @RequestBody Account account) {
        
        Account updatedAccount = accountService.updateAccount(id, account);
        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<HttpStatus> deleteAccount(@PathVariable("id") Long id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/users/{userId}/accounts")
    public ResponseEntity<HttpStatus> deleteAllAccountsOfUser(
            @PathVariable(value = "userId") Long userId) {
        
        accountService.deleteAllAccountsByUserId(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

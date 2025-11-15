package com.chubb.service;

import com.chubb.model.Account;
import com.chubb.model.User;
import com.chubb.repository.AccountRepository;
import com.chubb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public List<Account> getAllAccountsByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found with id = " + userId);
        }
        return accountRepository.findByUserId(userId);
    }
    
    public Account createAccount(Long userId, Account account) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found with id = " + userId));
        
        account.setUser(user);
        return accountRepository.save(account);
    }
    
    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }
    
    public Account updateAccount(Long id, Account accountDetails) {
        Account account = accountRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Account not found with id = " + id));
        
        account.setAccountNumber(accountDetails.getAccountNumber());
        account.setAccountType(accountDetails.getAccountType());
        account.setBalance(accountDetails.getBalance());
        
        return accountRepository.save(account);
    }
    
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
    
    @Transactional
    public void deleteAllAccountsByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found with id = " + userId);
        }
        accountRepository.deleteByUserId(userId);
    }
}

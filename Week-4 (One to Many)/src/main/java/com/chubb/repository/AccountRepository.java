package com.chubb.repository;

import java.util.List;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.chubb.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByUserId(Long userId);
    
    List<Account> findByAccountType(String accountType);
    
    @Transactional
    void deleteByUserId(Long userId);
}

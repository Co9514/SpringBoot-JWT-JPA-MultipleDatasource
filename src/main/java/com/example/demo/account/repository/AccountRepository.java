package com.example.demo.account.repository;

import com.example.demo.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,String> {
    Account findBySid(String sid);
}

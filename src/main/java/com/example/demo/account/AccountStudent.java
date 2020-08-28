package com.example.demo.account;

import com.example.demo.account.domain.Account;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

//토큰에 담아 어플내에서 사용할 인증여부 Student객체
@Getter
public class AccountStudent extends User {
    private Account account;

    public AccountStudent(Account account) {
        super(account.getSid(), account.getPwd(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.account = account;
    }
}


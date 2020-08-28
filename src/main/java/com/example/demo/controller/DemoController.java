package com.example.demo.controller;

import com.example.demo.account.domain.Account;
import com.example.demo.account.AccountDTO;
import com.example.demo.auth.domain.User;
import com.example.demo.auth.repository.UserRepository;
import com.example.demo.config.CustomPasswordEncoder;
import com.example.demo.account.AccountMapper;
import com.example.demo.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class DemoController {
    @Autowired
    AccountRepository accountRepository;
    UserRepository userRepository ;
    CustomPasswordEncoder customPasswordEncoder = new CustomPasswordEncoder();

    @GetMapping("/")
    public ResponseEntity<AccountDTO> test(){
        Account account = new Account();
        account.setSid("20173163");
        account.setPwd(customPasswordEncoder.encode("test1234!"));
        accountRepository.saveAndFlush(account);
        Account account2 = accountRepository.findBySid("20173163");
        AccountDTO accountDTO = AccountMapper.INSTANCE.userToUserDto(account2);
        return new ResponseEntity<>(accountDTO, HttpStatus.OK);
    }

    @GetMapping("/pg")
    public ResponseEntity<?> pgtest(){
        User user = new User();
        user.setSid("20173163");
        user.setName("김형진");
        userRepository.saveAndFlush(user);
        Optional<User> user1 = userRepository.findById("20173163");
        return new ResponseEntity<>(user1, HttpStatus.OK);
    }
}

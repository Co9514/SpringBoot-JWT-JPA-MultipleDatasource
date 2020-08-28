package com.example.demo.config.jwt;

import com.example.demo.account.domain.Account;
import com.example.demo.account.AccountStudent;
import com.example.demo.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class JwtUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Transactional(readOnly = true)
    @Override
    //DB에서 유저찾아서 가져오기
    public UserDetails loadUserByUsername(String sid) throws UsernameNotFoundException {
        Account account = accountRepository.findBySid(sid);
        if(account == null){
            throw new UsernameNotFoundException(sid);
        }
        return new AccountStudent(account);
    }
}
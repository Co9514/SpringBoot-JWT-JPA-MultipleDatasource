package com.example.demo.config;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class CustomPasswordEncoder implements PasswordEncoder {
    @Override
    //커스텀 패스워드 인코딩 SHA-512 방식을 스프링 시큐리티에서 지원하지 않기 때문에 따로 인코더를 만들어주어 추가했다.
    public String encode(CharSequence rawPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = rawPassword.toString().getBytes();
            md.update(bytes);
            return Base64.getEncoder().encodeToString(md.digest());
        } catch (NoSuchAlgorithmException e) {
            //Error 처리 하도록할것
            e.printStackTrace();
            return null;
        }
    }
    @Override
    //사용자 패스워드와 실제 비밀번호를 비교하는 부분
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(encode(rawPassword));
    }
}

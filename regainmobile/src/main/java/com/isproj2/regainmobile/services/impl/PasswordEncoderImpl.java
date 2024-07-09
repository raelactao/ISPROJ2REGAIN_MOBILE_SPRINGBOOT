package com.isproj2.regainmobile.services.impl;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.isproj2.regainmobile.security.AESEncryptor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordEncoderImpl implements PasswordEncoder {

    private AESEncryptor _aesEncryptor;

    @Override
    public String encode(CharSequence rawPassword) {
        return _aesEncryptor.encrypt(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String hashedPassword = encode(rawPassword);
        return encodedPassword.equals(hashedPassword);
    }

}

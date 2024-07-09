package com.isproj2.regainmobile.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.isproj2.regainmobile.model.AppUser;
import com.isproj2.regainmobile.repo.UserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository _userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final AppUser users = _userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return User.builder()
                .username(users.getUsername())
                .password(users.getPassword())
                .roles(users.getRole().toString())
                .build();
    }

}

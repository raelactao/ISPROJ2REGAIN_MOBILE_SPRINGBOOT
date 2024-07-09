package com.isproj2.regainmobile.controller;

import java.net.http.HttpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isproj2.regainmobile.dto.UserDTO;
import com.isproj2.regainmobile.model.AppUser;
import com.isproj2.regainmobile.model.AuthResponseModel;
import com.isproj2.regainmobile.repo.RoleRepository;
import com.isproj2.regainmobile.security.AESEncryptor;
import com.isproj2.regainmobile.security.JwtTokenProvider;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Value("${regainmobile.app.jwt-expiration-millis}")
    private Long expiration;

    @Autowired
    private AuthenticationManager _authenticationManager;

    @Autowired
    private JwtTokenProvider _jwtTokenProvider;

    @Autowired
    private AESEncryptor aesEncryptor;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseModel> loginUser(@RequestBody AppUser user) {
        final AuthResponseModel authResponseModel;

        // AppUser actualUser = new AppUser();
        // actualUser.setUsername(user.getUsername());
        // actualUser.setPassword(aesEncryptor.encrypt(user.getPassword()));
        // actualUser.setRole(roleRepository.findByName(user.getRole()));

        // Authentication authentication = _authenticationManager
        // .authenticate(new
        // UsernamePasswordAuthenticationToken(actualUser.getUsername(),
        // actualUser.getPassword()));

        Authentication authentication = _authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),
                        user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = _jwtTokenProvider.generateToken(authentication);
        authResponseModel = new AuthResponseModel(HttpStatus.OK.value(), "Successfully logged in", token,
                System.currentTimeMillis(), expiration);
        return ResponseEntity.ok(authResponseModel);
    }

}

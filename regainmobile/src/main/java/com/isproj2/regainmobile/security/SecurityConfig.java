package com.isproj2.regainmobile.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.isproj2.regainmobile.services.AppUserDetailsService;
import com.isproj2.regainmobile.services.impl.PasswordEncoderImpl;

import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JWTEntryPoint _jwtEntryPoint;

    @Autowired
    private AppUserDetailsService _appUserDetailsService;

    @Autowired
    private JwtAuthFilter _jwtAuthFilter;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // .csrf(csrf -> csrf.disable())
        // .requestMatchers(HttpMethod.GET).permitAll()

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/register/**").permitAll())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(_jwtEntryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(_jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(AESEncryptor aesEncryptor) {
        PasswordEncoderImpl passwordEncoder = new PasswordEncoderImpl();
        passwordEncoder.set_aesEncryptor(aesEncryptor);

        return passwordEncoder;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}

package com.isproj2.regainmobile.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Value("${spring.websecurity.debug:false}")
        boolean webSecurityDebug;

        @Bean
        public WebSecurityCustomizer webSecurityCustomizer() {
                return (web) -> web.debug(webSecurityDebug);
        }

        @Bean
        BCryptPasswordEncoder BCryptPasswordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                // return http
                // .authorizeHttpRequests(
                // authz -> authz
                // .requestMatchers("/api/register", "/api/login")
                // .permitAll()
                // .requestMatchers("/api/user/addID").permitAll()
                // .requestMatchers("/api/user/update").permitAll()
                // .requestMatchers(
                // "/api/products/viewlist/{id}",
                // "/api/products/userviewlist/{id}")
                // .permitAll()
                // .requestMatchers("/api/addresses/*",
                // "/api/addresses/user/{id}")
                // .permitAll()
                // .requestMatchers("/api/category/list").permitAll()
                // .requestMatchers("/api/chat/*").permitAll()
                // .requestMatchers("/api/favorites/*",
                // "/api/favorites/list/{id}",
                // "/api/favorites/delete/{userId}/{productId}")
                // .permitAll()
                // .requestMatchers("/api/green_zone/*").permitAll()
                // .requestMatchers("/api/listingreports/*").permitAll()
                // .requestMatchers("/api/products/add",
                // "/api/products/list/{id}",
                // "/api/products/update/{id}",
                // "/api/products/delete/{id}")
                // .permitAll()
                // .requestMatchers("/api/offers/add",
                // "/api/offers/buyer/{buyerId}/viewoffers",
                // "/api/offers/product/{productId}/viewoffers")
                // .permitAll()
                // .requestMatchers(new AntPathRequestMatcher(
                // "/api/offers/{id}", "PUT"))
                // .permitAll()
                // .requestMatchers(new AntPathRequestMatcher(
                // "/api/offers/{id}", "DELETE"))
                // .permitAll()
                // .requestMatchers("/api/orders/*").permitAll()
                // .requestMatchers("/api/userreports/*").permitAll()
                // .anyRequest().authenticated())
                // .httpBasic(Customizer.withDefaults())
                // .csrf(csrf -> csrf.disable())
                // .build();

                return http
                                .authorizeHttpRequests(
                                                authz -> authz
                                                                .anyRequest().permitAll())
                                .httpBasic(Customizer.withDefaults())
                                .csrf(csrf -> csrf.disable())
                                .build();
        }

}

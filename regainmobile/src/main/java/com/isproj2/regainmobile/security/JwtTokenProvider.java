package com.isproj2.regainmobile.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.isproj2.regainmobile.model.RegainmobileApiException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

    @Value("${regainmobile.app.jwt-secret}")
    private String jwtSecretKey;

    @Value("${regainmobile.app.jwt-expiration-millis}")
    private Long expiration;

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date expiryDate = new Date(new Date().getTime() + expiration);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(key())
                .compact();
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException e) {
            throw new RegainmobileApiException(HttpStatus.BAD_REQUEST, "Invalid Token");
        } catch (ExpiredJwtException e) {
            throw new RegainmobileApiException(HttpStatus.BAD_REQUEST, "Token Expired");
        } catch (UnsupportedJwtException e) {
            throw new RegainmobileApiException(HttpStatus.BAD_REQUEST, "Unsupported JWT");
        } catch (IllegalArgumentException e) {
            throw new RegainmobileApiException(HttpStatus.BAD_REQUEST, "Invalid Arguments");
        }

    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecretKey));
    }

}

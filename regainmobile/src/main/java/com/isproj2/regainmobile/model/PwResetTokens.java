package com.isproj2.regainmobile.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pw_reset_tokens")
public class PwResetTokens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private Integer tokenId;

    @Column(name = "otp", nullable = false)
    private String otp; 

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "expiration_time", nullable = false)
    private LocalDateTime expirationTime;

    @Column(name = "is_used", nullable = false)
    private Boolean isUsed;

    public PwResetTokens(String otp, String email, LocalDateTime expirationTime, Boolean isUsed) {
        this.otp = otp;
        this.email = email;
        this.expirationTime = expirationTime;
        this.isUsed = isUsed;
    }
}

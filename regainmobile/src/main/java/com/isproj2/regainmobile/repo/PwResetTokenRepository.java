package com.isproj2.regainmobile.repo;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isproj2.regainmobile.model.PwResetTokens;

@Repository
public interface PwResetTokenRepository extends JpaRepository<PwResetTokens, Integer> {

    Optional<PwResetTokens> findByEmail(String email);
    Optional<PwResetTokens> findByOtp(String otp);
    Optional<PwResetTokens> findByOtpAndExpirationTimeAfter(String otp, LocalDateTime currentTime);
}

package com.isproj2.regainmobile.services.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.isproj2.regainmobile.model.PwResetTokens;
import com.isproj2.regainmobile.model.User;
import com.isproj2.regainmobile.repo.PwResetTokenRepository;
import com.isproj2.regainmobile.repo.UserRepository;
import com.isproj2.regainmobile.services.EmailService;
import com.isproj2.regainmobile.services.PwResetTokenService;

@Service
public class PwResetTokenServiceImpl implements PwResetTokenService {

    private static final Logger logger = Logger.getLogger(PwResetTokenServiceImpl.class.getName());

    @Autowired
    private PwResetTokenRepository pwResetTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Override
    public PwResetTokens generateResetToken(String email) {
        if (userRepository.existsByEmail(email)) {
            String otp = String.format("%04d", new Random().nextInt(10000)); // Generate a 4-digit OTP
            LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(10); // Token expires in 10 minutes

            PwResetTokens pwResetToken = new PwResetTokens();
            pwResetToken.setEmail(email);
            pwResetToken.setOtp(otp);  // Store the plain OTP
            pwResetToken.setExpirationTime(expirationTime);
            pwResetToken.setIsUsed(false);

            pwResetTokenRepository.save(pwResetToken);
            emailService.sendResetPasswordEmail(email, otp); // Send the OTP email

            logger.log(Level.INFO, "Reset token generated and email sent to: {0}", email);

            return pwResetToken;
        }
        return null; // If email does not exist, return null
    }

    @Override
    public boolean validateResetToken(String otp) {
        LocalDateTime currentTime = LocalDateTime.now();
        Optional<PwResetTokens> pwResetTokenOpt = pwResetTokenRepository.findByOtpAndExpirationTimeAfter(otp, currentTime);

        if (pwResetTokenOpt.isPresent()) {
            PwResetTokens pwResetToken = pwResetTokenOpt.get();
            if (!pwResetToken.getIsUsed()) {
                logger.log(Level.INFO, "OTP {0} is valid and not used.", otp);
                return true; // OTP is valid and not used
            } else {
                logger.log(Level.WARNING, "OTP {0} has already been used.", otp);
            }
        } else {
            logger.log(Level.WARNING, "OTP {0} not found or expired.", otp);
        }
        return false;
    }


    @Override
    public boolean resetPassword(String otp, String newPassword) {
        Optional<PwResetTokens> pwResetTokenOpt = pwResetTokenRepository.findByOtp(otp);

        if (pwResetTokenOpt.isPresent()) {
            PwResetTokens pwResetToken = pwResetTokenOpt.get();

            if (validateResetToken(otp)) {
                Optional<User> userOpt = userRepository.findByEmail(pwResetToken.getEmail());
                if (userOpt.isPresent()) {
                    User user = userOpt.get();
                    String encryptedPassword = passwordEncoder.encode(newPassword); // Hash the new password
                    user.setPassword(encryptedPassword); // Set the hashed new password
                    userRepository.save(user);

                    pwResetToken.setIsUsed(true); // Mark the token as used
                    pwResetTokenRepository.save(pwResetToken);

                    logger.log(Level.INFO, "Password reset successful for user: {0}", pwResetToken.getEmail());
                    return true;
                }
            } else {
                logger.log(Level.WARNING, "OTP validation failed or expired.");
            }
        } else {
            logger.log(Level.WARNING, "Token not found for OTP: {0}", otp);
        }
        return false;
    }
}

package com.isproj2.regainmobile.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isproj2.regainmobile.model.PwResetTokens;
import com.isproj2.regainmobile.model.ResponseModel;
import com.isproj2.regainmobile.services.PwResetTokenService;

@RestController
@RequestMapping("/api/password")
public class PasswordResetController {

    @Autowired
    private PwResetTokenService pwResetTokenService;

    // Endpoint to request password reset (generate OTP and send email)
    @PostMapping("/request-reset")
    public ResponseModel<Map<String, String>> requestPasswordReset(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        PwResetTokens token = pwResetTokenService.generateResetToken(email);

        Map<String, String> responseMap = new HashMap<>();
        if (token != null) {
            responseMap.put("message", "Password reset OTP has been sent to your email.");
            return new ResponseModel<>(HttpStatus.OK.value(), "Success", responseMap);
        }

        responseMap.put("message", "Email not found.");
        return new ResponseModel<>(HttpStatus.NOT_FOUND.value(), "Error", responseMap);
    }

    @PostMapping("/verify-otp")
    public ResponseModel<Map<String, String>> verifyOtp(@RequestBody Map<String, String> requestBody) {
        String otp = requestBody.get("otp");

        Map<String, String> responseMap = new HashMap<>();
        boolean isOtpValid = pwResetTokenService.validateResetToken(otp);

        if (isOtpValid) {
            responseMap.put("message", "OTP is valid.");
            return new ResponseModel<>(HttpStatus.OK.value(), "Success", responseMap);
        }

        responseMap.put("message", "Invalid or expired OTP.");
        return new ResponseModel<>(HttpStatus.BAD_REQUEST.value(), "Error", responseMap);
    }

    // Endpoint to reset password using OTP
    @PostMapping("/reset")
    public ResponseModel<Map<String, String>> resetPassword(@RequestBody Map<String, String> requestBody) {
        String otp = requestBody.get("otp");
        String newPassword = requestBody.get("newPassword");

        Map<String, String> responseMap = new HashMap<>();
        boolean isResetSuccessful = pwResetTokenService.resetPassword(otp, newPassword);

        if (isResetSuccessful) {
            responseMap.put("message", "Password reset successful.");
            return new ResponseModel<>(HttpStatus.OK.value(), "Success", responseMap);
        }

        responseMap.put("message", "Invalid OTP or password reset request.");
        return new ResponseModel<>(HttpStatus.BAD_REQUEST.value(), "Error", responseMap);
    }
}


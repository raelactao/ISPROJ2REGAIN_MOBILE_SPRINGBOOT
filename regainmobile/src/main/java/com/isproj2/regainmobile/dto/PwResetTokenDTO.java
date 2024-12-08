package com.isproj2.regainmobile.dto;

import java.time.LocalDateTime;

public class PwResetTokenDTO {

    private String otp;           
    private String email;
    private LocalDateTime expirationTime;
    private Boolean isUsed;

    public PwResetTokenDTO() {}

    public PwResetTokenDTO(String otp, String email, LocalDateTime expirationTime, Boolean isUsed) {
        this.otp = otp;
        this.email = email;
        this.expirationTime = expirationTime;
        this.isUsed = isUsed;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }
}

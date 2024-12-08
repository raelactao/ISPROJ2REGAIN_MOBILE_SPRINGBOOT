package com.isproj2.regainmobile.services;

import com.isproj2.regainmobile.model.PwResetTokens;

public interface PwResetTokenService {

    PwResetTokens generateResetToken(String email);

    boolean validateResetToken(String otp);

    boolean resetPassword(String otp, String newPassword);
}

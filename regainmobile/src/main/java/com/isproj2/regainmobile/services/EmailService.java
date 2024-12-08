package com.isproj2.regainmobile.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendResetPasswordEmail(String toEmail, String otp) {
        String subject = "Password Reset Request";
        
        String message = """
                         We have received a request to reset your password.
                         
                         Your OTP for resetting the password is: """ + otp + """
                         
                         This OTP is valid for the next 10 minutes.
                         
                         If you did not request a password reset, please ignore this email.
                         
                         To proceed, use the OTP in the reset password form.
                         If you have any issues, please contact support.
                         """;
        
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(toEmail);
        email.setSubject(subject);
        email.setText(message);

        javaMailSender.send(email);
    }
}

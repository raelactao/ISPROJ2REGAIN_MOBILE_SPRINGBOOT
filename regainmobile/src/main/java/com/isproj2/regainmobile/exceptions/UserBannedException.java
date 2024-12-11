package com.isproj2.regainmobile.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserBannedException extends RuntimeException {
    private String message = "Your account has been banned for violating our terms of service.";
}

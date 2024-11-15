package com.isproj2.regainmobile.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountNotActiveException extends RuntimeException {
    private String message = "User account is under pending verification.";
}

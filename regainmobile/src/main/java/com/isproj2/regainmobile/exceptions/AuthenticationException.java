package com.isproj2.regainmobile.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationException extends RuntimeException {
    private String message = "Invalid username or password";

}

package com.isproj2.regainmobile.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserAlreadyExistsException extends RuntimeException {
    private String message = "Username or Email already exists";
}

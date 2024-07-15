package com.isproj2.regainmobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isproj2.regainmobile.dto.UserDTO;
import com.isproj2.regainmobile.model.ResponseModel;
import com.isproj2.regainmobile.services.UserService;

import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/api/register")
public class RegistrationController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseModel<UserDTO> registerUser(@RequestBody UserDTO user) {
        // UserDTO savedUser = userService.addUser(user);

        try {
            userService.addUser(user);
        } catch (ValidationException e) {
            return new ResponseModel<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }

        return new ResponseModel<>(HttpStatus.OK.value(), "User saved", user);
    }
}

package com.isproj2.regainmobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isproj2.regainmobile.dto.UserDTO;
import com.isproj2.regainmobile.model.ResponseModel;
import com.isproj2.regainmobile.services.UserService;

import jakarta.validation.ValidationException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseModel<UserDTO> login(@RequestBody UserDTO userDTO) {

        UserDTO loginUser;

        try {
            loginUser = userService.login(userDTO);
        } catch (ValidationException e) {
            return new ResponseModel<UserDTO>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), userDTO);
        }

        return new ResponseModel<UserDTO>(HttpStatus.OK.value(), "User logged in succesfully", loginUser);
    }

}

package com.isproj2.regainmobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isproj2.regainmobile.dto.UserDTO;
import com.isproj2.regainmobile.model.ResponseModel;
import com.isproj2.regainmobile.model.User;
import com.isproj2.regainmobile.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/register")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseModel registerUser(@RequestBody UserDTO user) {
        final User savedUser = userService.addUser(user);
        return new ResponseModel<>(HttpStatus.OK.value(), "User saved", savedUser);
    }

}
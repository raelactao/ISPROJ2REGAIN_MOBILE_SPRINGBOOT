package com.isproj2.regainmobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isproj2.regainmobile.model.User;
import com.isproj2.regainmobile.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/register")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String registerUser(@RequestBody User user) {
        userService.addUser(user);
        return "User registered successfully";
    }

}